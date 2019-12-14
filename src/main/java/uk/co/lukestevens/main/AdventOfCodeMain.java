package uk.co.lukestevens.main;

import java.awt.Point;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import uk.co.lukestevens.challenges.GravityAssistComputer;
import uk.co.lukestevens.challenges.HullPaintingRobot;
import uk.co.lukestevens.challenges.PasswordCracker;
import uk.co.lukestevens.challenges.RocketFuelCalculator;
import uk.co.lukestevens.challenges.amplifiers.AmplifierController;
import uk.co.lukestevens.challenges.arcade.ArcadeCabinet;
import uk.co.lukestevens.challenges.asteroids.Asteroid;
import uk.co.lukestevens.challenges.asteroids.AsteroidField;
import uk.co.lukestevens.challenges.circuits.Circuit;
import uk.co.lukestevens.challenges.intcode.IntCodeComputer;
import uk.co.lukestevens.challenges.intcode.IntCodeComputerFactory;
import uk.co.lukestevens.challenges.moons.Moon;
import uk.co.lukestevens.challenges.moons.MoonMapper;
import uk.co.lukestevens.challenges.nanofactory.ChemicalReaction;
import uk.co.lukestevens.challenges.nanofactory.Nanofactory;
import uk.co.lukestevens.challenges.orbits.OrbitalMap;
import uk.co.lukestevens.challenges.spaceimage.SpaceImage;
import uk.co.lukestevens.utils.Grid;
import uk.co.lukestevens.utils.InputFileReader;

public class AdventOfCodeMain {

	public static void main(String[] args) throws IOException {
		day14Task2();
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
		
		IntCodeComputerFactory factory = new IntCodeComputerFactory(input);
		IntCodeComputer computer = factory.createComputer();
		computer.addInput(1L);
		computer.run();
	}
	
	public static void day5Task2() throws IOException {
		InputFileReader reader = new InputFileReader("Day5");
		int[] input = reader.readFileAsArrayOfIntegers();
		
		IntCodeComputerFactory factory = new IntCodeComputerFactory(input);
		IntCodeComputer computer = factory.createComputer();
		computer.addInput(5L);
		computer.run();
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
	
	public static void day8Task1() throws IOException {
		InputFileReader reader = new InputFileReader("Day8");
		List<String> input = reader.readFileAsListOfStrings();
		List<Integer> format = input.get(0).chars().mapToObj(i -> i-48).collect(Collectors.toList());
		
		SpaceImage image = new SpaceImage(25, 6, format);
		Grid<Integer> layer = image.getLayerWithFewestZeroes();
		
		long ones = image.getDigitCountOnLayer(layer, 1);
		long twos = image.getDigitCountOnLayer(layer, 2);
		System.out.println(ones * twos);
	}
	
	public static void day8Task2() throws IOException {
		InputFileReader reader = new InputFileReader("Day8");
		List<String> input = reader.readFileAsListOfStrings();
		List<Integer> format = input.get(0).chars().mapToObj(i -> i-48).collect(Collectors.toList());
		
		SpaceImage image = new SpaceImage(25, 6, format);
		System.out.println(image.getDecodedImage().toString().replaceAll("0", " ").replaceAll("1", "x"));
	}
	
	public static void day9Task1() throws IOException {
		InputFileReader reader = new InputFileReader("Day9");
		Long[] input = reader.readFileAsArrayOfLongs();
		
		IntCodeComputerFactory factory = new IntCodeComputerFactory(input);
		IntCodeComputer computer = factory.createComputer();
		computer.addInput(1L);
		computer.run();
	}
	
	public static void day9Task2() throws IOException {
		InputFileReader reader = new InputFileReader("Day9");
		Long[] input = reader.readFileAsArrayOfLongs();
		
		IntCodeComputerFactory factory = new IntCodeComputerFactory(input);
		IntCodeComputer computer = factory.createComputer();
		computer.addInput(2L);
		computer.run();
	}
	
	public static void day10Task1() throws IOException {
		InputFileReader reader = new InputFileReader("Day10");
		List<String> input = reader.readFileAsListOfStrings();
		
		AsteroidField field = new AsteroidField(input);
		field.mapPossibleMonitoringStations();
		
		Asteroid bestLocation = field.getBestMonitoringStationLocation();
		System.out.println(bestLocation.getAsteroidsInSight());
	}
	
	public static void day10Task2() throws IOException {
		InputFileReader reader = new InputFileReader("Day10");
		List<String> input = reader.readFileAsListOfStrings();
		
		AsteroidField field = new AsteroidField(input);
		field.mapPossibleMonitoringStations();
		
		Asteroid bestLocation = field.getBestMonitoringStationLocation();
		List<Asteroid> blastOrder = field.blastAsteroids(bestLocation);
		
		Point point200 = blastOrder.get(199).getPoint();
		System.out.println((point200.getX()*100) + point200.getY());
	}
	
	public static void day11Task1() throws IOException {
		InputFileReader reader = new InputFileReader("Day11");
		Long[] input = reader.readFileAsArrayOfLongs();
		
		HullPaintingRobot robot = new HullPaintingRobot(input);
		robot.paintPanel(0);
		System.out.println(robot.getPanelsPainted());
	}
	
	public static void day11Task2() throws IOException {
		InputFileReader reader = new InputFileReader("Day11");
		Long[] input = reader.readFileAsArrayOfLongs();
		
		HullPaintingRobot robot = new HullPaintingRobot(input);
		robot.paintPanel(1);
		System.out.println(robot.getHull().toString().replaceAll(" ", "x").replaceAll("X", " "));
	}
	
	public static void day12Task1() {
		Moon m1 = new Moon(17, -12, 13);
		Moon m2 = new Moon(2, 1, 1);
		Moon m3 = new Moon(-1, -17, 7);
		Moon m4 = new Moon(12, -14, 18);
		
		MoonMapper mapper = new MoonMapper(Arrays.asList(m1, m2, m3, m4));
		mapper.simulateTimeSteps(1000);
		System.out.println(mapper.getTotalEnergy());
	}
	
	public static void day12Task2() {
		Moon m1 = new Moon(17, -12, 13);
		Moon m2 = new Moon(2, 1, 1);
		Moon m3 = new Moon(-1, -17, 7);
		Moon m4 = new Moon(12, -14, 18);
		
		MoonMapper mapper = new MoonMapper(Arrays.asList(m1, m2, m3, m4));
		System.out.println(mapper.findStepsToMatchPreviousPoint());
	}
	
	public static void day13Task1() throws IOException {
		InputFileReader reader = new InputFileReader("Day13");
		Long[] input = reader.readFileAsArrayOfLongs();
		
		ArcadeCabinet arcade = new ArcadeCabinet(input);
		arcade.drawGame();
		System.out.println(arcade.getBlockTiles());
	}
	
	public static void day13Task2() throws IOException {
		InputFileReader reader = new InputFileReader("Day13");
		Long[] input = reader.readFileAsArrayOfLongs();
		
		ArcadeCabinet arcade = new ArcadeCabinet(input);
		System.out.println(arcade.playGame());
	}
	
	public static void day14Task1() throws IOException {
		InputFileReader reader = new InputFileReader("Day14");
		List<ChemicalReaction> input = reader
				.readFileAsListOfStrings()
				.stream()
				.map(ChemicalReaction::parseReaction)
				.collect(Collectors.toList());
		
		Nanofactory factory = new Nanofactory(input);
		System.out.println(factory.getOreNeededForOneFuel());
	}
	
	public static void day14Task2() throws IOException {
		InputFileReader reader = new InputFileReader("Day14");
		List<ChemicalReaction> input = reader
				.readFileAsListOfStrings()
				.stream()
				.map(ChemicalReaction::parseReaction)
				.collect(Collectors.toList());
		
		Nanofactory factory = new Nanofactory(input);
		System.out.println(factory.getFuelProducedFromOre(1000000000000L));
	}
	
}
