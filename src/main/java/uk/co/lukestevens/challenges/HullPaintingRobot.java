package uk.co.lukestevens.challenges;

import java.awt.Point;

import uk.co.lukestevens.challenges.intcode.IntCodeComputer;
import uk.co.lukestevens.challenges.intcode.IntCodeComputerFactory;
import uk.co.lukestevens.utils.Grid;
import uk.co.lukestevens.utils.Wrapper;

public class HullPaintingRobot {
	
	private final Grid<String> hull = new Grid<>();
	private final IntCodeComputerFactory factory;
	
	public HullPaintingRobot(Long[] input) {
		this.factory = new IntCodeComputerFactory(input);
	}
	
	public void paintPanel() {
		IntCodeComputer computer = factory.createComputer();
		Wrapper<Boolean> isPaintOutput = new Wrapper<>(true);
		Robot robot = new Robot();
		
		computer.addInput(1L);
		computer.setOutputCallback(out -> {
			if(isPaintOutput.get()) {
				this.hull.set(robot.getX(), robot.getY(), out == 0L? "X" : " ");
				isPaintOutput.set(false);
			}
			else {
				robot.move(out.intValue());
				String value = this.hull.get(robot.getX(), robot.getY());
				long panel = value == null || value.equals("X")? 0L : 1L; 
				computer.addInput(panel);
				isPaintOutput.set(true);
			}
		});
		
		computer.run();
	}
	
	public Grid<String> getHull() {
		return hull;
	}
	
	public long getPanelsPainted() {
		return hull.stream().count();
	}

	public class Robot{
		private Point point;
		private Direction direction;
		
		public Robot() {
			this.point = new Point(0, 0);
			this.direction = Direction.UP;
		}
		
		public void move(int input) {
			this.direction = input == 0? this.direction.left() : this.direction.right();
			this.direction.translatePoint(this.point);
		}
		
		public int getX() {
			return (int) point.getX();
		}
		
		public int getY() {
			return (int) point.getY();
		}
	}
	
	public enum Direction{
		
		UP(0, -1),
		RIGHT(1, 0),
		DOWN(0, 1),
		LEFT(-1, 0);

		
		private int dx;
		private int dy;
		
		private Direction(int dx, int dy) {
			this.dx = dx;
			this.dy = dy;
		}

		public void translatePoint(Point point){
			point.translate(dx, dy);
		}
		
		public Direction left() {
			int leftIndex = (this.ordinal() + 3) % 4;
			return Direction.values()[leftIndex];
		}
		
		public Direction right() {
			int rightIndex = (this.ordinal() + 1) % 4;
			return Direction.values()[rightIndex];
		}
		
	}

}
