package uk.co.lukestevens.challenges.intcode;

import java.util.function.Consumer;

public class IntCodeComputer {
	
	final int[] program;
	int[] memory;
	Consumer<Integer> outputCallback;
		
	public IntCodeComputer(int[] program) {
		this(program, System.out::println);
	}
	
	public IntCodeComputer(int[] program, Consumer<Integer> outputCallback) {
		super();
		this.program = program;
		this.memory = this.cloneProgram();
		this.outputCallback = outputCallback;
	}

	public void run(int input) {
		this.memory = this.cloneProgram();
		int cursor = 0;
		while(cursor < this.memory.length) {
			Opcode opcode = this.parseOpcode(input, cursor);
			if(opcode.getAction() == OpcodeAction.WRITE) {
				this.memory[opcode.getPositionForValue()] = opcode.getValue();
			}
			else if(opcode.getAction() == OpcodeAction.OUTPUT) {
				this.outputCallback.accept(opcode.getValue());
			}
			else if(opcode.getAction() == OpcodeAction.HALT) {
				return;
			}
			else if(opcode.getAction() == OpcodeAction.JUMP) {
				cursor = opcode.getPositionForValue();
			}
			cursor+=opcode.getCursorIncrement();
		}
	}
	
	Opcode parseOpcode(int input, int cursorPosition) {
		String opcode = String.valueOf(this.memory[cursorPosition]);
		OpcodeParameters params = new OpcodeParameters(opcode, this.memory, cursorPosition);
		
		if(params.getCommand() == 1) {
			int p1 = params.getParameterValue(1);
			int p2 = params.getParameterValue(2);
			return Opcode.write(p1 + p2, memory[cursorPosition + 3], 4);
		}
		else if(params.getCommand() == 2) {
			int p1 = params.getParameterValue(1);
			int p2 = params.getParameterValue(2);
			return Opcode.write(p1 * p2, memory[cursorPosition + 3], 4);
		}
		else if(params.getCommand() == 3) {
			return Opcode.write(input, memory[cursorPosition + 1], 2);
		}
		else if(params.getCommand() == 4) {
			int p1 = params.getParameterValue(1);
			return Opcode.output(p1);
		}
		else if(params.getCommand() == 5) {
			int p1 = params.getParameterValue(1);
			int p2 = params.getParameterValue(2);
			if(p1 == 0) {
				return Opcode.doNothing(3);
			}
			else {
				return Opcode.jump(p2);
			}
		}
		else if(params.getCommand() == 6) {
			int p1 = params.getParameterValue(1);
			int p2 = params.getParameterValue(2);
			if(p1 != 0) {
				return Opcode.doNothing(3);
			}
			else {
				return Opcode.jump(p2);
			}
		}
		else if(params.getCommand() == 7) {
			int p1 = params.getParameterValue(1);
			int p2 = params.getParameterValue(2);
			int value = p1 < p2? 1 : 0;
			return Opcode.write(value, memory[cursorPosition + 3], 4);
		}
		else if(params.getCommand() == 8) {
			int p1 = params.getParameterValue(1);
			int p2 = params.getParameterValue(2);
			int value = p1 == p2? 1 : 0;
			return Opcode.write(value, memory[cursorPosition + 3], 4);
		}
		else if(params.getCommand() == 99) {
			return Opcode.halt();
		}
		else {
			throw new RuntimeException("Command " + params.getCommand() + " is not recognised");
		}
	}
	
	/*Opcode parseOpcode(int input, int cursorPosition) {
		String opcode = String.valueOf(this.memory[cursorPosition]);
		int command = 0;
		List<ParameterMode> modes = new ArrayList<>();
		if(opcode.length() > 1 && !opcode.endsWith("99")) {
			command = Integer.parseInt(opcode.substring(opcode.length() - 2, opcode.length()));
			for(int i = 3; opcode.length() >= i; i++) {
				ParameterMode mode = opcode.length() >= i && opcode.charAt(opcode.length() - i) == '1'? ParameterMode.IMMEDIATE : ParameterMode.POSITION; 
				modes.add(mode);
			}
		}
		else {
			command = Integer.parseInt(opcode);
		}

		
		// Avoid array out of bounds
		modes.add(ParameterMode.POSITION);
		modes.add(ParameterMode.POSITION);
		modes.add(ParameterMode.POSITION);
		
		if(command == 1) {
			int p1 = this.getValue(modes.get(0), memory[cursorPosition + 1]);
			int p2 = this.getValue(modes.get(1), memory[cursorPosition + 2]);
			return Opcode.write(p1 + p2, memory[cursorPosition + 3], 4);
		}
		else if(command == 2) {
			int p1 = this.getValue(modes.get(0), memory[cursorPosition + 1]);
			int p2 = this.getValue(modes.get(1), memory[cursorPosition + 2]);
			return Opcode.write(p1 * p2, memory[cursorPosition + 3], 4);
		}
		else if(command == 3) {
			return Opcode.write(input, memory[cursorPosition + 1], 2);
		}
		else if(command == 4) {
			int p1 = this.getValue(modes.get(0), memory[cursorPosition + 1]);
			return Opcode.output(p1);
		}
		else if(command == 5) {
			int p1 = this.getValue(modes.get(0), memory[cursorPosition + 1]);
			int p2 = this.getValue(modes.get(1), memory[cursorPosition + 2]);
			if(p1 == 0) {
				return Opcode.doNothing(3);
			}
			else {
				return Opcode.jump(p2);
			}
		}
		else if(command == 6) {
			int p1 = this.getValue(modes.get(0), memory[cursorPosition + 1]);
			int p2 = this.getValue(modes.get(1), memory[cursorPosition + 2]);
			if(p1 != 0) {
				return Opcode.doNothing(3);
			}
			else {
				return Opcode.jump(p2);
			}
		}
		else if(command == 7) {
			int p1 = this.getValue(modes.get(0), memory[cursorPosition + 1]);
			int p2 = this.getValue(modes.get(1), memory[cursorPosition + 2]);
			int value = p1 < p2? 1 : 0;
			return Opcode.write(value, memory[cursorPosition + 3], 4);
		}
		else if(command == 8) {
			int p1 = this.getValue(modes.get(0), memory[cursorPosition + 1]);
			int p2 = this.getValue(modes.get(1), memory[cursorPosition + 2]);
			int value = p1 == p2? 1 : 0;
			return Opcode.write(value, memory[cursorPosition + 3], 4);
		}
		else if(command == 99) {
			return Opcode.halt();
		}
		else {
			throw new RuntimeException("Command " + command + " is not recognised");
		}
	}*/
	
	int[] cloneProgram() {
		int[] clone = new int[program.length];
		for(int i = 0; i<program.length; i++) {
			clone[i] = program[i];
		}
		return clone;
	}
	


}
