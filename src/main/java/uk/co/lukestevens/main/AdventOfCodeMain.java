package uk.co.lukestevens.main;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import uk.co.lukestevens.challenges.GravityAssistComputer;
import uk.co.lukestevens.challenges.PasswordCracker;
import uk.co.lukestevens.challenges.RocketFuelCalculator;
import uk.co.lukestevens.challenges.amplifiers.AmplifierController;
import uk.co.lukestevens.challenges.circuits.Circuit;
import uk.co.lukestevens.challenges.intcode.IntCodeComputer;
import uk.co.lukestevens.challenges.orbits.OrbitalMap;
import uk.co.lukestevens.utils.InputFileReader;

public class AdventOfCodeMain {

	public static void main(String[] args) throws IOException {
		day7Task2();

	}
	
	public static void day1Task1() throws IOException {
		InputFileReader reader = new InputFileReader("Day1");
		List<Integer> input = reader.readFileAsListOfIntegers();
		
		RocketFuelCalculator calculator = new RocketFuelCalculator();
		System.out.println(calculator.getBaseFuelForModules(input));
	}

	public static void day1Task2() throws IOException {
		InputFileReader reader = new InputFileReader("Day1");
		List<Integer> input = reader.readFileAsListOfIntegers();
		
		RocketFuelCalculator calculator = new RocketFuelCalculator();
		System.out.println(calculator.getTotalFuelForModules(input));
	}
	
	public static void day2Task1() throws IOException {
		InputFileReader reader = new InputFileReader("Day2");
		int[] input = reader.readFileAsArrayOfIntegers();
		
		GravityAssistComputer computer = new GravityAssistComputer(input);
		System.out.println(computer.run(12, 2));
	}
	
	public static void day2Task2() throws IOException {
		InputFileReader reader = new InputFileReader("Day2");
		int[] input = reader.readFileAsArrayOfIntegers();
		
		GravityAssistComputer computer = new GravityAssistComputer(input);
		System.out.println(computer.findInputsForValue(19690720));
	}
	
	public static void day3Task1() throws IOException {
		InputFileReader reader = new InputFileReader("Day3");
		List<List<String>> wires = reader.readFileAsListOfStrings().stream().map(in -> Arrays.asList(in.split(","))).collect(Collectors.toList());
		
		Circuit circuit = new Circuit();
		circuit.mapCircuits(wires);
		System.out.println(circuit.findClosestIntersectionToCentralPort());
	}
	
	public static void day3Task2() throws IOException {
		InputFileReader reader = new InputFileReader("Day3");
		List<List<String>> wires = reader.readFileAsListOfStrings().stream().map(in -> Arrays.asList(in.split(","))).collect(Collectors.toList());
		
		Circuit circuit = new Circuit();
		circuit.mapCircuits(wires);
		System.out.println(circuit.findIntersectionWithFewestSteps());
	}
	
	public static void day4Task1() throws IOException {
		PasswordCracker cracker = new PasswordCracker();
		System.out.println(cracker.getPasswordsInRange(234208, 765869).size());
	}
	
	public static void day4Task2() throws IOException {
		PasswordCracker cracker = new PasswordCracker();
		System.out.println(cracker.getPasswordsInRangeExt(234208, 765869).size());
	}
	
	public static void day5Task1() throws IOException {
		InputFileReader reader = new InputFileReader("Day5");
		int[] input = reader.readFileAsArrayOfIntegers();
		
		IntCodeComputer computer = new IntCodeComputer(input);
		computer.run(1);
	}
	
	public static void day5Task2() throws IOException {
		InputFileReader reader = new InputFileReader("Day5");
		int[] input = reader.readFileAsArrayOfIntegers();
		
		IntCodeComputer computer = new IntCodeComputer(input);
		computer.run(5);
	}
	
	public static void day6Task1() throws IOException {
		InputFileReader reader = new InputFileReader("Day6");
		List<String> input = reader.readFileAsListOfStrings();
		
		OrbitalMap mapper = new OrbitalMap(input);
		System.out.println(mapper.getTotalNumberOfOrbits());
	}
	
	public static void day6Task2() throws IOException {
		InputFileReader reader = new InputFileReader("Day6");
		List<String> input = reader.readFileAsListOfStrings();
		
		OrbitalMap mapper = new OrbitalMap(input);
		System.out.println(mapper.getOrbitalTransfers("YOU", "SAN"));
	}
	
	public static void day7Task1() throws IOException {
		InputFileReader reader = new InputFileReader("Day7");
		int[] input = reader.readFileAsArrayOfIntegers();
		
		AmplifierController controller = new AmplifierController(input);
		System.out.println(controller.findMaxThrusterSignal(5));
	}
	
	public static void day7Task2() throws IOException {
		InputFileReader reader = new InputFileReader("Day7");
		int[] input = reader.readFileAsArrayOfIntegers();
		
		AmplifierController controller = new AmplifierController(input);
		System.out.println(controller.findMaxThrusterSignalRewired(5));
	}
	
	
	
}
