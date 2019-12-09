package uk.co.lukestevens.challenges.intcode;

public class Opcode {
	private Long value;
	private int positionForValue;
	private int cursorIncrement;
	private OpcodeAction action;
	
	public static Opcode write(Long value, int positionForValue, int cursorIncrement) {
		return new Opcode(value, positionForValue, cursorIncrement, OpcodeAction.WRITE);
	}
	
	public static Opcode output(Long value) {
		return new Opcode(value, 0, 2, OpcodeAction.OUTPUT);
	}
	
	public static Opcode doNothing(int cursorIncrement) {
		return new Opcode(0L, 0, cursorIncrement, OpcodeAction.NOTHING);
	}
	
	public static Opcode halt() {
		return new Opcode(0L, 0, 2, OpcodeAction.HALT);
	}
	
	public static Opcode jump(int pointerValue) {
		return new Opcode(0L, pointerValue, 0, OpcodeAction.JUMP);
	}
	
	private Opcode(Long value, int positionForValue, int cursorIncrement, OpcodeAction action) {
		this.value = value;
		this.positionForValue = positionForValue;
		this.cursorIncrement = cursorIncrement;
		this.action = action;
	}

	public Long getValue() {
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
