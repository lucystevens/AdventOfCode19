package uk.co.lukestevens.challenges;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class IntCodeComputer {
	
	int[] program;
	Consumer<Integer> outputCallback;
		
	public IntCodeComputer(int[] program) {
		this(program, System.out::println);
	}
	
	public IntCodeComputer(int[] program, Consumer<Integer> outputCallback) {
		super();
		this.program = program;
		this.outputCallback = outputCallback;
	}

	public void run(int input) {
		int cursor = 0;
		while(cursor < this.program.length) {
			Opcode opcode = this.parseOpcode(input, cursor);
			if(opcode.action == OpcodeAction.WRITE) {
				this.program[opcode.positionForValue] = opcode.value;
			}
			else if(opcode.action == OpcodeAction.OUTPUT) {
				this.outputCallback.accept(opcode.value);
			}
			else if(opcode.action == OpcodeAction.HALT) {
				return;
			}
			cursor+=opcode.cursorIncrement;
		}
	}
	
	Opcode parseOpcode(int input, int cursorPosition) {
		String opcode = String.valueOf(this.program[cursorPosition]);
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
		modes.add(null);
		modes.add(null);
		modes.add(null);
		
		if(command == 1) {
			int p1 = this.getValue(modes.get(0), program[cursorPosition + 1]);
			int p2 = this.getValue(modes.get(1), program[cursorPosition + 2]);
			return new Opcode(p1 + p2, program[cursorPosition + 3], 4);
		}
		else if(command == 2) {
			int p1 = this.getValue(modes.get(0), program[cursorPosition + 1]);
			int p2 = this.getValue(modes.get(1), program[cursorPosition + 2]);
			return new Opcode(p1 * p2, program[cursorPosition + 3], 4);
		}
		else if(command == 3) {
			return new Opcode(input, program[cursorPosition + 1], 2);
		}
		else if(command == 4) {
			int p1 = this.getValue(modes.get(0), program[cursorPosition + 1]);
			return new Opcode(p1, -1, 2, OpcodeAction.OUTPUT);
		}
		else if(command == 99) {
			return new Opcode(-1, -1, 2, OpcodeAction.HALT);
		}
		else {
			throw new RuntimeException("Command " + command + " is not recognised");
		}
	}
	
	int getValue(ParameterMode mode, int parameter) {
		if(mode == ParameterMode.IMMEDIATE) {
			return parameter;
		}
		else {
			return this.program[parameter];
		}
	}
	
	public enum OpcodeAction {
		WRITE,
		OUTPUT,
		HALT;
	}
	
	public enum ParameterMode{
		POSITION,
		IMMEDIATE;
	}
	
	public class Opcode {
		int value;
		int positionForValue;
		int cursorIncrement;
		OpcodeAction action;
		
		
		public Opcode(int value, int positionForValue, int cursorIncrement) {
			this(value, positionForValue, cursorIncrement, OpcodeAction.WRITE);
		}
		
		public Opcode(int value, int positionForValue, int cursorIncrement, OpcodeAction action) {
			this.value = value;
			this.positionForValue = positionForValue;
			this.cursorIncrement = cursorIncrement;
			this.action = action;
		}
		
	}

}
