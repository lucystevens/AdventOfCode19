package uk.co.lukestevens.challenges.orbits;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import uk.co.lukestevens.challenges.orbits.OrbitalMap;
import uk.co.lukestevens.utils.InputFileReader;

public class TestOrbitalMap {
	
	@Test
	public void sequentialTotalOrbitsTest() {
		OrbitalMap mapper = new OrbitalMap(Arrays.asList(
				"COM)B",
				"B)C",
				"C)D",
				"D)E",
				"E)F",
				"B)G",
				"G)H",
				"D)I",
				"E)J",
				"J)K",
				"K)L"
		));
		
		assertEquals(42, mapper.getTotalNumberOfOrbits());
	}
	
	@Test
	public void randomisedTotalOrbitsTest() {
		List<String> input = Arrays.asList(
				"COM)B",
				"B)C",
				"C)D",
				"D)E",
				"E)F",
				"B)G",
				"G)H",
				"D)I",
				"E)J",
				"J)K",
				"K)L"
		);
		Collections.shuffle(input);
		OrbitalMap mapper = new OrbitalMap(input);
		assertEquals(42, mapper.getTotalNumberOfOrbits());
	}
	
	@Test
	public void testTask1() throws IOException {
		InputFileReader reader = new InputFileReader("Day6");
		List<String> input = reader.readFileAsListOfStrings();
		
		OrbitalMap mapper = new OrbitalMap(input);
		assertEquals(110190, mapper.getTotalNumberOfOrbits());
	}
	
	@Test
	public void sequentialTransferTimeTest() {
		OrbitalMap mapper = new OrbitalMap(Arrays.asList(
				"COM)B",
				"B)C",
				"C)D",
				"D)E",
				"E)F",
				"B)G",
				"G)H",
				"D)I",
				"E)J",
				"J)K",
				"K)L",
				"K)YOU",
				"I)SAN"
		));
		
		assertEquals(4, mapper.getOrbitalTransfers("YOU", "SAN"));
	}
	
	@Test
	public void randomisedTransferTimeTest() {
		List<String> input = Arrays.asList(
				"COM)B",
				"B)C",
				"C)D",
				"D)E",
				"E)F",
				"B)G",
				"G)H",
				"D)I",
				"E)J",
				"J)K",
				"K)L",
				"K)YOU",
				"I)SAN"
		);
		Collections.shuffle(input);
		OrbitalMap mapper = new OrbitalMap(input);
		assertEquals(4, mapper.getOrbitalTransfers("YOU", "SAN"));
	}
	
	@Test
	public void testTask2() throws IOException {
		InputFileReader reader = new InputFileReader("Day6");
		List<String> input = reader.readFileAsListOfStrings();
		
		OrbitalMap mapper = new OrbitalMap(input);
		assertEquals(343, mapper.getOrbitalTransfers("YOU", "SAN"));
	}

}
