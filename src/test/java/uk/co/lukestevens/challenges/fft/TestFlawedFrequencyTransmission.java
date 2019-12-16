package uk.co.lukestevens.challenges.fft;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

import static uk.co.lukestevens.utils.Utils.asString;
import static uk.co.lukestevens.utils.Utils.asInts;

public class TestFlawedFrequencyTransmission {
	
	@Test
	public void testGetPattern_position1() {
		FlawedFrequencyTransmission fft = new FlawedFrequencyTransmission();
		List<Integer> pattern = fft.getPattern(1);
		assertEquals(4, pattern.size());
		assertEquals("010-1", asString(pattern));
	}
	
	@Test
	public void testGetPattern_position3() {
		FlawedFrequencyTransmission fft = new FlawedFrequencyTransmission();
		List<Integer> pattern = fft.getPattern(3);
		assertEquals(12, pattern.size());
		assertEquals("000111000-1-1-1", asString(pattern));
	}
	
	@Test
	public void testProcessPhase() {
		FlawedFrequencyTransmission fft = new FlawedFrequencyTransmission();
		List<Integer> input = asInts("12345678");
		
		List<Integer> phase1 = fft.processPhase(input);
		assertEquals("48226158", asString(phase1));
		
		List<Integer> phase2 = fft.processPhase(phase1);
		assertEquals("34040438", asString(phase2));
		
		List<Integer> phase3 = fft.processPhase(phase2);
		assertEquals("03415518", asString(phase3));
		
		List<Integer> phase4 = fft.processPhase(phase3);
		assertEquals("01029498", asString(phase4));
	}
	
	@Test
	public void testProcess100Phases() {
		FlawedFrequencyTransmission fft = new FlawedFrequencyTransmission();
		
		{
			List<Integer> input = asInts("80871224585914546619083218645595");
			List<Integer> output = fft.processPhases(input, 100);
			assertEquals("24176176", asString(output).substring(0, 8));
		}
		
		{
			List<Integer> input = asInts("19617804207202209144916044189917");
			List<Integer> output = fft.processPhases(input, 100);
			assertEquals("73745418", asString(output).substring(0, 8));
		}
		
		{
			List<Integer> input = asInts("69317163492948606335995924319873");
			List<Integer> output = fft.processPhases(input, 100);
			assertEquals("52432133", asString(output).substring(0, 8));
		}
	}
	
	@Test
	public void testDecodeSignal() {
		FlawedFrequencyTransmission fft = new FlawedFrequencyTransmission();
		
		{
			List<Integer> input = asInts("03036732577212944063491565474664");
			String output = fft.decodeSignal(input);
			assertEquals("84462026", output);
		}
		
		{
			List<Integer> input = asInts("02935109699940807407585447034323");
			String output = fft.decodeSignal(input);
			assertEquals("78725270", output);
		}
		
		{
			List<Integer> input = asInts("03081770884921959731165446850517");
			String output = fft.decodeSignal(input);
			assertEquals("53553731", output);
		}
	}

}
