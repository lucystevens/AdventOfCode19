package uk.co.lukestevens.challenges;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

public class Circuit {
	
	static final String WIRE_1 = "1";
	static final String WIRE_2 = "2";
	
	final Map<Point, GridData> grid = new HashMap<>();
	final Point centralPort;
	
	public Circuit() {
		this(2000, 2000);
	}
	
	public Circuit(int x, int y) {
		this.centralPort = new Point(x, y);
		grid.put(centralPort, new GridData(true));
	}
	
	public int findClosestIntersectionToCentralPort() {
		return grid.entrySet()
			.stream()
			.filter(e -> e.getValue().isIntersection())
			.mapToInt(entry -> this.findDistanceToCentralPort(entry.getKey()))
			.sorted()
			.findFirst()
			.getAsInt();
	}
	
	public int findIntersectionWithFewestSteps() {
		return grid.entrySet()
				.stream()
				.filter(e -> e.getValue().isIntersection())
				.map(Entry::getValue)
				.mapToInt(data -> data.getWire1Steps() + data.getWire2Steps())
				.sorted()
				.findFirst()
				.getAsInt();
	}
	
	public int findDistanceToCentralPort(Point location) {
		return Math.abs(centralPort.x - location.x) + Math.abs(centralPort.y - location.y);
	}
	
	public void mapCircuit(List<String> wire1, List<String> wire2){
		this.mapWire(wire1, WIRE_1, WIRE_2);
		this.mapWire(wire2, WIRE_2, WIRE_1);
	}
	
	void mapWire(List<String> wire, String thisWire, String otherWire) {
		Point currentPoint = centralPort;
		AtomicInteger steps = new AtomicInteger(0);
		for(String command : wire) {
			currentPoint = this.mapWireCommand(currentPoint, command, thisWire, otherWire, steps);
		}
	}
	
	Point mapWireCommand(Point lastWireLocation, String command, String wire, String otherWire, AtomicInteger steps) {
		int distance = Integer.parseInt(command.replaceAll("\\D+", ""));
		String direction = command.replaceAll("\\d+", "");
		
		Point location = new Point(lastWireLocation);;
		for(int i = 1; i<= distance; i++) {
			steps.getAndIncrement();
			location = new Point(lastWireLocation);
			translatePoint(location, direction, i);
			
			GridData atPosition = grid.get(location);
			if(atPosition == null) {
				atPosition = new GridData();
				atPosition.setWireSteps(wire, steps.get());
				grid.put(location, atPosition);
			}
			else if(atPosition.hasWire(otherWire)) {
				atPosition.setWireSteps(wire, steps.get());
				grid.put(location, atPosition);
			}
		}
		
		return location;
	}
	
	void translatePoint(Point location, String direction, int distance){
		if(direction.equals("U")) {
			location.translate(0, distance);
		}
		else if(direction.equals("D")) {
			location.translate(0, -distance);
		}
		else if(direction.equals("L")) {
			location.translate(-distance, 0);
		}
		else if(direction.equals("R")) {
			location.translate(distance, 0);
		}
		else {
			throw new RuntimeException("Invaid direction " + direction);
		}
	}
	
	class GridData {
		
		// 		static final GridData CENTRAL_PORT = new GridData(true);
		
		Integer wire1Steps = null;
		Integer wire2Steps = null;
		
		boolean centralPort = false;
		
		public GridData() {
			this(false);
		}
		
		private GridData(boolean centralPort) {
			this.centralPort = centralPort;
		}
		
		public void setWireSteps(String wire, int steps) {
			if(wire.equals(WIRE_1)) {
				this.setWire1Steps(steps);
			}
			else if(wire.equals(WIRE_2)) {
				this.setWire2Steps(steps);
			}
			else {
				throw new RuntimeException(wire + " is not a valid wire!"); 
			}
		}
		
		public boolean hasWire(String wire) {
			if(wire.equals(WIRE_1)) {
				return this.hasWire1();
			}
			else if(wire.equals(WIRE_2)) {
				return this.hasWire2();
			}
			else {
				throw new RuntimeException(wire + " is not a valid wire!"); 
			}
		}
		
		public int getWireSteps(String wire) {
			if(wire.equals(WIRE_1)) {
				return this.getWire1Steps();
			}
			else if(wire.equals(WIRE_2)) {
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

}
