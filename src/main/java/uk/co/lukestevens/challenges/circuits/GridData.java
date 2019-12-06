package uk.co.lukestevens.challenges.circuits;

import java.util.HashMap;
import java.util.Map;

class GridData {
	
	public static final GridData CENTRAL_PORT = new GridData(true);
	
	Map<Integer, Integer> wireSteps = new HashMap<>();
	boolean centralPort = false;
	
	public GridData() {
		this(false);
	}
	
	private GridData(boolean centralPort) {
		this.centralPort = centralPort;
	}
	
	public void setWireSteps(Integer wire, int steps) {
		this.wireSteps.put(wire, steps);
	}
	
	public boolean hasWire(Integer wire) {
		return this.wireSteps.containsKey(wire);
	}
	
	public int getWireSteps(Integer wire) {
		return this.wireSteps.getOrDefault(wire, 0);
	}
	
	public boolean isIntersection() {
		return this.wireSteps.size() > 1 && !centralPort;
	}
	
	public int getTotalSteps() {
		return this.wireSteps.values().stream().mapToInt(i -> i).sum();
	}
}
