package uk.co.lukestevens.challenges.circuits;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import uk.co.lukestevens.challenges.circuits.Circuit;
import uk.co.lukestevens.utils.InputFileReader;

public class TestCircuitFixing {

	@Test
	public void fullTest1() {
		List<List<String>> wires = Arrays.asList(
				Arrays.asList("R75","D30","R83","U83","L12","D49","R71","U7","L72"),
				Arrays.asList("U62","R66","U55","R34","D71","R55","D58","R83"));
		
		Circuit circuit = new Circuit();
		circuit.mapCircuits(wires);
		assertEquals(159, circuit.findClosestIntersectionToCentralPort());
	}
	
	@Test
	public void fullTest2() {
		List<List<String>> wires = Arrays.asList(
				Arrays.asList("R98","U47","R26","D63","R33","U87","L62","D20","R33","U53","R51"),
				Arrays.asList("U98","R91","D20","R16","D67","R40","U7","R15","U6","R7"));
		
		Circuit circuit = new Circuit();
		circuit.mapCircuits(wires);
		assertEquals(135, circuit.findClosestIntersectionToCentralPort());
	}
	
	@Test
	public void testTask1() throws IOException {
		InputFileReader reader = new InputFileReader("Day3");
		List<List<String>> wires = reader.readFileAsListOfStrings().stream().map(in -> Arrays.asList(in.split(","))).collect(Collectors.toList());
		
		Circuit circuit = new Circuit();
		circuit.mapCircuits(wires);
		assertEquals(245, circuit.findClosestIntersectionToCentralPort());
	}

	@Test
	public void stepsTest1() {
		List<List<String>> wires = Arrays.asList(
				Arrays.asList("R75","D30","R83","U83","L12","D49","R71","U7","L72"),
				Arrays.asList("U62","R66","U55","R34","D71","R55","D58","R83"));
		
		Circuit circuit = new Circuit();
		circuit.mapCircuits(wires);
		assertEquals(610, circuit.findIntersectionWithFewestSteps());
	}
	
	@Test
	public void stepsTest2() {
		List<List<String>> wires = Arrays.asList(
				Arrays.asList("R98","U47","R26","D63","R33","U87","L62","D20","R33","U53","R51"),
				Arrays.asList("U98","R91","D20","R16","D67","R40","U7","R15","U6","R7"));
		
		Circuit circuit = new Circuit();
		circuit.mapCircuits(wires);
		assertEquals(410, circuit.findIntersectionWithFewestSteps());
	}
	
	@Test
	public void testTask2() throws IOException {
		InputFileReader reader = new InputFileReader("Day3");
		List<List<String>> wires = reader.readFileAsListOfStrings().stream().map(in -> Arrays.asList(in.split(","))).collect(Collectors.toList());
		
		Circuit circuit = new Circuit();
		circuit.mapCircuits(wires);
		assertEquals(48262, circuit.findIntersectionWithFewestSteps());
	}
}
