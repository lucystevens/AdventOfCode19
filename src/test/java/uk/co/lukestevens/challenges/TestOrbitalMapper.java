package uk.co.lukestevens.challenges;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import uk.co.lukestevens.utils.InputFileReader;

public class TestOrbitalMapper {
	
	@Test
	public void sequentialTotalOrbitsTest() {
		OrbitalMapper mapper = new OrbitalMapper(Arrays.asList(
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
		OrbitalMapper mapper = new OrbitalMapper(input);
		assertEquals(42, mapper.getTotalNumberOfOrbits());
	}
	
	@Test
	public static void testTask1() throws IOException {
		InputFileReader reader = new InputFileReader("Day6");
		List<String> input = reader.readFileAsListOfStrings();
		
		OrbitalMapper mapper = new OrbitalMapper(input);
		assertEquals(110190, mapper.getTotalNumberOfOrbits());
	}
	
	@Test
	public void sequentialTransferTimeTest() {
		OrbitalMapper mapper = new OrbitalMapper(Arrays.asList(
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
		OrbitalMapper mapper = new OrbitalMapper(input);
		assertEquals(4, mapper.getOrbitalTransfers("YOU", "SAN"));
	}

}
