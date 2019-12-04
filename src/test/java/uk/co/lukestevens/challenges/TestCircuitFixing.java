package uk.co.lukestevens.challenges;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import uk.co.lukestevens.utils.InputFileReader;

public class TestCircuitFixing {

	@Test
	public void fullTest1() {
		List<String> wire1 = Arrays.asList("R75","D30","R83","U83","L12","D49","R71","U7","L72");
		List<String> wire2 = Arrays.asList("U62","R66","U55","R34","D71","R55","D58","R83");
		
		Circuit circuit = new Circuit();
		circuit.mapCircuit(wire1, wire2);
		assertEquals(159, circuit.findClosestIntersectionToCentralPort());
	}
	
	@Test
	public void fullTest2() {
		List<String> wire1 = Arrays.asList("R98","U47","R26","D63","R33","U87","L62","D20","R33","U53","R51");
		List<String> wire2 = Arrays.asList("U98","R91","D20","R16","D67","R40","U7","R15","U6","R7");
		
		Circuit circuit = new Circuit();
		circuit.mapCircuit(wire1, wire2);
		assertEquals(135, circuit.findClosestIntersectionToCentralPort());
	}
	
	@Test
	public void testTask1() throws IOException {
		InputFileReader reader = new InputFileReader("Day3");
		List<String> input = reader.readFileAsListOfStrings();
		
		List<String> wire1 = Arrays.asList(input.get(0).split(","));
		List<String> wire2 = Arrays.asList(input.get(1).split(","));
		
		Circuit circuit = new Circuit();
		circuit.mapCircuit(wire1, wire2);
		assertEquals(245, circuit.findClosestIntersectionToCentralPort());
	}

	@Test
	public void stepsTest1() {
		List<String> wire1 = Arrays.asList("R75","D30","R83","U83","L12","D49","R71","U7","L72");
		List<String> wire2 = Arrays.asList("U62","R66","U55","R34","D71","R55","D58","R83");
		
		Circuit circuit = new Circuit();
		circuit.mapCircuit(wire1, wire2);
		assertEquals(610, circuit.findIntersectionWithFewestSteps());
	}
	
	@Test
	public void stepsTest2() {
		List<String> wire1 = Arrays.asList("R98","U47","R26","D63","R33","U87","L62","D20","R33","U53","R51");
		List<String> wire2 = Arrays.asList("U98","R91","D20","R16","D67","R40","U7","R15","U6","R7");
		
		Circuit circuit = new Circuit();
		circuit.mapCircuit(wire1, wire2);
		assertEquals(410, circuit.findIntersectionWithFewestSteps());
	}
}
