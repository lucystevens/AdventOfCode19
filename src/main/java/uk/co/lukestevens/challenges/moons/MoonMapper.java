package uk.co.lukestevens.challenges.moons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import uk.co.lukestevens.utils.Point3D;
import uk.co.lukestevens.utils.Utils;

public class MoonMapper {
	
	private List<Moon> moons;

	public MoonMapper(List<Moon> moons) {
		this.moons = moons;
	}
	
	public List<Moon> getMoons(){
		return this.moons;
	}
	
	public long findAxisResonance(Function<Point3D, Integer> axisMethod) {
		Map<String, Boolean> states = new HashMap<>();
		long result = 0;
		while(states.get(this.getStringKeyForAxis(axisMethod)) == null) {
			states.put(this.getStringKeyForAxis(axisMethod), true);
			this.simulateTimeStep();
			result++;
		}
		return result;
	}
	
	String getStringKeyForAxis(Function<Point3D, Integer> axisMethod) {
		List<String> locations = new ArrayList<>();
		List<String> velocities = new ArrayList<>();
		for(Moon moon : this.moons) {
			int location = axisMethod.apply(moon.getLocation());
			int velocity = axisMethod.apply(moon.getVelocity());
			locations.add(String.valueOf(location));
			velocities.add(String.valueOf(velocity));
		}
		return String.join(",", locations) + "|" + String.join(",", velocities);
	}
	
	public long findStepsToMatchPreviousPoint() {
		List<AxisStateChecker> checkers = Arrays.asList(
			new AxisStateChecker(Point3D::getX),
			new AxisStateChecker(Point3D::getY),
			new AxisStateChecker(Point3D::getZ)
		);
				
		
		while(checkers.stream().filter(c -> c.isUnsolved()).count() > 0) {
			checkers.stream().filter(c -> c.isUnsolved()).forEach(c -> {
				c.putState(this);
			});
			this.simulateTimeStep();
		}
		
		System.out.println("X: " + checkers.get(0).getResonance() + ", Y: " + checkers.get(1).getResonance() + ", Z: " + checkers.get(2).getResonance());
		return Utils.getLowestCommonMultiple(checkers.get(0).getResonance(), checkers.get(1).getResonance(), checkers.get(2).getResonance());
	}
	
	public void simulateTimeSteps(int steps) {
		for(int i = 0; i<steps; i++) {
			this.simulateTimeStep();
		}
	}
	
	public int getTotalEnergy() {
		return moons.stream().mapToInt(Moon::getTotalEnergy).sum();
	}
	

	void simulateTimeStep(){
		Utils.forEachPair(moons, (m1, m2) -> {
			int xChange = this.getVelocityChange(m1.getLocation().getX(), m2.getLocation().getX());
			int yChange = this.getVelocityChange(m1.getLocation().getY(), m2.getLocation().getY());
			int zChange = this.getVelocityChange(m1.getLocation().getZ(), m2.getLocation().getZ());
			
			m1.getVelocity().translate(xChange, yChange, zChange);
			m2.getVelocity().translate(xChange*-1, yChange*-1, zChange*-1);
		});
		
		for(Moon moon : moons) {
			Point3D velocity = moon.getVelocity();
			moon.getLocation().translate(velocity.getX(), velocity.getY(), velocity.getZ());
		}
	}
	
	int getVelocityChange(int x1, int x2) {
		return x1 > x2? -1 :
			   x1 < x2?  1 : 0;
	}
	
	@Override
	public String toString() {
		return moons.stream().map(Moon::toString).collect(Collectors.joining("\n"));
	}
}
