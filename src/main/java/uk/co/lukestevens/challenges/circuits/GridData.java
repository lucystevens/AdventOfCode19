package uk.co.lukestevens.challenges.circuits;

class GridData {
	
	public static final GridData CENTRAL_PORT = new GridData(true);
	
	Integer wire1Steps = null;
	Integer wire2Steps = null;
	
	boolean centralPort = false;
	
	public GridData() {
		this(false);
	}
	
	private GridData(boolean centralPort) {
		this.centralPort = centralPort;
	}
	
	public void setWireSteps(Wire wire, int steps) {
		if(wire == Wire.WIRE_1) {
			this.setWire1Steps(steps);
		}
		else if(wire == Wire.WIRE_2) {
			this.setWire2Steps(steps);
		}
		else {
			throw new RuntimeException(wire + " is not a valid wire!"); 
		}
	}
	
	public boolean hasWire(Wire wire) {
		if(wire == Wire.WIRE_1) {
			return this.hasWire1();
		}
		else if(wire == Wire.WIRE_2) {
			return this.hasWire2();
		}
		else {
			throw new RuntimeException(wire + " is not a valid wire!"); 
		}
	}
	
	public int getWireSteps(Wire wire) {
		if(wire == Wire.WIRE_1) {
			return this.getWire1Steps();
		}
		else if(wire == Wire.WIRE_2) {
			return this.getWire2Steps();
		}
		else {
			throw new RuntimeException(wire + " is not a valid wire!"); 
		}
	}
	
	public void setWire1Steps(int steps) {
		this.wire1Steps = steps;
	}
	
	public boolean hasWire1() {
		return wire1Steps != null;
	}
	
	public int getWire1Steps() {
		return wire1Steps;
	}
	
	public void setWire2Steps(int steps) {
		this.wire2Steps = steps;
	}
	
	public boolean hasWire2() {
		return wire2Steps != null;
	}
	
	public int getWire2Steps() {
		return wire2Steps;
	}
	
	public boolean isIntersection() {
		return hasWire1() && hasWire2() && !centralPort;
	}
}
