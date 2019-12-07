package uk.co.lukestevens.challenges.intcode;

import java.util.function.Consumer;

public class AmplifierIntCodeComputer {
	
	private final int[] program;
	private IntCodeComputerMemory memory;
	private InputSource<Integer> inputSource;
	private Consumer<Integer> outputCallback;
	private volatile boolean isRunning = false;
		
	public AmplifierIntCodeComputer(int[] program) {
		this(program, System.out::println);
	}
	
	public AmplifierIntCodeComputer(int[] program, Consumer<Integer> outputCallback) {
		super();
		this.program = program;
		this.memory = new IntCodeComputerMemory(program);
		this.inputSource = new InputSource<>();
		this.outputCallback = outputCallback;
	}
	
	public void setOutputCallback(Consumer<Integer> outputCallback) {
		this.outputCallback = outputCallback;
	}

	public IntCodeComputerMemory getMemory() {
		return memory;
	}
	
	public AmplifierIntCodeComputer clone() {
		return new AmplifierIntCodeComputer(program);
	}
	
	public void addInput(int input) {
		this.inputSource.add(input);
	}
	
	public void reset() {
		this.memory = new IntCodeComputerMemory(program);
		this.inputSource = new InputSource<>();
	}
	
	public boolean isRunning() {
		return isRunning;
	}

	public int run() {
		this.isRunning = true;
		while(this.memory.hasNext()) {
			Opcode opcode = this.parseOpcode();
			if(opcode.getAction() == OpcodeAction.WRITE) {
				this.memory.setValue(opcode.getPositionForValue(), opcode.getValue());
			}
			else if(opcode.getAction() == OpcodeAction.OUTPUT) {
				this.outputCallback.accept(opcode.getValue());
			}
			else if(opcode.getAction() == OpcodeAction.HALT) {
				this.isRunning = false;
				return 0;
			}
			else if(opcode.getAction() == OpcodeAction.JUMP) {
				this.memory.jumpCursor(opcode.getPositionForValue());
			}
			this.memory.incrementCursor(opcode.getCursorIncrement());
		}
		this.isRunning = false;
		return 1;
	}
	
	Opcode parseOpcode() {
		String opcode = String.valueOf(this.memory.getValue());
		OpcodeParameters params = new OpcodeParameters(opcode, this.memory);
		
		switch(params.getCommand()) {
			case 1: {
				int p1 = params.getParameterValue(1);
				int p2 = params.getParameterValue(2);
				return Opcode.write(p1 + p2, memory.getOffsetValue(3), 4);
			}
			case 2: {
				int p1 = params.getParameterValue(1);
				int p2 = params.getParameterValue(2);
				return Opcode.write(p1 * p2, memory.getOffsetValue(3), 4);
			}
			case 3: {
				return Opcode.write(inputSource.get(), memory.getOffsetValue(1), 2);
			}
			case 4: {
				int p1 = params.getParameterValue(1);
				return Opcode.output(p1);
			}
			case 5: {
				int p1 = params.getParameterValue(1);
				int p2 = params.getParameterValue(2);
				if(p1 == 0) {
					return Opcode.doNothing(3);
				}
				else {
					return Opcode.jump(p2);
				}
			}
			case 6: {
				int p1 = params.getParameterValue(1);
				int p2 = params.getParameterValue(2);
				if(p1 != 0) {
					return Opcode.doNothing(3);
				}
				else {
					return Opcode.jump(p2);
				}
			}
			case 7: {
				int p1 = params.getParameterValue(1);
				int p2 = params.getParameterValue(2);
				int value = p1 < p2? 1 : 0;
				return Opcode.write(value, memory.getOffsetValue(3), 4);
			}
			case 8: {
				int p1 = params.getParameterValue(1);
				int p2 = params.getParameterValue(2);
				int value = p1 == p2? 1 : 0;
				return Opcode.write(value, memory.getOffsetValue(3), 4);
			}
			case 99: {
				return Opcode.halt();
			}
			default: {
				throw new RuntimeException("Command " + params.getCommand() + " is not recognised");
			}
		}
	}


}
