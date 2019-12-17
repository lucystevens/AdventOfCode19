package uk.co.lukestevens.challenges.oxygen;

import java.awt.Point;

public enum Direction {
	
	NORTH(0, -1),
	SOUTH(0, 1),
	WEST(-1, 0),
	EAST(1, 0);
	
	private int dx;
	private int dy;
	
	private Direction(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public long getValue() {
		return this.ordinal() + 1;
	}
	
	public void translatePoint(Point point) {
		point.translate(dx, dy);
	}
	
	public void reverseTranslatePoint(Point point) {
		point.translate(-1*dx, -1*dy);
	}
	
	public Point getTranslatedPoint(Point point) {
		Point newPoint = new Point(point);
		this.translatePoint(newPoint);
		return newPoint;
	}
	
	public Direction opposite() {
		switch(this) {
			case NORTH : return SOUTH;
			case EAST : return WEST;
			case SOUTH : return NORTH;
			case WEST : return EAST;
			default: return null;
		}
	}
	
}
