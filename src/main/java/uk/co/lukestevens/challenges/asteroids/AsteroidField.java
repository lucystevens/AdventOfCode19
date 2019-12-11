package uk.co.lukestevens.challenges.asteroids;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import uk.co.lukestevens.utils.Grid;

public class AsteroidField {
	
	private final Grid<Asteroid> asteroidField = new Grid<>();
	
	public AsteroidField(List<String> map) {
		for(int y = 0; y < map.size(); y++) {
			String row = map.get(y);
			for(int x = 0; x < row.length(); x++) {
				char c = row.charAt(x);
				Asteroid ast = new Asteroid(x, y, c == '#');
				this.asteroidField.set(x, y, ast);
			}
		}
	}
	
	public void print() {
		System.out.println(asteroidField.toString());
	}
	
	public void mapPossibleMonitoringStations() {
		List<Asteroid> asteroids = this.getAsteroids();
		
		for(int start = 0; start < asteroids.size(); start++) {
			for(int end = start+1; end < asteroids.size(); end++) {
				Asteroid startAst = asteroids.get(start);
				Asteroid endAst = asteroids.get(end);
				if(this.hasLineOfSight(startAst, endAst)) {
					startAst.incrementAsteroidsInSight();
					endAst.incrementAsteroidsInSight();
				}
			}
		}
	}
	
	public List<Asteroid> getAsteroids(){
		return asteroidField.stream()
				.map(Entry::getValue)
				.filter(Asteroid::isAsteroid)
				.collect(Collectors.toList());
	}
	
	public boolean hasLineOfSight(Asteroid start, Asteroid end){
		List<Point> blockers = start.getPointsBetweenAsteroids(end);
		for(Point point : blockers) {
			Asteroid asteroid = this.asteroidField.get(point);
			if(asteroid.isAsteroid() && !asteroid.isBlasted()) {
				return false;
			}
		}
		return true;
	}
	
	public Asteroid getBestMonitoringStationLocation() {
		List<Asteroid> asteroids = this.getAsteroids();
		Collections.sort(asteroids, (a1, a2) -> a2.getAsteroidsInSight() - a1.getAsteroidsInSight());
		return asteroids.get(0);
	}
	
	public List<Asteroid> blastAsteroids(Asteroid laser){
		laser.blast();
		
		List<Asteroid> blastOrder = new ArrayList<>();
		List<Asteroid> asteroids = this.getAsteroids();
		Collections.sort(asteroids, (a1, a2) -> (int)((laser.getAngleToLocation(a1)-laser.getAngleToLocation(a2))*100.0));
		
		// Keep looping through while there are still unblasted asteroids
		while(asteroids.stream().filter(a -> !a.isBlasted()).count() > 0) {
			double lastAngleBlasted = -1;
			
			for(Asteroid asteroid : asteroids) {
				double angle = laser.getAngleToLocation(asteroid);
				if(!asteroid.isBlasted() && angle != lastAngleBlasted) {
					Asteroid toBlast = this.getNextToBlast(laser, asteroid);
					toBlast.blast();
					blastOrder.add(toBlast);
					lastAngleBlasted = angle;
				}
			}
			
		}
		return blastOrder;
	}
	
	Asteroid getNextToBlast(Asteroid laser, Asteroid target) {
		List<Point> blockers = laser.getPointsBetweenAsteroids(target);
		for(Point point : blockers) {
			Asteroid asteroid = this.asteroidField.get(point);
			if(asteroid.isAsteroid() && !asteroid.isBlasted()) {
				return asteroid;
			}
		}
		return target;
	}

}
