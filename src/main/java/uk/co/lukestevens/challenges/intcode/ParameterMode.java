package uk.co.lukestevens.challenges.intcode;

import java.util.function.BiFunction;

public enum ParameterMode {
	POSITION((memory, param) -> memory.getValue(param)),
	IMMEDIATE((memory, param) -> param);
	
	private BiFunction<IntCodeComputerMemory, Integer, Integer> action;

	private ParameterMode(BiFunction<IntCodeComputerMemory, Integer, Integer> action) {
		this.action = action;
	}
	
	public int getValue(IntCodeComputerMemory memory, int parameter) {
		return this.action.apply(memory, parameter);
	}

}
