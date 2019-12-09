package uk.co.lukestevens.challenges;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import uk.co.lukestevens.utils.InputFileReader;

public class TestGravityAssistComputer {
	
	
	
	@Test
	public void testProgram1() {
		int[] input = {1,0,0,0,99};
		Integer[] expected = {2,0,0,0,99};
		
		GravityAssistComputer comp = new GravityAssistComputer(input);
		assertArrayEquals(expected, comp.run());
	}
	
	@Test
	public void testProgram2() {
		int[] input = {2,3,0,3,99};
		Integer[] expected = {2,3,0,6,99};
		
		GravityAssistComputer comp = new GravityAssistComputer(input);
		assertArrayEquals(expected, comp.run());
	}
	
	@Test
	public void testProgram3() {
		int[] input = {2,4,4,5,99,0};
		Integer[] expected = {2,4,4,5,99,9801};
		
		GravityAssistComputer comp = new GravityAssistComputer(input);
		assertArrayEquals(expected, comp.run());
	}
	
	@Test
	public void testProgram4() {
		int[] input = {1,1,1,4,99,5,6,0,99};
		Integer[] expected = {30,1,1,4,2,5,6,0,99};
		
		GravityAssistComputer comp = new GravityAssistComputer(input);
		assertArrayEquals(expected, comp.run());
	}
	
	@Test
	public void testProgramSolution() throws IOException {
		InputFileReader reader = new InputFileReader("Day2");
		int[] input = reader.readFileAsArrayOfIntegers();
		
		GravityAssistComputer comp = new GravityAssistComputer(input);
		assertEquals(6568671, comp.run(12, 2));
	}
	
	@Test
	public void testProgramFindInputs() throws IOException {
		InputFileReader reader = new InputFileReader("Day2");
		int[] input = reader.readFileAsArrayOfIntegers();
		
		GravityAssistComputer comp = new GravityAssistComputer(input);
		assertEquals(1202, comp.findInputsForValue(6568671));
	}
	

}
