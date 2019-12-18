package uk.co.lukestevens.challenges.tunnels;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import uk.co.lukestevens.challenges.oxygen.Direction;
import uk.co.lukestevens.utils.Grid;
import uk.co.lukestevens.utils.Pair;

public abstract class TunnelMapper {
	
	final Grid<String> map = new Grid<>();
	
	final Map<Pair<Point, Point>, Integer> shortestRouteCache = new HashMap<>();
	final Map<Pair<String, String>, Integer> quickestRouteForKeysCache = new HashMap<>();
	
	TunnelMapper(List<String> tunnels) {
		for(int y = 0; y<tunnels.size(); y++) {
			String row = tunnels.get(y);
			for(int x = 0; x < row.length(); x++) {
				map.set(x, y, String.valueOf(row.charAt(x)));
			}
		}
	}
	
	public List<Point> findShortestRoute(Point start, Point end){
		Grid<Boolean> visited = new Grid<>();
		visited.set(start, true);
		
		// Start queue
		Queue<TunnelQueueNode> queue = new LinkedList<>();
		TunnelQueueNode startNode = new TunnelQueueNode(start, new ArrayList<>());
		queue.add(startNode);
		
		while(!queue.isEmpty()) {
			TunnelQueueNode currentNode = queue.peek();
			Point currentPoint = currentNode.getPoint();
			
			if(currentPoint.equals(end)) {
				return currentNode.getRoute();
			}
			
			queue.remove();
			
			for(Direction direction :  Direction.values()) {
				Point nextPoint = direction.getTranslatedPoint(currentPoint);
				String cellValue = map.get(nextPoint);
				if(!cellValue.equals("#") && visited.get(nextPoint) == null) {
					visited.set(nextPoint, true);
					queue.add(new TunnelQueueNode(nextPoint, currentNode.getRoute()));
				}
			}
		}
		
		return null;
	}
	
	public int findShortestRouteLength(Point start, Point end){
		Pair<Point, Point> cacheKey = new Pair<>(start, end);
		Integer cachedValue = shortestRouteCache.get(cacheKey);
		if(cachedValue != null) {
			return cachedValue;
		}
		
		List<Point> route = this.findShortestRoute(start, end);
		int result = route == null? -1 : route.size()-1;
		shortestRouteCache.put(cacheKey, result);
		return result;
	}
	
	public List<String> findKeysNeededToReachPoint(Point start, Point end){
		return this.findShortestRoute(start, end)
				.stream()
				.map(map::get)
				.filter(v -> v.matches("[A-Z]"))
				.map(String::toLowerCase)
				.collect(Collectors.toList());
	}
	
	public abstract int findQuickestRouteToCollectAllKeys();
	
	// It doesn't matter what order we collected the keys in
	String keyFromList(List<String> list) {
		return list.stream().sorted().collect(Collectors.joining());
	}
	
	public Point findKey(String key) {
		return this.find(key.toLowerCase());
	}
	
	public Point findDoor(String door) {
		return this.find(door.toUpperCase());
	}
	
	Point find(String value) {
		return map.stream()
				.filter(e -> e.getValue().equals(value))
				.findFirst()
				.get()
				.getKey();
	}
	
	public Grid<String> getMap(){
		return map;
	}

}
