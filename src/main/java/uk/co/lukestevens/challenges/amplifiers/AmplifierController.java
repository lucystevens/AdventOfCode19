package uk.co.lukestevens.challenges.amplifiers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import uk.co.lukestevens.challenges.intcode.IntCodeComputer;
import uk.co.lukestevens.challenges.intcode.IntCodeComputerFactory;
import uk.co.lukestevens.utils.Utils;
import uk.co.lukestevens.utils.Wrapper;

public class AmplifierController {
	
	private final IntCodeComputerFactory factory;
	
	public AmplifierController(int[] program) {
		this.factory = new IntCodeComputerFactory(program);
	}
	
	public int run(List<Integer> phaseSettings) {
		Long nextInput = 0L;
		for(int phaseSetting : phaseSettings) {
			IntCodeComputer tempComputer = this.factory.createComputer();
			Wrapper<Long> outputBuffer = new Wrapper<>();
			
			tempComputer.setOutputCallback(outputBuffer::set);
			tempComputer.addInput(Long.valueOf(phaseSetting));
			tempComputer.addInput(nextInput);
			tempComputer.run();
			
			nextInput = outputBuffer.get();
		}
		return nextInput.intValue();
	}
	
	public int runRewired(List<Integer> phaseSettings) {
		Map<Integer, Long> outputMap = new HashMap<>();
		List<Thread> threads = new ArrayList<>();
		
		List<IntCodeComputer> comps = phaseSettings.stream()
				.map(phaseSetting -> {
					IntCodeComputer tempComputer = this.factory.createComputer();
					tempComputer.addInput(Long.valueOf(phaseSetting));
					return tempComputer;
		}).collect(Collectors.toList());
		
		comps.get(0).addInput(0L);
		
		for(int i = 0; i<comps.size(); i++) {
			IntCodeComputer tempComputer = comps.get(i);
			int nextInt = i <comps.size() - 1 ? i+1 : 0;
			int thisIndex = i;
			tempComputer.setOutputCallback(output -> {
				comps.get(nextInt).addInput(output);
				outputMap.put(thisIndex, output);
			});
			Thread t = new Thread(tempComputer::run);
			t.start();
			threads.add(t);
		}
		
		while(threads.stream().anyMatch(Thread::isAlive)) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return outputMap.get(comps.size() - 1).intValue();
		
	}
	
	public int findMaxThrusterSignal(int amplifiers) {
		List<Integer> range = IntStream.range(0, amplifiers).mapToObj(i -> i).collect(Collectors.toList());
		List<List<Integer>> combinations = Utils.findPermutations(range);
		int maxSignal = 0;
		for(List<Integer> phaseSettings : combinations) {
			int signal = this.run(phaseSettings);
			if(signal > maxSignal) {
				maxSignal = signal;
			}
		}
		return maxSignal;
	}
	
	public int findMaxThrusterSignalRewired(int amplifiers) {
		List<Integer> range = IntStream.range(5, amplifiers+5).mapToObj(i -> i).collect(Collectors.toList());
		List<List<Integer>> combinations = Utils.findPermutations(range);
		int maxSignal = 0;
		for(List<Integer> phaseSettings : combinations) {
			int signal = this.runRewired(phaseSettings);
			if(signal > maxSignal) {
				maxSignal = signal;
			}
		}
		return maxSignal;
	}

}
