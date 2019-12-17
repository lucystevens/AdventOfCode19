package uk.co.lukestevens.challenges.scaffold;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import uk.co.lukestevens.utils.InputFileReader;

public class TestScaffoldMap {
	
	static InputFileReader reader;
	static Long[] input;

	@BeforeAll
	public static void setup() throws IOException {
		reader = new InputFileReader("Day17");
		input = reader.readFileAsArrayOfLongs();
	}
	
	@Test
	public void testGetIntersections() {
		ScaffoldMap map = new ScaffoldMap(input);
		List<Point> intersections = map.getAllIntersections();
		assertEquals(11, intersections.size());
	}
	
	@Test
	public void testGetAlignmentSum() {
		ScaffoldMap map = new ScaffoldMap(input);
		assertEquals(6244, map.getAlignmentSum());
	}

}
