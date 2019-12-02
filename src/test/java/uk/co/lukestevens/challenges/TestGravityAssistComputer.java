package uk.co.lukestevens.challenges;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import uk.co.lukestevens.utils.InputFileReader;

public class TestGravityAssistComputer {
	
	GravityAssistComputer comp = new GravityAssistComputer();
	
	@Test
	public void testProgram1() {
		int[] input = {1,0,0,0,99};
		int[] expected = {2,0,0,0,99};
		assertArrayEquals(expected, comp.runOpcode(input));
	}
	
	@Test
	public void testProgram2() {
		int[] input = {2,3,0,3,99};
		int[] expected = {2,3,0,6,99};
		assertArrayEquals(expected, comp.runOpcode(input));
	}
	
	@Test
	public void testProgram3() {
		int[] input = {2,4,4,5,99,0};
		int[] expected = {2,4,4,5,99,9801};
		assertArrayEquals(expected, comp.runOpcode(input));
	}
	
	@Test
	public void testProgram4() {
		int[] input = {1,1,1,4,99,5,6,0,99};
		int[] expected = {30,1,1,4,2,5,6,0,99};
		assertArrayEquals(expected, comp.runOpcode(input));
	}
	
	@Test
	public void testProgramSolution() throws IOException {
		InputFileReader reader = new InputFileReader("Day2");
		int[] input = reader.readFileAsArrayOfIntegers();
		assertEquals(6568671, comp.runOpcodeWithInputs(12, 2, input));
	}
	
	@Test
	public void testProgramFindInputs() throws IOException {
		InputFileReader reader = new InputFileReader("Day2");
		int[] input = reader.readFileAsArrayOfIntegers();
		assertEquals(1202, comp.findInputsForValue(6568671, input));
	}
	

}
