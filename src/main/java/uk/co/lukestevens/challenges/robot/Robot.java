package uk.co.lukestevens.challenges.robot;

import java.awt.Point;

import uk.co.lukestevens.challenges.robot.Direction;

public class Robot{
	private Point point;
	private Direction direction;
	
	public Robot() {
		this(new Point(0, 0), Direction.UP);
	}
	
	public Robot(Point point, Direction direction) {
		this.point = point;
		this.direction = direction;
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
	
	public void turnLeft() {
		this.direction = this.direction.left();
	}
	
	public void turnRight() {
		this.direction = this.direction.right();
	}
	
	public void move() {
		this.direction.translatePoint(point);
	}
	
	public void moveForwards(int amount) {
		for(int i =0; i<amount; i++) {
			this.direction.translatePoint(point);
		}
	}
	
	public Point nextPosition() {
		Point next = new Point(this.point);
		this.direction.translatePoint(next);
		return next;
	}
	
	public String getIcon() {
		return direction.getIcon();
	}
	
	public Robot deepClone() {
		return new Robot(new Point(point), direction);
	}
	
}
