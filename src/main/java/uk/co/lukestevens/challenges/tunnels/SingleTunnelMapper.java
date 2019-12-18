package uk.co.lukestevens.challenges.tunnels;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import uk.co.lukestevens.utils.Pair;

public class SingleTunnelMapper extends TunnelMapper{
	
	public SingleTunnelMapper(List<String> tunnels) {
		super(tunnels);
	}

	public List<TunnelKey> findKeys(){
		Point start = this.findStart();
		return this.map.stream()
				.filter(e -> e.getValue().matches("[a-z]"))
				.map(e -> 
					new TunnelKey(e.getValue(), e.getKey(), this.findKeysNeededToReachPoint(start, e.getKey()))
				).collect(Collectors.toList());
	}
	
	@Override
	public int findQuickestRouteToCollectAllKeys() {
		List<String> keysCollected = new ArrayList<>();
		TunnelKey start = new TunnelKey("~", this.findStart(), new ArrayList<>());
		List<TunnelKey> allKeys = this.findKeys();
		return this.findQuickestRouteToCollectRemainingKeys(allKeys, start, keysCollected);
	}
	
	int findQuickestRouteToCollectRemainingKeys(List<TunnelKey> allKeys, TunnelKey currentKey, List<String> keysCollected) {
		Pair<String, String> cacheKey = new Pair<>(currentKey.getKey(), keyFromList(keysCollected));
		Integer cacheValue = quickestRouteForKeysCache.get(cacheKey);
		if(cacheValue != null) {
			return cacheValue;
		}
		
		List<TunnelKey> nextKeys = this.getPossibleNextKeys(keysCollected, allKeys, currentKey);
		if(nextKeys.isEmpty()) {
			return 0;
		}
		
		int shortestRoute = Integer.MAX_VALUE;
		for(TunnelKey nextKey : nextKeys) {
			List<String> nextKeysCollected = new ArrayList<String>(keysCollected);
			nextKeysCollected.add(nextKey.getKey());
			
			int distance = this.findShortestRouteLength(currentKey.getPoint(), nextKey.getPoint());
			
			if(distance < shortestRoute) {
				distance += findQuickestRouteToCollectRemainingKeys(allKeys, nextKey, nextKeysCollected);
				if(distance < shortestRoute) {
					shortestRoute = distance;
				}
			}
		}
		
		quickestRouteForKeysCache.put(cacheKey, shortestRoute);
		return shortestRoute;
	}
	
	List<TunnelKey> getPossibleNextKeys(List<String> keysCollected, List<TunnelKey> keys, TunnelKey current){
		return keys.stream()
			.filter(tk -> !tk.equals(current))
			.filter(tk -> !keysCollected.contains(tk.getKey()))
			.filter(tk -> tk.hasPrerequisiteKeys(keysCollected))
			.collect(Collectors.toList());
	}
	
	public Point findStart() {
		return this.find("@");
	}

}
