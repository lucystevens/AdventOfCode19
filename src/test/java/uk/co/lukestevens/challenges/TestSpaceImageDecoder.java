package uk.co.lukestevens.challenges;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import uk.co.lukestevens.challenges.spaceimage.SpaceImage;
import uk.co.lukestevens.utils.Grid;

public class TestSpaceImageDecoder {
	
	@Test
	public void testGetLayerWithFewestZeroes() {
		List<Integer> format = "123456789012".chars().mapToObj(i -> i-48).collect(Collectors.toList());
		SpaceImage image = new SpaceImage(3, 2, format);
		Grid<Integer> layer = image.getLayerWithFewestZeroes();
		
		assertEquals(1, layer.get(0, 0));
		assertEquals(2, layer.get(1, 0));
		assertEquals(3, layer.get(2, 0));
		assertEquals(4, layer.get(0, 1));
		assertEquals(5, layer.get(1, 1));
		assertEquals(6, layer.get(2, 1));
	}
	
	@Test
	public void testMultiplyCountsOfOnesAndTwos() {
		List<Integer> format = "123456789012".chars().mapToObj(i -> i-48).collect(Collectors.toList());
		SpaceImage image = new SpaceImage(3, 2, format);
		Grid<Integer> layer = image.getLayerWithFewestZeroes();
		
		long ones = image.getDigitCountOnLayer(layer, 1);
		long twos = image.getDigitCountOnLayer(layer, 2);
		assertEquals(1, ones * twos);
	}
	
	@Test
	public void testDecodeImage() {
		List<Integer> format = "0222112222120000".chars().mapToObj(i -> i-48).collect(Collectors.toList());
		SpaceImage image = new SpaceImage(2, 2, format);
		Grid<Integer> decodedImage = image.getDecodedImage();
		assertEquals("01\n10\n", decodedImage.toString());
	}

}
