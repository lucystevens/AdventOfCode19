package uk.co.lukestevens.challenges.painting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import uk.co.lukestevens.challenges.HullPaintingRobot;
import uk.co.lukestevens.utils.InputFileReader;

public class TestHullPaintingRobot {
	
	static InputFileReader reader;
	static Long[] input;

	@BeforeAll
	public static void setup() throws IOException {
		reader = new InputFileReader("Day11");
		input = reader.readFileAsArrayOfLongs();
	}
	
	@Test
	public void testPaintHull() throws IOException {
		HullPaintingRobot robot = new HullPaintingRobot(input);
		robot.paintPanel();
		assertEquals(1951, robot.getPanelsPainted());
	}

}
