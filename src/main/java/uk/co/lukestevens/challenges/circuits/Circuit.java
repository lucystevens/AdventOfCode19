package uk.co.lukestevens.challenges.circuits;

import java.awt.Point;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import uk.co.lukestevens.utils.Grid;

public class Circuit {
	
	final Grid<GridData> grid = new Grid<>();
	final Point centralPort;
	
	public Circuit() {
		this(2000, 2000);
	}
	
	public Circuit(int x, int y) {
		this.centralPort = new Point(x, y);
		grid.set(centralPort, GridData.CENTRAL_PORT);
	}
	
	public int findClosestIntersectionToCentralPort() {
		return grid
			.stream()
			.filter(e -> e.getValue().isIntersection())
			.mapToInt(entry -> this.findDistanceToCentralPort(entry.getKey()))
			.sorted()
			.findFirst()
			.getAsInt();
	}
	
	public int findIntersectionWithFewestSteps() {
		return grid
				.stream()
				.filter(e -> e.getValue().isIntersection())
				.map(Entry::getValue)
				.mapToInt(GridData::getTotalSteps)
				.sorted()
				.findFirst()
				.getAsInt();
	}
	
	public int findDistanceToCentralPort(Point location) {
		return Math.abs(centralPort.x - location.x) + Math.abs(centralPort.y - location.y);
	}
	
	public void mapCircuits(List<List<String>> wires){
		for(int i = 0; i<wires.size(); i++) {
			this.mapWire(wires.get(i), i);
		}
	}
	
	void mapWire(List<String> wire, Integer thisWire) {
		Point currentPoint = centralPort;
		AtomicInteger steps = new AtomicInteger(0);
		for(String command : wire) {
			currentPoint = this.mapWireCommand(currentPoint, command, thisWire, steps);
		}
	}
	
	Point mapWireCommand(Point lastWireLocation, String command, Integer wire, AtomicInteger steps) {
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
				grid.set(location, atPosition);
			}
			else if(!atPosition.hasWire(wire)) {
				atPosition.setWireSteps(wire, steps.get());
				grid.set(location, atPosition);
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

}
