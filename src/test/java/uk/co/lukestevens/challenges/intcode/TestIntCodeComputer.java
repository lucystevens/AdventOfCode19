package uk.co.lukestevens.challenges.intcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

import uk.co.lukestevens.utils.InputFileReader;

public class TestIntCodeComputer {
	
	@Test
	public void testParseOpcode() {
		int[] input = {1002,4,3,4,33,99};
		
		IntCodeComputerFactory factory = new IntCodeComputerFactory(input);
		IntCodeComputer computer = factory.createComputer();
		computer.addInput(1L);
		
		Opcode opcode = computer.parseOpcode();
		assertEquals(99, opcode.getValue());
		assertEquals(4, opcode.getPositionForValue());
		assertEquals(4, opcode.getCursorIncrement());
		assertEquals(OpcodeAction.WRITE, opcode.getAction());
	}
	
	@Test
	public void testTask1() throws IOException {
		List<Long> outputs = new ArrayList<>();
		InputFileReader reader = new InputFileReader("Day5");
		int[] input = reader.readFileAsArrayOfIntegers();
		
		IntCodeComputerFactory factory = new IntCodeComputerFactory(input);
		IntCodeComputer computer = factory.createComputer();
		computer.setOutputCallback(outputs::add);
		computer.addInput(1L);
		computer.run();
		
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
	
	@Test
	public void testInputIsEqualTo8_positionMode() {
		List<Long> outputs = new ArrayList<>();
		int[] input = {3,9,8,9,10,9,4,9,99,-1,8};
		IntCodeComputerFactory factory = new IntCodeComputerFactory(input);
		
		{
			IntCodeComputer computer = factory.createComputer();
			computer.setOutputCallback(outputs::add);
			computer.addInput(-1L);
			computer.run();
			assertEquals(0, outputs.get(0));
		}
		
		{
			IntCodeComputer computer = factory.createComputer();
			computer.setOutputCallback(outputs::add);
			computer.addInput(8L);
			computer.run();
			assertEquals(1, outputs.get(1));
		}
	}
	
	@Test
	public void testInputIsLessThan8_positionMode() {
		List<Long> outputs = new ArrayList<>();
		int[] input = {3,9,7,9,10,9,4,9,99,-1,8};
		IntCodeComputerFactory factory = new IntCodeComputerFactory(input);
		
		{
			IntCodeComputer computer = factory.createComputer();
			computer.setOutputCallback(outputs::add);
			computer.addInput(-1L);
			computer.run();
			assertEquals(1, outputs.get(0));
		}
		
		{
			IntCodeComputer computer = factory.createComputer();
			computer.setOutputCallback(outputs::add);
			computer.addInput(8L);
			computer.run();
			assertEquals(0, outputs.get(1));
		}
	}
	
	@Test
	public void testInputIsEqualTo8_immediateMode() {
		List<Long> outputs = new ArrayList<>();
		int[] input = {3,3,1108,-1,8,3,4,3,99};
		IntCodeComputerFactory factory = new IntCodeComputerFactory(input);
		
		{
			IntCodeComputer computer = factory.createComputer();
			computer.setOutputCallback(outputs::add);
			computer.addInput(-1L);
			computer.run();
			assertEquals(0, outputs.get(0));
		}
		
		{
			IntCodeComputer computer = factory.createComputer();
			computer.setOutputCallback(outputs::add);
			computer.addInput(8L);
			computer.run();
			assertEquals(1, outputs.get(1));
		}
	}
	
	@Test
	public void testInputIsLessThan8_immediateMode() {
		List<Long> outputs = new ArrayList<>();
		int[] input = {3,3,1107,-1,8,3,4,3,99};
		IntCodeComputerFactory factory = new IntCodeComputerFactory(input);
		
		{
			IntCodeComputer computer = factory.createComputer();
			computer.setOutputCallback(outputs::add);
			computer.addInput(-1L);
			computer.run();
			assertEquals(1, outputs.get(0));
		}
		
		{
			IntCodeComputer computer = factory.createComputer();
			computer.setOutputCallback(outputs::add);
			computer.addInput(8L);
			computer.run();
			assertEquals(0, outputs.get(1));
		}
	}
	
	@Test
	public void testInputIsNonZero_positionMode() {
		List<Long> outputs = new ArrayList<>();
		int[] input = {3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9};
		IntCodeComputerFactory factory = new IntCodeComputerFactory(input);
		
		{
			IntCodeComputer computer = factory.createComputer();
			computer.setOutputCallback(outputs::add);
			computer.addInput(-1L);
			computer.run();
			assertEquals(1, outputs.get(0));
		}
		
		{
			IntCodeComputer computer = factory.createComputer();
			computer.setOutputCallback(outputs::add);
			computer.addInput(0L);
			computer.run();
			assertEquals(0, outputs.get(1));
		}
		
		{
			IntCodeComputer computer = factory.createComputer();
			computer.setOutputCallback(outputs::add);
			computer.addInput(1L);
			computer.run();
			assertEquals(1, outputs.get(2));
		}
	}
	
	@Test
	public void testInputIsNonZero_immediateMode() {
		List<Long> outputs = new ArrayList<>();
		int[] input = {3,3,1105,-1,9,1101,0,0,12,4,12,99,1};
		IntCodeComputerFactory factory = new IntCodeComputerFactory(input);
		
		{
			IntCodeComputer computer = factory.createComputer();
			computer.setOutputCallback(outputs::add);
			computer.addInput(-1L);
			computer.run();
			assertEquals(1, outputs.get(0));
		}
		
		{
			IntCodeComputer computer = factory.createComputer();
			computer.setOutputCallback(outputs::add);
			computer.addInput(0L);
			computer.run();
			assertEquals(0, outputs.get(1));
		}
		
		{
			IntCodeComputer computer = factory.createComputer();
			computer.setOutputCallback(outputs::add);
			computer.addInput(1L);
			computer.run();
			assertEquals(1, outputs.get(2));
		}
	}
	
	@Test
	public void largerTest() {
		int[] input = {3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
				1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
				999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99};
		
		List<Long> outputs = new ArrayList<>();
		IntCodeComputerFactory factory = new IntCodeComputerFactory(input);
		
		{
			IntCodeComputer computer = factory.createComputer();
			computer.setOutputCallback(outputs::add);
			computer.addInput(-1L);
			computer.run();
			assertEquals(999, outputs.get(0));
		}
		
		{
			IntCodeComputer computer = factory.createComputer();
			computer.setOutputCallback(outputs::add);
			computer.addInput(7L);
			computer.run();
			assertEquals(999, outputs.get(1));
		}
		
		{
			IntCodeComputer computer = factory.createComputer();
			computer.setOutputCallback(outputs::add);
			computer.addInput(8L);
			computer.run();
			assertEquals(1000, outputs.get(2));
		}
		
		{
			IntCodeComputer computer = factory.createComputer();
			computer.setOutputCallback(outputs::add);
			computer.addInput(9L);
			computer.run();
			assertEquals(1001, outputs.get(3));
		}
	}
	
	public static void testTask2() throws IOException {
		Consumer<Long> callback = i -> assertEquals(3508186, 1);
		InputFileReader reader = new InputFileReader("Day5");
		int[] input = reader.readFileAsArrayOfIntegers();
		
		IntCodeComputerFactory factory = new IntCodeComputerFactory(input);
		IntCodeComputer computer = factory.createComputer();
		computer.setOutputCallback(callback);
		computer.addInput(5L);
		computer.run();
	}

}
