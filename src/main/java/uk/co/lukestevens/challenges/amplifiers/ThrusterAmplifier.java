package uk.co.lukestevens.challenges.amplifiers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import uk.co.lukestevens.challenges.intcode.IntCodeComputer;

public class ThrusterAmplifier {
	
	private final int phaseSetting;
	private final IntCodeComputer computer;
	
	public ThrusterAmplifier(int phaseSetting, IntCodeComputer computer) {
		this.phaseSetting = phaseSetting;
		this.computer = computer;
	}
	
	public List<Integer> run(int input, Consumer<Integer> outputMethod){ 
		List<Integer> outputs = new ArrayList<>();
		List<Integer> inputs = Arrays.asList(phaseSetting, input);
		
		IntCodeComputer tempComputer = this.computer.clone();
		tempComputer.setOutputCallback(i -> {
			outputs.add(i);
			outputMethod.accept(i);
		});
		tempComputer.run(inputs);
		return outputs;
	}

}
