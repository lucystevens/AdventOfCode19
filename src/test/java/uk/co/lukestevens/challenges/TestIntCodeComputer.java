package uk.co.lukestevens.challenges;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import uk.co.lukestevens.challenges.IntCodeComputer.Opcode;
import uk.co.lukestevens.challenges.IntCodeComputer.OpcodeAction;
import uk.co.lukestevens.utils.InputFileReader;

public class TestIntCodeComputer {
	
	@Test
	public void testParseOpcode() {
		int[] input = {1002,4,3,4,33,99};
		IntCodeComputer comp = new IntCodeComputer(input);
		
		Opcode opcode = comp.parseOpcode(1, 0);
		assertEquals(99, opcode.value);
		assertEquals(4, opcode.positionForValue);
		assertEquals(4, opcode.cursorIncrement);
		assertEquals(OpcodeAction.WRITE, opcode.action);
	}
	
	@Test
	public void testTask1() throws IOException {
		List<Integer> outputs = new ArrayList<>();
		InputFileReader reader = new InputFileReader("Day5");
		int[] input = reader.readFileAsArrayOfIntegers();
		
		IntCodeComputer computer = new IntCodeComputer(input, outputs::add);
		computer.run(1);
		
		assertEquals(0, outputs.get(0));
		assertEquals(0, outputs.get(1));
		assertEquals(0, outputs.get(2));
		assertEquals(0, outputs.get(3));
		assertEquals(0, outputs.get(4));
		assertEquals(0, outputs.get(5));
		assertEquals(0, outputs.get(6));
		assertEquals(0, outputs.get(7));
		assertEquals(0, outputs.get(8));
		assertEquals(12234644, outputs.get(9));
	}

}
