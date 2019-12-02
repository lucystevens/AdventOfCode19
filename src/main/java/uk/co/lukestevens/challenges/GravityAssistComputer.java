package uk.co.lukestevens.challenges;

public class GravityAssistComputer {
	
	public int[] runOpcode(int[] input) {
		for(int i = 0; i<input.length;i+=4) {
			int opcode = input[i];
			if(opcode == 1) {
				input[input[i+3]] = input[input[i+1]] + input[input[i+2]];	
			}
			else if(opcode == 2) {
				input[input[i+3]] = input[input[i+1]] * input[input[i+2]];	
			}
			else if(opcode == 99) {
				return input;
			}
			else {
				return new int[] {-1};
			}
		}
		
		return input;
	}
	
	public int runOpcodeWithInputs(int noun, int verb, int[] opcode) {
		opcode[1] = noun;
		opcode[2] = verb;
		return runOpcode(opcode)[0];
	}
	
	public int findInputsForValue(int output, int[] opcode) {
		for(int noun = 0;noun < 100; noun++) {
			for(int verb = 0; verb < 100; verb++) {
				int[] newOpcode = cloneOpcode(opcode);
				int actualOutput = this.runOpcodeWithInputs(noun, verb, newOpcode);
				if(actualOutput == output) {
					return (100 * noun) + verb;
				}
			}
		}
		return -1;
	}
	
	int[] cloneOpcode(int[] opcode) {
		int[] clone = new int[opcode.length];
		for(int i = 0; i<opcode.length; i++) {
			clone[i] = opcode[i];
		}
		return clone;
	}

}
