package uk.co.lukestevens.challenges.oxygen;

import java.awt.Point;

public class RepairDroid {
	
	Point point = new Point();
	Direction lastMove;
	
	
	public RepairDroid() { }
	
	public RepairDroid(Point point) {
		this(new Point(point), null);
	}
	
	private RepairDroid(Point point, Direction lastMove) {
		this.point = point;
		this.lastMove = lastMove;
	}

	public Point getNextPoint(Direction direction) {
		return direction.getTranslatedPoint(point);
	}
	
	public long move(Direction direction) {
		this.lastMove = direction;
		direction.translatePoint(point);
		return direction.getValue();
	}

	public Point getCurrentPoint() {
		return point;
	}
	
	public void reverse() {
		lastMove.reverseTranslatePoint(point);
	}
	
	public RepairDroid deepClone() {
		return new RepairDroid(new Point(point), lastMove);
	}
	
}
