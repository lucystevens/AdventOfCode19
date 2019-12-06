package uk.co.lukestevens.challenges;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrbitalMapper {
	
	final Map<String, OrbitingObject> orbits = new HashMap<>();
	
	public OrbitalMapper(List<String> orbits) {
		this.buildOrbitMap(orbits);
	}
	
	void buildOrbitMap(List<String> orbits) {
		
	}
	
	public class OrbitingObject {
		
		String identifier;
		OrbitingObject directOrbit;
		List<OrbitingObject> indirectOrbits = new ArrayList<>();
		
		public OrbitingObject(String identifier) {
			this.identifier = identifier;
		}
		
		public void setDirectOrbit(OrbitingObject directOrbit) {
			this.directOrbit = directOrbit;
			if(this.directOrbit.hasDirectOrbit()) {
				this.indirectOrbits.add(directOrbit.getDirectOrbit());
			}
			this.indirectOrbits.addAll(directOrbit.getIndirectOrbits());
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

		public List<OrbitingObject> getIndirectOrbits() {
			return indirectOrbits;
		}
		
	}

}
