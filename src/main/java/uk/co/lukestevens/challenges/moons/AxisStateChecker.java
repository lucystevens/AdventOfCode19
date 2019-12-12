package uk.co.lukestevens.challenges.moons;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import uk.co.lukestevens.utils.Point3D;

public class AxisStateChecker {
	
	private boolean solved = false;
	private final AtomicInteger result = new AtomicInteger();
	private final Set<String> states = new HashSet<>();
	private final Function<Point3D, Integer> axisMethod;
	
	public AxisStateChecker(Function<Point3D, Integer> axisMethod) {
		this.axisMethod = axisMethod;
	}
	
	public boolean hasState(MoonMapper mapper) {
		return this.states.contains(this.getStringKey(mapper));
	}
	
	public void putState(MoonMapper mapper) {
		if(this.hasState(mapper)) {
			this.solved = true;
		}
		else if(!solved) {
			this.states.add(this.getStringKey(mapper));
			result.getAndIncrement();
		}
	}
	
	public boolean isUnsolved() {
		return !solved;
	}
	
	public int getResonance() {
		return result.get();
	}
	
	String getStringKey(MoonMapper mapper) {
		List<String> locations = new ArrayList<>();
		List<String> velocities = new ArrayList<>();
		for(Moon moon : mapper.getMoons()) {
			int location = axisMethod.apply(moon.getLocation());
			int velocity = axisMethod.apply(moon.getVelocity());
			locations.add(String.valueOf(location));
			velocities.add(String.valueOf(velocity));
		}
		return String.join(",", locations) + "|" + String.join(",", velocities);
	}
}
