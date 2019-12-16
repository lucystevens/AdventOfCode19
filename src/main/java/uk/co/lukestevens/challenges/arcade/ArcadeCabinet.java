package uk.co.lukestevens.challenges.arcade;

import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uk.co.lukestevens.challenges.intcode.IntCodeComputer;
import uk.co.lukestevens.challenges.intcode.IntCodeComputerFactory;
import uk.co.lukestevens.challenges.intcode.OutputBuffer;
import uk.co.lukestevens.ui.VisualGrid;
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
		
		List<Long> inputs = new ArrayList<>();
		
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
		
		Wrapper<Point> lastBallPosition = new Wrapper<>();
		Wrapper<Point> predictedNextPosition = new Wrapper<>();
		computer.setInputCallback(() -> {
			System.out.println(this.screen
					.toString()
					.replace("0", ".")
					.replace("1", "X")
					.replace("2", "#")
					.replace("3", "_")
					.replace("4", "o"));
			/*JPanel panel = VisualGrid.getInputView("Score: " + score.get(), this.screen);
			String input = JOptionPane.showInputDialog(panel);
			
			if(input == null || input.isEmpty()) {
				inputs.add(0L);
				return 0L;
			};
			
			Long result = input.startsWith("a")? -1L : 1L;
			inputs.add(result);
			return result;*/
			Point currentBallPosition = this.getBallPosition();
			if(lastBallPosition.isNull()) {
				lastBallPosition.set(currentBallPosition);
				return 0L;
			}
			int currentBumperPosition = this.getBumperPosition();
			Point nextBallPosition = this.getNextBallPosition(lastBallPosition.get());
			
			int input = this.normalise((int) (nextBallPosition.getX() - currentBumperPosition));
			if(this.screen.get((int) currentBallPosition.getX(), (int) (currentBallPosition.getY()+1)).intValue() == 3) {
				input = 0;
				System.out.println("About to hit bumper");
			}
			
			System.out.println("Ball x: " + currentBallPosition.getX() +
					"\tNext x: " + nextBallPosition.getX() +
					"\tBumper x: " + currentBumperPosition +
					"\tInput: " + input);
			System.out.println();
			
			if(!predictedNextPosition.isNull() &&!currentBallPosition.equals(predictedNextPosition.get())) {
				System.err.println("Predicted position doesn't meet actual position");
			}
			predictedNextPosition.set(nextBallPosition);
			
			lastBallPosition.set(currentBallPosition);	
			return (long) input;
		});
		computer.run();
		
		return score.get();
	}
	
	Point getNextBallPosition(Point lastPosition) {
		Point currentPosition = this.getBallPosition();
		int xDirection = this.normalise((int) (currentPosition.getX() - lastPosition.getX()));
		int yDirection = this.normalise((int) (currentPosition.getY() - lastPosition.getY()));
		
		Integer valueAtNextPosition = this.screen.get(
				(int)(currentPosition.getX() + xDirection),
				(int)(currentPosition.getY() + yDirection));
		
		Integer valueAtNextXPosition = this.screen.get(
				(int)(currentPosition.getX() + xDirection),
				(int)(currentPosition.getY()));
		
		Integer valueAtNextYPosition = this.screen.get(
				(int)(currentPosition.getX()),
				(int)(currentPosition.getY() + yDirection));
		
		if(valueAtNextXPosition*valueAtNextYPosition>0) {
			return new Point((int)(currentPosition.getX() - xDirection),
					(int)(currentPosition.getY() - yDirection));
		}
		else if(valueAtNextXPosition.intValue() > 0) {
			return new Point((int)(currentPosition.getX() - xDirection),
					(int)(currentPosition.getY() + yDirection));
		}
		else if(valueAtNextYPosition.intValue() > 0) {
			return new Point((int)(currentPosition.getX() + xDirection),
					(int)(currentPosition.getY() - yDirection));
		}
		else if(valueAtNextPosition > 0) {
			return new Point((int)(currentPosition.getX() - xDirection),
					(int)(currentPosition.getY() - yDirection));
		}
		else {
			return new Point((int)(currentPosition.getX() + xDirection),
					(int)(currentPosition.getY() + yDirection));
		}
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
