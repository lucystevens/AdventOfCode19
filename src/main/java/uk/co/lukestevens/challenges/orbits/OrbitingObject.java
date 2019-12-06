package uk.co.lukestevens.challenges.orbits;

import java.util.ArrayList;
import java.util.List;


public class OrbitingObject {
	
	private String identifier;
	private OrbitingObject directOrbit;
	private List<OrbitingObject> orbitingBodies = new ArrayList<>();
	private Integer totalOrbits = null;
	
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
	
	public boolean hasTotalOrbits() {
		return totalOrbits != null;
	}

	public OrbitingObject getDirectOrbit() {
		return directOrbit;
	}
	
	public void addOrbitingBody(OrbitingObject body) {
		this.orbitingBodies.add(body);
	}

	public List<OrbitingObject> getOrbitingBodies() {
		return orbitingBodies;
	}
	
	public Integer getTotalOrbits() {
		return totalOrbits;
	}

	public int calculateTotalOrbits() {
		this.totalOrbits = 0;
		OrbitingObject parent = this.directOrbit;
		while(parent != null) {
			this.totalOrbits ++;
			
			if(parent.hasTotalOrbits()) {
				this.totalOrbits += parent.totalOrbits;
				break;
			}
			
			parent = parent.getDirectOrbit();
		}
		return this.totalOrbits;
	}

	
}
