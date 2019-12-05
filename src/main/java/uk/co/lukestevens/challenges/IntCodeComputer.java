package uk.co.lukestevens.challenges;

import java.util.ArrayList;
import java.util.List;
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
			if(opcode.action == OpcodeAction.WRITE) {
				this.memory[opcode.positionForValue] = opcode.value;
			}
			else if(opcode.action == OpcodeAction.OUTPUT) {
				this.outputCallback.accept(opcode.value);
			}
			else if(opcode.action == OpcodeAction.HALT) {
				return;
			}
			else if(opcode.action == OpcodeAction.JUMP) {
				cursor = opcode.positionForValue;
			}
			cursor+=opcode.cursorIncrement;
		}
	}
	
	Opcode parseOpcode(int input, int cursorPosition) {
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
		modes.add(null);
		modes.add(null);
		modes.add(null);
		
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
	}
	
	int getValue(ParameterMode mode, int parameter) {
		if(mode == ParameterMode.IMMEDIATE) {
			return parameter;
		}
		else {
			return this.memory[parameter];
		}
	}
	
	int[] cloneProgram() {
		int[] clone = new int[program.length];
		for(int i = 0; i<program.length; i++) {
			clone[i] = program[i];
		}
		return clone;
	}
	
	public enum OpcodeAction {
		WRITE,
		OUTPUT,
		HALT,
		NOTHING,
		JUMP;
	}
	
	public enum ParameterMode{
		POSITION,
		IMMEDIATE;
	}
	
	public static class Opcode {
		int value;
		int positionForValue;
		int cursorIncrement;
		OpcodeAction action;
		
		public static Opcode write(int value, int positionForValue, int cursorIncrement) {
			return new Opcode(value, positionForValue, cursorIncrement, OpcodeAction.WRITE);
		}
		
		public static Opcode output(int value) {
			return new Opcode(value, 0, 2, OpcodeAction.OUTPUT);
		}
		
		public static Opcode doNothing(int cursorIncrement) {
			return new Opcode(0, 0, cursorIncrement, OpcodeAction.NOTHING);
		}
		
		public static Opcode halt() {
			return new Opcode(0, 0, 2, OpcodeAction.HALT);
		}
		
		public static Opcode jump(int pointerValue) {
			return new Opcode(pointerValue, pointerValue, 0, OpcodeAction.JUMP);
		}
		
		private Opcode(int value, int positionForValue, int cursorIncrement, OpcodeAction action) {
			this.value = value;
			this.positionForValue = positionForValue;
			this.cursorIncrement = cursorIncrement;
			this.action = action;
		}
		
	}

}
