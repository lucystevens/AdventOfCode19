package uk.co.lukestevens.challenges;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import uk.co.lukestevens.utils.InputFileReader;

public class TestRocketFuelCalculator {
	
	RocketFuelCalculator calc = new RocketFuelCalculator();
	
	@Test
	public void testSimpleInput() {
		List<Integer> input = Arrays.asList(14);
		assertEquals(2, calc.getBaseFuelForModules(input));
	}
	
	@Test
	public void testSingleInput() {
		List<Integer> input = Arrays.asList(100756);
		assertEquals(33583, calc.getBaseFuelForModules(input));
	}
	
	
	@Test
	public void testMultipleInput() {
		List<Integer> input = Arrays.asList(100756, 1969, 12);
		assertEquals(34239, calc.getBaseFuelForModules(input));
	}
	
	@Test
	public void testFinalInput() throws IOException {
		InputFileReader reader = new InputFileReader("Day1");
		List<Integer> input = reader.readFileAsListOfIntegers();
		
		assertEquals(3393938, calc.getBaseFuelForModules(input));
	}
	
	@Test
	public void testSimpleInputWithFuel() {
		List<Integer> input = Arrays.asList(14);
		assertEquals(2, calc.getTotalFuelForModules(input));
	}
	
	@Test
	public void testSingleInputWithFuel() {
		List<Integer> input = Arrays.asList(1969);
		assertEquals(966, calc.getTotalFuelForModules(input));
	}
	
	
	@Test
	public void testMultipleInputWithFuel() {
		List<Integer> input = Arrays.asList(100756, 1969, 12);
		assertEquals(51314, calc.getTotalFuelForModules(input));
	}

}
