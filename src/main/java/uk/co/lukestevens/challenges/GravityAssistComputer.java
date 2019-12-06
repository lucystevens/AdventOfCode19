package uk.co.lukestevens.challenges;

import uk.co.lukestevens.challenges.intcode.IntCodeComputer;

public class GravityAssistComputer {
	
	private final IntCodeComputer computer;
			
	public GravityAssistComputer(int[] input) {
		this.computer = new IntCodeComputer(input);
	}
	
	public int[] run() {
		this.computer.run(0);
		return this.computer.getMemory().getBuffer();
	}
	
	public int run(int noun, int verb) {
		return this.computer.run(noun, verb);
	}
	
	public int findInputsForValue(int output) {
		for(int noun = 0;noun < 100; noun++) {
			for(int verb = 0; verb < 100; verb++) {
				int actualOutput = this.run(noun, verb);
				if(actualOutput == output) {
					return (100 * noun) + verb;
				}
			}
		}
		return -1;
	}

}
