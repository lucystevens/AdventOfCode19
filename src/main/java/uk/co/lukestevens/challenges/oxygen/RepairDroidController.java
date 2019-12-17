package uk.co.lukestevens.challenges.oxygen;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

import uk.co.lukestevens.challenges.intcode.IntCodeComputer;
import uk.co.lukestevens.challenges.intcode.IntCodeComputerFactory;
import uk.co.lukestevens.utils.Grid;
import uk.co.lukestevens.utils.Wrapper;

public class RepairDroidController {
	
	private final IntCodeComputerFactory factory;
	private final Grid<String> map = new Grid<>();
	private final Wrapper<Point> oxygenTank = new Wrapper<>();

	public RepairDroidController(Long[] program) {
		this.factory = new IntCodeComputerFactory(program);
		this.drawMap();
	}
	
	void drawMap() {
		RepairDroid droid = new RepairDroid();
		map.set(droid.getCurrentPoint(), ".");
		Stack<Direction> backtrackStack = new Stack<>();
		
		IntCodeComputer computer = factory.createComputer();
		computer.setInputCallback(() -> {
			Direction dir = null;
			for(Direction direction : Direction.values()) {
				Point nextPoint = droid.getNextPoint(direction);
				String cell = map.get(nextPoint);
				if(cell == null) {
					dir = direction;
				}
			}
			
			if(dir == null && backtrackStack.isEmpty()) {
				map.set(0, 0, "X");
				computer.halt();
				return 1L;
			}
			else if(dir == null && !backtrackStack.isEmpty()) {
				dir = backtrackStack.pop();
			}
			else {
				backtrackStack.add(dir.opposite());
			}
			return droid.move(dir);
		});
		
		computer.setOutputCallback(out -> {
			if(out == 0) {
				map.set(droid.getCurrentPoint(), "#");
				backtrackStack.pop();
				droid.reverse();
			}
			else if(out == 1) {
				map.set(droid.getCurrentPoint(), ".");
			}
			else {
				map.set(droid.getCurrentPoint(), "O");
				oxygenTank.set(new Point(droid.getCurrentPoint()));
			}
		});
		
		computer.run();
	}
	
	public Point getOxygenTank() {
		return this.oxygenTank.get();
	}
	
	public class QueueNode {
		Point point;
		int distance;
		
		public QueueNode(Point point, int distance) {
			this.point = point;
			this.distance = distance;
		}

		public Point getPoint() {
			return point;
		}

		public int getDistance() {
			return distance;
		}
		
	}
	
	public int findShortestRoute(Point start, Point end){
		Grid<Boolean> visited = new Grid<>();
		visited.set(start, true);
		
		// Start queue
		Queue<QueueNode> queue = new LinkedList<>();
		QueueNode startNode = new QueueNode(start, 0);
		queue.add(startNode);
		
		while(!queue.isEmpty()) {
			QueueNode currentNode = queue.peek();
			Point currentPoint = currentNode.getPoint();
			
			if(currentPoint.equals(end)) {
				return currentNode.getDistance();
			}
			
			queue.remove();
			
			for(Direction direction :  Direction.values()) {
				Point nextPoint = direction.getTranslatedPoint(currentPoint);
				String cellValue = map.get(nextPoint);
				if(!cellValue.equals("#") && visited.get(nextPoint) == null) {
					visited.set(nextPoint, true);
					queue.add(new QueueNode(nextPoint, currentNode.getDistance() + 1));
				}
			}
		}
		
		return -1;
	}
	
	public int getTimeToSpreadOxygen() {
		int time = 0;
		while(this.map.stream().filter(e -> e.getValue().equals(".")).count() > 0) {
			this.spreadOxygen();
			time++;
		}
		return time;
	}
	
	public void spreadOxygen() {
		this.map.stream()
			.filter(e -> e.getValue().equals("."))
			.map(Entry::getKey)
			.filter(this::isAdjacentToOxygen)
			.collect(Collectors.toList())
			.forEach(p -> this.map.set(p, "O"));
	}
	
	boolean isAdjacentToOxygen(Point point) {
		for(Direction direction : Direction.values()) {
			String cellValue = this.map.get(direction.getTranslatedPoint(point));
			if(cellValue.equals("O")) {
				return true;
			}
		}
		return false;
	}

}
