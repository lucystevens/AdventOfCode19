package uk.co.lukestevens.challenges;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import uk.co.lukestevens.challenges.intcode.IntCodeComputer;
import uk.co.lukestevens.challenges.intcode.IntCodeComputerFactory;
import uk.co.lukestevens.utils.InputFileReader;
import uk.co.lukestevens.utils.Wrapper;

public class TestCompleteIntCodeComputer {
	
	@Test
	public void testProgramLongerThanMemory() {
		List<Long> outputs = new ArrayList<>();
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
		Wrapper<Long> output = new Wrapper<>();
		int[] input = {1102,34915192,34915192,7,4,7,99,0};
		IntCodeComputerFactory factory = new IntCodeComputerFactory(input);
		IntCodeComputer computer = factory.createComputer();
		computer.setOutputCallback(output::set);
		computer.run();
		
		assertEquals(1219070632396864L, output.get());
	}
	
	@Test
	public void testLargeNumberInProgram() {
		Wrapper<Long> output = new Wrapper<>();
		Long[] input = {104L,1125899906842624L,99L};
		IntCodeComputerFactory factory = new IntCodeComputerFactory(input);
		IntCodeComputer computer = factory.createComputer();
		computer.setOutputCallback(output::set);
		computer.run();
		
		assertEquals(1125899906842624L, output.get());
	}
	
	@Test
	public void testBOOSTProgram() throws IOException {
		InputFileReader reader = new InputFileReader("Day9");
		Long[] input = reader.readFileAsArrayOfLongs();
		
		
		List<Long> outputs = new ArrayList<>();
		IntCodeComputerFactory factory = new IntCodeComputerFactory(input);
		IntCodeComputer computer = factory.createComputer();
		computer.addInput(1L);
		computer.setOutputCallback(outputs::add);
		computer.run();
		
		assertEquals(1, outputs.size());
		assertEquals(3507134798L, outputs.get(0));
	}
	
	@Test
	public void testFindSignal() throws IOException {
		InputFileReader reader = new InputFileReader("Day9");
		Long[] input = reader.readFileAsArrayOfLongs();
		
		
		Wrapper<Long> output = new Wrapper<>();
		IntCodeComputerFactory factory = new IntCodeComputerFactory(input);
		IntCodeComputer computer = factory.createComputer();
		computer.addInput(2L);
		computer.setOutputCallback(output::set);
		computer.run();
		
		assertEquals(84513L, output.get());
	}

}
