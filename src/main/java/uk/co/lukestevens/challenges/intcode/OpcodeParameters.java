package uk.co.lukestevens.challenges.intcode;

import java.util.ArrayList;
import java.util.List;

public class OpcodeParameters {
	
	private int[] memory;
	private int cursor;
	private List<ParameterMode> modes = new ArrayList<>();
	private int command;

	public OpcodeParameters(String opcode, int[] memory, int cursor) {
		this.memory = memory;
		this.cursor = cursor;
		
		if(opcode.length() > 1 && !opcode.endsWith("99")) {
			this.command = Integer.parseInt(opcode.substring(opcode.length() - 2, opcode.length()));
			for(int i = 3; opcode.length() >= i; i++) {
				ParameterMode mode = opcode.length() >= i && opcode.charAt(opcode.length() - i) == '1'? ParameterMode.IMMEDIATE : ParameterMode.POSITION; 
				modes.add(mode);
			}
		}
		else {
			this.command = Integer.parseInt(opcode);
		}
	}

	public int getCommand() {
		return command;
	}
	
	public int getParameterValue(int index) {
		return this.getParameterMode(index).getValue(memory, memory[cursor + index]);
	}
	
	public ParameterMode getParameterMode(int index) {
		return modes.size() >= index? modes.get(index-1) : ParameterMode.POSITION;
	}

}
