package uk.co.lukestevens.challenges.tunnels;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TunnelRobots {
	
	private final Map<Quadrant, Point> robots;

	public TunnelRobots(Map<Quadrant, Point> robots) {
		this.robots = robots;
	}
	
	public TunnelRobots deepClone() {
		Map<Quadrant, Point> clonedRobots = new HashMap<>();
		for(Quadrant q : Quadrant.values()) {
			clonedRobots.put(q, new Point(robots.get(q)));
		}
		return new TunnelRobots(clonedRobots);
	}
	
	public Point getRobotLocationForQuadrant(Quadrant q) {
		return this.robots.get(q);
	}
	
	public boolean hasRobotAtPoint(Point p) {
		for(Quadrant q : Quadrant.values()) {
			if(this.robots.get(q).equals(p)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return robots.entrySet()
			.stream()
			.map(e -> e.getKey() + ":[" + e.getValue().getX() + "," + e.getValue().getY() + "]")
			.collect(Collectors.joining(","));
	}

}
