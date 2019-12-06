package uk.co.lukestevens.challenges.intcode;

public class Opcode {
	private int value;
	private int positionForValue;
	private int cursorIncrement;
	private OpcodeAction action;
	
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

	public int getValue() {
		return value;
	}

	public int getPositionForValue() {
		return positionForValue;
	}

	public int getCursorIncrement() {
		return cursorIncrement;
	}

	public OpcodeAction getAction() {
		return action;
	}
	
	
	
}
