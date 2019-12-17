package uk.co.lukestevens.challenges.robot;

import java.awt.Point;

public enum Direction{
	
	UP(0, -1, "^"),
	RIGHT(1, 0, ">"),
	DOWN(0, 1, "v"),
	LEFT(-1, 0, "<");

	
	private int dx;
	private int dy;
	private String icon;
	
	private Direction(int dx, int dy, String icon) {
		this.dx = dx;
		this.dy = dy;
		this.icon = icon;
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

	public String getIcon() {
		return icon;
	}
	
}
