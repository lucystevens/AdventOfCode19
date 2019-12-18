package uk.co.lukestevens.challenges.tunnels;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class TunnelQueueNode {
	
	List<Point> route;
	Point point;
	
	public TunnelQueueNode(Point point, List<Point> route) {
		this.point = point;
		this.route = new ArrayList<>(route);
		this.route.add(point);
	}

	public Point getPoint() {
		return point;
	}

	public List<Point> getRoute() {
		return route;
	}
	
	public int getDistance() {
		return route.size();
	}
	
}