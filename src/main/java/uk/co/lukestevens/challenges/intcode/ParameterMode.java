package uk.co.lukestevens.challenges.intcode;

import java.util.function.BiFunction;

public enum ParameterMode {
	POSITION((memory, param) -> memory[param]),
	IMMEDIATE((memory, param) -> param);
	
	private BiFunction<int[], Integer, Integer> action;

	private ParameterMode(BiFunction<int[], Integer, Integer> action) {
		this.action = action;
	}
	
	public int getValue(int[] memory, int parameter) {
		return this.action.apply(memory, parameter);
	}

}
