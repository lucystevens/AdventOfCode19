package uk.co.lukestevens.challenges;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import uk.co.lukestevens.challenges.intcode.IntCodeComputer;
import uk.co.lukestevens.challenges.intcode.IntCodeComputerFactory;
import uk.co.lukestevens.utils.Wrapper;

public class TestCompleteIntCodeComputer {
	
	@Test
	public void testProgramLongerThanMemory() {
		List<Integer> outputs = new ArrayList<>();
		int[] input = {109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99};
		IntCodeComputerFactory factory = new IntCodeComputerFactory(input);
		IntCodeComputer computer = factory.createComputer();
		computer.setOutputCallback(outputs::add);
		computer.run();
		
		assertEquals(input.length, outputs.size());
		for(int i = 0; i<input.length; i++) {
			assertEquals(input[i], outputs.get(i));
		}
		
	}
	
	@Test
	public void testOutputLargeNumber() {
		Wrapper<Integer> output = new Wrapper<Integer>();
		int[] input = {1102,34915192,34915192,7,4,7,99,0};
		IntCodeComputerFactory factory = new IntCodeComputerFactory(input);
		IntCodeComputer computer = factory.createComputer();
		computer.setOutputCallback(output::set);
		computer.run();
		
		String outputAsString = String.valueOf(output.get());
		assertEquals(16, outputAsString.length());
	}
	
	@Test
	public void testLargeNumberInProgram() {
		Wrapper<Integer> output = new Wrapper<Integer>();
		long[] input = {104,1125899906842624L,99};
		//IntCodeComputerFactory factory = new IntCodeComputerFactory(input);
		//IntCodeComputer computer = factory.createComputer();
		//computer.setOutputCallback(output::set);
		//computer.run();
		
		//assertEquals(1125899906842624L, output.get());
	}

}
