package uk.co.lukestevens.challenges.intcode;

public class IntCodeComputerFactory {
	
	private final int[] program;
		
	public IntCodeComputerFactory(int[] program) {
		this.program = program;
	}
	
	public IntCodeComputer createComputer() {
		return new IntCodeComputer(program);
	}

}
