package uk.co.lukestevens.challenges.fft;

import java.util.ArrayList;
import java.util.List;
import uk.co.lukestevens.utils.Utils;

public class FlawedFrequencyTransmission {
	
	private final int[] pattern = {0, 1, 0, -1};

	// Position is 1-indexed
	public List<Integer> getPattern(int position){
		List<Integer> pattern = new ArrayList<>();
		for(int i : this.pattern) {
			for(int x = 0; x<position; x++) {
				pattern.add(i);
			}
		}
		return pattern;
	}
	
	public List<Integer> processPhases(List<Integer> input, int phases){
		for(int i = 0; i<phases; i++) {
			input = this.processPhase(input);
		}
		return input;
	}
	
	public List<Integer> processPhase(List<Integer> input){
		List<Integer> output = new ArrayList<>();
		for(int i=0; i<input.size(); i++) {
			int sum = 0;
			List<Integer> pattern = this.getPattern(i+1);
			for(int j=0; j<input.size(); j++) {
				int patternIndex = j<pattern.size()-1? j+1 : (j+1)%pattern.size();
				sum += input.get(j)*pattern.get(patternIndex);
			}
			
			output.add(Math.abs(sum%10));
		}
		
		return output;
	}
	
	public String decodeSignal(List<Integer> input) {
		List<Integer> repeatedInput = new ArrayList<>();
		for(int i = 0; i<10000; i++) {
			repeatedInput.addAll(input);
		}
		
		int messageOffset = Integer.parseInt(Utils.asString(input.subList(0, 7)));
		List<Integer> signal = repeatedInput.subList(messageOffset, repeatedInput.size());

		for(int i = 1; i <= 100; i++) {
			int result = 0;
			for(int index = signal.size() - 1; index >= 0; index--) {
				result += signal.get(index);
				result = Math.abs(result%10);
				signal.set(index, result);
			}
		}

		return Utils.asString(signal.subList(0, 8));
	}

}
