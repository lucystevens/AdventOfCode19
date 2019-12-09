package uk.co.lukestevens.challenges.intcode;

public enum ParameterMode {
	POSITION(){
		@Override
		public Long getValue(IntCodeComputerMemory memory, Long parameter) {
			return memory.getValue(parameter.intValue());
		}

		@Override
		public int getLocation(IntCodeComputerMemory memory, int index) {
			return memory.getOffsetValue(index).intValue();
		}
	},
	IMMEDIATE(){
		@Override
		public Long getValue(IntCodeComputerMemory memory, Long parameter) {
			return parameter;
		}

		@Override
		public int getLocation(IntCodeComputerMemory memory, int index) {
			throw new RuntimeException("Immediate position should never be used for setting values");
		}
	},
	RELATIVE(){
		@Override
		public Long getValue(IntCodeComputerMemory memory, Long parameter) {
			return memory.getRelativeValue(parameter.intValue());
		}

		@Override
		public int getLocation(IntCodeComputerMemory memory, int index) {
			Long offset = memory.getOffsetValue(index);
			return memory.getRelativeBase() + offset.intValue();
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
	
	public abstract Long getValue(IntCodeComputerMemory memory, Long parameter);
	
	public abstract int getLocation(IntCodeComputerMemory memory, int index);

}
