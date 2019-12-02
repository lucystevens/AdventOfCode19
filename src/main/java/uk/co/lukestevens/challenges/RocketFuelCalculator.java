package uk.co.lukestevens.challenges;

import java.util.List;

public class RocketFuelCalculator {
	
	public int getBaseFuelForModules(List<Integer> input) {
		return input.stream().map(this::getBaseFuelForModule).reduce(0, Integer::sum);
	}
	
	public int getTotalFuelForModules(List<Integer> input) {
		return input.stream().map(this::getTotalFuelForModule).reduce(0, Integer::sum);
	}
	
	int getBaseFuelForModule(int module) {
		return (module/3)-2;
	}
	
	int getTotalFuelForModule(int module) {
		int totalFuel = 0;
		int nextFuel = this.getBaseFuelForModule(module);
		while(nextFuel > 0) {
			totalFuel += nextFuel;
			nextFuel = this.getBaseFuelForModule(nextFuel);
		}
		return totalFuel;
	}

}
