package uk.co.lukestevens.main;

import java.io.IOException;
import java.util.List;

import uk.co.lukestevens.challenges.GravityAssistComputer;
import uk.co.lukestevens.challenges.RocketFuelCalculator;
import uk.co.lukestevens.utils.InputFileReader;

public class AdventOfCodeMain {

	public static void main(String[] args) throws IOException {
		day2Task2();

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
		
		GravityAssistComputer computer = new GravityAssistComputer();
		System.out.println(computer.runOpcodeWithInputs(12, 2, input));
	}
	
	public static void day2Task2() throws IOException {
		InputFileReader reader = new InputFileReader("Day2");
		int[] input = reader.readFileAsArrayOfIntegers();
		
		GravityAssistComputer computer = new GravityAssistComputer();
		System.out.println(computer.findInputsForValue(19690720, input));
	}
}
