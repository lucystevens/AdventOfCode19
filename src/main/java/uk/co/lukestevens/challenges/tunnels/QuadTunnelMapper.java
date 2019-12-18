package uk.co.lukestevens.challenges.tunnels;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import uk.co.lukestevens.utils.Grid;
import uk.co.lukestevens.utils.Pair;

public class QuadTunnelMapper extends TunnelMapper{
	
	private final Point centre;
	
	public QuadTunnelMapper(List<String> tunnels) {
		super(tunnels);
		this.centre = this.find("*");
	}
	
	public TunnelRobots findRobotStarts() {
		Map<Quadrant, Point> robots = new HashMap<>();
		for(Quadrant q : Quadrant.values()) {
			robots.put(q, this.findStart(q));
		}
		return new TunnelRobots(robots);
	}

	public List<TunnelKey> findKeys(TunnelRobots robots){
		return this.map.stream()
				.filter(e -> e.getValue().matches("[a-z]"))
				.map(e -> {
					Point p = e.getKey();
					Quadrant q = this.getQuadrantForPoint(p);
					List<String> keys = this.findKeysNeededToReachPoint(robots.getRobotLocationForQuadrant(q), p);
					return new TunnelKey(e.getValue(), p, keys);
				}).collect(Collectors.toList());
	}
	
	@Override
	public int findQuickestRouteToCollectAllKeys() {
		TunnelRobots robots = this.findRobotStarts();
		List<String> keysCollected = new ArrayList<>();
		List<TunnelKey> allKeys = this.findKeys(robots);
		int result = this.findQuickestRouteToCollectRemainingKeys(allKeys, robots, keysCollected);
		this.quickestRouteForKeysCache.entrySet().stream().sorted((e1, e2) -> e1.getValue() - e2.getValue()).forEach(e -> System.out.println(e.getKey() + "\t\t" + e.getValue()));
		return result;
	}
	
	int findQuickestRouteToCollectRemainingKeys(List<TunnelKey> allKeys, TunnelRobots robots, List<String> keysCollected) {
		Pair<String, String> cacheKey = new Pair<>(robots.toString(), keyFromList(keysCollected));
		Integer cacheValue = quickestRouteForKeysCache.get(cacheKey);
		if(cacheValue != null) {
			return cacheValue;
		}
		
		List<TunnelKey> nextKeys = this.getPossibleNextKeys(keysCollected, allKeys, robots);
		if(nextKeys.isEmpty()) {
			return 0;
		}
		
		int shortestRoute = Integer.MAX_VALUE;
		for(TunnelKey nextKey : nextKeys) {
			List<String> nextKeysCollected = new ArrayList<String>(keysCollected);
			nextKeysCollected.add(nextKey.getKey());
			
			Quadrant q = this.getQuadrantForPoint(nextKey.getPoint());
			TunnelRobots nextRobots = robots.deepClone();
			Point robot = nextRobots.getRobotLocationForQuadrant(q);
			
			int distance = this.findShortestRouteLength(robot, nextKey.getPoint());
			
			if(distance < shortestRoute) {
				robot.setLocation(nextKey.getPoint());
				distance += findQuickestRouteToCollectRemainingKeys(allKeys, nextRobots, nextKeysCollected);
			}
			
			if(distance < shortestRoute) {
				shortestRoute = distance;
			}
		}
		
		quickestRouteForKeysCache.put(cacheKey, shortestRoute);
		return shortestRoute;
	}
	
	void print(TunnelRobots robots) {
		Grid<String> toPrint = map.deepClone();
		toPrint.set(39, 39, ".");
		toPrint.set(41, 39, ".");
		toPrint.set(39, 41, ".");
		toPrint.set(41, 41, ".");
		for(Quadrant q : Quadrant.values()) {
			toPrint.set(robots.getRobotLocationForQuadrant(q), "@");
		}
		System.out.println(toPrint);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	List<TunnelKey> getPossibleNextKeys(List<String> keysCollected, List<TunnelKey> keys, TunnelRobots robots){
		return keys.stream()
			.filter(tk -> !robots.hasRobotAtPoint(tk.getPoint()))
			.filter(tk -> !keysCollected.contains(tk.getKey()))
			.filter(tk -> tk.hasPrerequisiteKeys(keysCollected))
			.collect(Collectors.toList());
	}
	
	public Quadrant getQuadrantForPoint(Point p) {
		if(p.getX() < centre.getX() && p.getY() < centre.getY()) {
			return Quadrant.TOP_LEFT;
		}
		else if(p.getX() > centre.getX() && p.getY() < centre.getY()) {
			return Quadrant.TOP_RIGHT;
		}
		else if(p.getX() < centre.getX() && p.getY() > centre.getY()) {
			return Quadrant.BOTTOM_LEFT;
		}
		else if(p.getX() > centre.getX() && p.getY() > centre.getY()) {
			return Quadrant.BOTTOM_RIGHT;
		}
		else {
			throw new RuntimeException("Tried to find quadrant for " + p + " in relation to " + centre);
		}
	}
	
	public Point findStart(Quadrant q) {
		return map.stream()
				.filter(e -> e.getValue().equals("@"))
				.map(Entry::getKey)
				.filter(p -> this.getQuadrantForPoint(p).equals(q))
				.findFirst()
				.get();
	}
}
