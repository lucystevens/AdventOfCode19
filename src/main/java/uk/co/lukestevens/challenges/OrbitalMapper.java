package uk.co.lukestevens.challenges;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.co.lukestevens.utils.Wrapper;

public class OrbitalMapper {
	
	final Map<String, OrbitingObject> orbits = new HashMap<>();
	
	public OrbitalMapper(List<String> orbits) {
		this.buildOrbitMap(orbits);
	}
	
	void buildOrbitMap(List<String> orbits) {
		for(String orbit : orbits) {
			String[] parts = orbit.split("\\)");
			OrbitingObject parent = this.orbits.computeIfAbsent(parts[0], OrbitingObject::new);
			OrbitingObject child = this.orbits.computeIfAbsent(parts[1], OrbitingObject::new);
			child.setDirectOrbit(parent);
		}
	}
	
	public Map<String, OrbitingObject> getOrbitMap() {
		return orbits;
	}
	
	public int getTotalNumberOfOrbits() {
		return this.orbits.values().stream().mapToInt(OrbitingObject::getTotalOrbits).sum();
	}

	public int getOrbitalTransfers(String start, String finish) {
		OrbitingObject startBody = this.orbits.get(start).getDirectOrbit();
		OrbitingObject endBody = this.orbits.get(finish).getDirectOrbit();
		return this.getOrbitalTransfers(startBody, endBody, new HashSet<>());
	}
	
	int getOrbitalTransfers(OrbitingObject start, OrbitingObject finish, Set<OrbitingObject> traversedOrbits) {
		if(start == finish) {
			return 0;
		}
		
		Set<OrbitingObject> newTraversedOrbits = new HashSet<>(traversedOrbits);
		newTraversedOrbits.add(start);
		
		List<OrbitingObject> bodiesToCheck = new ArrayList<>(start.orbitingBodies);
		if(start.hasDirectOrbit()) {
			bodiesToCheck.add(start.getDirectOrbit());
		}
		
		Wrapper<Integer> minimumTransfers = new Wrapper<>();
		bodiesToCheck.stream()
			.filter(oo -> !traversedOrbits.contains(oo))
			.forEach(obj -> {
				int transferTime = this.getOrbitalTransfers(obj, finish, newTraversedOrbits) + 1;
				if(transferTime > -1 && (minimumTransfers.isNull() || transferTime < minimumTransfers.get())) {
					minimumTransfers.set(transferTime);;
				}
			});
		
		return minimumTransfers.isNull()? -10 : minimumTransfers.get();
	}
	
	public class OrbitingObject {
		
		String identifier;
		OrbitingObject directOrbit;
		List<OrbitingObject> orbitingBodies = new ArrayList<>();
		
		public OrbitingObject(String identifier) {
			this.identifier = identifier;
		}
		
		public void setDirectOrbit(OrbitingObject directOrbit) {
			this.directOrbit = directOrbit;
			this.directOrbit.addOrbitingBody(this);
		}

		public String getIdentifier() {
			return identifier;
		}
		
		public boolean hasDirectOrbit() {
			return directOrbit != null;
		}

		public OrbitingObject getDirectOrbit() {
			return directOrbit;
		}
		
		public void addOrbitingBody(OrbitingObject body) {
			this.orbitingBodies.add(body);
		}
		
		public int getTotalOrbits() {
			int totalOrbits = 0;
			OrbitingObject parent = this.directOrbit;
			while(parent != null) {
				parent = parent.getDirectOrbit();
				totalOrbits ++;
			}
			return totalOrbits;
		}
	
		
	}

}
