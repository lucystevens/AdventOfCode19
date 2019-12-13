package uk.co.lukestevens.challenges.asteroids;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import uk.co.lukestevens.utils.Utils;

public class Asteroid {
	
	private Point point;
	private boolean isAsteroid;
	private int asteroidsInSight = 0;
	private boolean isBlasted = false;
	
	public Asteroid(int x, int y, boolean isAsteroid) {
		this.point = new Point(x, y);
		this.isAsteroid = isAsteroid;
	}
	
	public Point getPoint() {
		return point;
	}
	
	public boolean isAsteroid() {
		return isAsteroid;
	}
	
	public void incrementAsteroidsInSight() {
		this.asteroidsInSight++;
	}

	public int getAsteroidsInSight() {
		return asteroidsInSight;
	}
	
	public void blast() {
		this.isBlasted = true;
	}
	
	public boolean isBlasted() {
		return isBlasted;
	}

	public List<Point> getPointsBetweenAsteroids(Asteroid ast){
		Point end = ast.getPoint();
		List<Point> points = new ArrayList<>();
		int xDiff = (int) Math.abs(this.point.getX() - end.getX());
		int yDiff = (int) Math.abs(this.point.getY() - end.getY());
		int divisor = (int) Utils.getGreatestCommonDivisor(xDiff, yDiff);
		
		int yDirection = this.point.getY() - end.getY() > 0? -1 : 1;
		int xDirection = this.point.getX() - end.getX() > 0? -1 : 1;
		
		int baseX = (xDiff/divisor) * xDirection;
		int baseY = (yDiff/divisor) * yDirection;
		
		for(int i = 1; i<divisor; i++) {
			Point newPoint = new Point(this.point);
			newPoint.translate(baseX*i, baseY*i);
			points.add(newPoint);
		}
		
		return points;
	}
	
	public double getAngleToLocation(Asteroid ast) {
		int xDiff = (int) Math.abs(this.point.getX() - ast.getPoint().getX());
		int yDiff = (int) Math.abs(this.point.getY() - ast.getPoint().getY());
		
		int yDirection = (int) (this.point.getY() - ast.getPoint().getY());
		int xDirection = (int) (this.point.getX() - ast.getPoint().getX());
		
		int baseAngle = 0;
		int opposite = 0;
		int adjacent = 0;
		if(xDirection < 0 && yDirection > 0) {
			baseAngle = 0;
			opposite = xDiff;
			adjacent = yDiff;
		}
		else if(xDirection < 0 && yDirection < 0) {
			baseAngle = 90;
			opposite = yDiff;
			adjacent = xDiff;
		}
		else if(xDirection > 0 && yDirection < 0) {
			baseAngle = 180;
			opposite = xDiff;
			adjacent = yDiff;
		}
		else if(xDirection > 0 && yDirection > 0) {
			baseAngle = 270;
			opposite = yDiff;
			adjacent = xDiff;
		}
		else if(xDirection == 0 && yDirection > 0) {
			return 0.0;
		}
		else if(xDirection == 0 && yDirection < 0) {
			return 180.0;
		}
		else if(yDirection == 0 && xDirection > 0) {
			return 90.0;
		}
		else if(yDirection == 0 && xDirection < 0) {
			return 270.0;
		}

		return Math.toDegrees(Math.atan2(opposite, adjacent)) + baseAngle;
	}
	
	@Override
	public String toString() {
		return this.isAsteroid? String.valueOf(asteroidsInSight) : ".";
	}

}
