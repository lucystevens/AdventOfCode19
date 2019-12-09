package uk.co.lukestevens.challenges.intcode;

public class IntCodeComputerFactory {
	
	private final Long[] program;
		
	public IntCodeComputerFactory(Long[] program) {
		this.program = program;
	}
	
	public IntCodeComputerFactory(int[] program) {
		this.program = new Long[program.length];
		for(int i = 0; i<program.length; i++) {
			this.program[i] = Long.valueOf(program[i]);
		}
	}
	
	public IntCodeComputer createComputer() {
		return new IntCodeComputer(program);
	}

}
