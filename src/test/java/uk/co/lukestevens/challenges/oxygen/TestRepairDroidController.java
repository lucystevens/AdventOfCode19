package uk.co.lukestevens.challenges.oxygen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;
import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import uk.co.lukestevens.utils.InputFileReader;

public class TestRepairDroidController {
	
	static InputFileReader reader;
	static Long[] input;

	@BeforeAll
	public static void setup() throws IOException {
		reader = new InputFileReader("Day15");
		input = reader.readFileAsArrayOfLongs();
	}
	
	@Test
	public void testFindOxygenTank() {
		RepairDroidController controller = new RepairDroidController(input);
		Point oxygenTank = controller.getOxygenTank();
		assertEquals(-14.0, oxygenTank.getX());
		assertEquals(12.0, oxygenTank.getY());
	}
	
	@Test
	public void testFindDistanceToOxygenTank() {
		RepairDroidController controller = new RepairDroidController(input);
		Point oxygenTank = controller.getOxygenTank();
		assertEquals(258, controller.findShortestRoute(new Point(0,0), oxygenTank));
	}
	
	@Test
	public void testFindTimeToFillWithOxygen() {
		RepairDroidController controller = new RepairDroidController(input);
		assertEquals(372, controller.getTimeToSpreadOxygen());
	}

}
