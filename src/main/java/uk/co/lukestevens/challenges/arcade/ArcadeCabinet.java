package uk.co.lukestevens.challenges.arcade;

import java.awt.Point;
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
	
	public long playGame(final boolean print) {
		IntCodeComputer computer = factory.createComputer();
		computer.getMemory().setValue(0, 2L);
		
		Wrapper<Long> score = new Wrapper<>(0L);
		OutputBuffer<Long> buffer = new OutputBuffer<Long>(3);
		buffer.setOutputCallback(out -> {
			if(out.get(0).equals(-1L)) {
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
		
		computer.setInputCallback(() -> {
			if(print) {
				System.out.println(this.screen
						.toString()
						.replace("0", ".")
						.replace("1", "X")
						.replace("2", "#")
						.replace("3", "_")
						.replace("4", "o"));
			}
			Point currentBallPosition = this.getBallPosition();
			int currentBumperPosition = this.getBumperPosition();
			Point nextBallPosition = this.getNextBallPosition(computer);
			
			if(nextBallPosition == null) {
				return 0L;
			}
			
			int input = this.normalise((int) (nextBallPosition.getX() - currentBumperPosition));
			if(this.screen.get((int) currentBallPosition.getX(), (int) (currentBallPosition.getY()+1)).intValue() == 3) {
				input = 0;
			}
				
			return (long) input;
		});
		computer.run();
		
		return score.get();
	}
	
	Point getNextBallPosition(IntCodeComputer computer) {
		IntCodeComputer ai = computer.deepClone();
		
		Wrapper<Point> ballPosition = new Wrapper<>();
		OutputBuffer<Long> buffer = new OutputBuffer<Long>(3);
		buffer.setOutputCallback(out -> {
			if(out.get(0).intValue() > -1 && out.get(2).intValue() == 4) {
				ballPosition.set(new Point(out.get(0).intValue(), out.get(1).intValue()));
				ai.halt();
			}
		});
		
		ai.useOutputBuffer(buffer);
		ai.setInputCallback(() -> 0L);
		ai.run();
		return ballPosition.get();
	}
	
	Point getBallPosition() {
		return this.getObjectPosition(4);
	}
	
	int getBumperPosition() {
		return (int) this.getObjectPosition(3).getX();
	}
	
	Point getObjectPosition(int obj) {
		return this.screen
				.stream()
				.filter(e -> e.getValue().equals(obj))
				.map(Entry::getKey)
				.findFirst()
				.get();
	}
	
	int normalise(int num) {
		return num < 0? -1 : num > 0? 1 : 0;
	}
	
	public Grid<Integer> getScreen(){
		return screen;
	}
	
	public long getBlockTiles() {
		return this.screen.stream().mapToInt(Entry::getValue).filter(i -> i == 2).count();
	}

}
