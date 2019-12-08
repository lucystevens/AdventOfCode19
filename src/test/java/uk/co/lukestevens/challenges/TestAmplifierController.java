package uk.co.lukestevens.challenges;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import uk.co.lukestevens.challenges.amplifiers.AmplifierController;
import uk.co.lukestevens.utils.InputFileReader;
import uk.co.lukestevens.utils.Utils;

public class TestAmplifierController {
	
	@Test
	public void testCalculateSignal1() {
		int[] program = {3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0};
		List<Integer> phaseSettings = Arrays.asList(4,3,2,1,0);
		
		AmplifierController controller = new AmplifierController(program);
		assertEquals(43210, controller.run(phaseSettings));
	}
	
	@Test
	public void testCalculateSignal2() {
		int[] program = {3,23,3,24,1002,24,10,24,1002,23,-1,23,
				101,5,23,23,1,24,23,23,4,23,99,0,0};
		List<Integer> phaseSettings = Arrays.asList(0,1,2,3,4);
		
		AmplifierController controller = new AmplifierController(program);
		assertEquals(54321, controller.run(phaseSettings));
	}
	
	@Test
	public void testCalculateSignal3() {
		int[] program = {3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,
				1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0};
		List<Integer> phaseSettings = Arrays.asList(1,0,4,3,2);
		
		AmplifierController controller = new AmplifierController(program);
		assertEquals(65210, controller.run(phaseSettings));
	}
	
	
	@Test
	public void testCalculateMaxSignal1() {
		int[] program = {3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0};
		
		AmplifierController controller = new AmplifierController(program);
		assertEquals(43210, controller.findMaxThrusterSignal(5));
	}
	
	@Test
	public void testCalculateMaxSignal2() {
		int[] program = {3,23,3,24,1002,24,10,24,1002,23,-1,23,
				101,5,23,23,1,24,23,23,4,23,99,0,0};
		
		AmplifierController controller = new AmplifierController(program);
		assertEquals(54321, controller.findMaxThrusterSignal(5));
	}
	
	@Test
	public void testCalculateMaxSignal3() {
		int[] program = {3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,
				1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0};
		
		AmplifierController controller = new AmplifierController(program);
		assertEquals(65210, controller.findMaxThrusterSignal(5));
	}
	
	@Test
	public void testCalculateSignal1_rewired() {
		int[] program = {3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,
				27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5};
		List<Integer> phaseSettings = Arrays.asList(9,8,7,6,5);
		
		AmplifierController controller = new AmplifierController(program);
		assertEquals(139629729, controller.runRewired(phaseSettings));
	}
	
	@Test
	public void testCalculateSignal2_rewired() {
		int[] program = {3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,
				-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,
				53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10};
		List<Integer> phaseSettings = Arrays.asList(9,7,8,5,6);
		
		AmplifierController controller = new AmplifierController(program);
		assertEquals(18216, controller.runRewired(phaseSettings));
	}
	
	//@Test Ignore test for now as it takes too long
	public static void testTask2() throws IOException {
		InputFileReader reader = new InputFileReader("Day7");
		int[] input = reader.readFileAsArrayOfIntegers();
		
		AmplifierController controller = new AmplifierController(input);
		assertEquals(3321777, controller.findMaxThrusterSignalRewired(5));
	}
	
	
	@Test
	public void testFindPermutations() {
		{
			List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
			List<List<Integer>> combinations = Utils.findPermutations(input);
			assertEquals(6, combinations.size());
			Set<String> combinationSet = combinations.stream().map(l -> Arrays.toString(l.toArray(new Integer[0]))).collect(Collectors.toSet());
			assertEquals(6, combinationSet.size());
		}
		
		{
			List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
			List<List<Integer>> combinations = Utils.findPermutations(input);
			assertEquals(24, combinations.size());
			Set<String> combinationSet = combinations.stream().map(l -> Arrays.toString(l.toArray())).collect(Collectors.toSet());
			assertEquals(24, combinationSet.size());
		}
		
		{
			List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
			List<List<Integer>> combinations = Utils.findPermutations(input);
			assertEquals(120, combinations.size());
			Set<String> combinationSet = combinations.stream().map(l -> Arrays.toString(l.toArray())).collect(Collectors.toSet());
			assertEquals(120, combinationSet.size());
		}
	}

}
