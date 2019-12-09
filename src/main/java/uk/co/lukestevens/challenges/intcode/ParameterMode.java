package uk.co.lukestevens.challenges.intcode;

public enum ParameterMode {
	POSITION(){
		@Override
		public int getValue(IntCodeComputerMemory memory, int parameter) {
			return memory.getValue(parameter);
		}

		@Override
		public int getLocation(IntCodeComputerMemory memory, int index) {
			return memory.getOffsetValue(index);
		}
	},
	IMMEDIATE(){
		@Override
		public int getValue(IntCodeComputerMemory memory, int parameter) {
			return parameter;
		}

		@Override
		public int getLocation(IntCodeComputerMemory memory, int index) {
			throw new RuntimeException("Immediate position should never be used for setting values");
		}
	},
	RELATIVE(){
		@Override
		public int getValue(IntCodeComputerMemory memory, int parameter) {
			return memory.getRelativeValue(parameter);
		}

		@Override
		public int getLocation(IntCodeComputerMemory memory, int index) {
			int offset = memory.getOffsetValue(index);
			return memory.getRelativeBase() + offset;
		}
	};
	
	public static ParameterMode get(int modeNo) {
		switch(modeNo) {
			case 0: return POSITION;
			case 1: return IMMEDIATE;
			case 2: return RELATIVE;
			default: throw new RuntimeException(modeNo + " is not a valid parameter mode");
		}
	}
	
	public abstract int getValue(IntCodeComputerMemory memory, int parameter);
	
	public abstract int getLocation(IntCodeComputerMemory memory, int index);

}
