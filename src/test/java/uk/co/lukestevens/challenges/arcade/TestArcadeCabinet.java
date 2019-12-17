package uk.co.lukestevens.challenges.arcade;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import uk.co.lukestevens.utils.InputFileReader;

public class TestArcadeCabinet {
	
	static InputFileReader reader;
	static Long[] input;

	@BeforeAll
	public static void setup() throws IOException {
		reader = new InputFileReader("Day13");
		input = reader.readFileAsArrayOfLongs();
	}
	
	@Test
	public void testDrawScreen() throws IOException {
		ArcadeCabinet arcade = new ArcadeCabinet(input);
		arcade.drawGame();
		assertEquals(324,  arcade.getBlockTiles());
	}
	
	@Test
	public void testPlayGame() {
		ArcadeCabinet arcade = new ArcadeCabinet(input);
		assertEquals(15957, arcade.playGame(false));
	}

}
