package uk.co.lukestevens.challenges.arcade;

import java.util.Map.Entry;

import uk.co.lukestevens.challenges.intcode.IntCodeComputer;
import uk.co.lukestevens.challenges.intcode.IntCodeComputerFactory;
import uk.co.lukestevens.challenges.intcode.OutputBuffer;
import uk.co.lukestevens.utils.Grid;
import uk.co.lukestevens.utils.Wrapper;

public class ArcadeCabinet {
	
	private final IntCodeComputerFactory factory;
	private final Grid<Integer> screen = new Grid<>();
	
	public ArcadeCabinet(Long[] program) {
		this.factory = new IntCodeComputerFactory(program);
	}
	
	public void drawGame() {
		OutputBuffer<Long> buffer = new OutputBuffer<Long>(3);
		buffer.setOutputCallback(out -> 
			this.screen.set(out.get(0).intValue(), out.get(1).intValue(), out.get(2).intValue())
		);
		IntCodeComputer computer = factory.createComputer();
		computer.useOutputBuffer(buffer);
		computer.run();
	}
	
	public long playGame() {
		IntCodeComputer computer = factory.createComputer();
		computer.getMemory().setValue(0, 2L);
		
		for(int i = 0; i<1000; i++) {
			for(int j = 0; j<50; j++) {
				computer.addInput(-1L);
			}
			for(int j = 0; j<50; j++) {
				computer.addInput(1L);
			}
		}
		
		Wrapper<Long> score = new Wrapper<>();
		OutputBuffer<Long> buffer = new OutputBuffer<Long>(3);
		buffer.setOutputCallback(out -> {
			System.out.println(out);
			if(out.get(0).equals(-1L)) {
				System.out.println("Score: " + out.get(2));
				score.set(out.get(2));
			}
			else {
				this.screen.set(
					out.get(0).intValue(),
					out.get(1).intValue(),
					out.get(2).intValue()
				);
			}
		});
		computer.useOutputBuffer(buffer);
		computer.run();
		
		return score.get();
	}
	
	public Grid<Integer> getScreen(){
		return screen;
	}
	
	public long getBlockTiles() {
		return this.screen.stream().mapToInt(Entry::getValue).filter(i -> i == 2).count();
	}

}
