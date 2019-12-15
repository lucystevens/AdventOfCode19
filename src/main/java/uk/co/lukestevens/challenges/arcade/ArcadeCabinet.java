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
		
		Wrapper<Integer> lastBallPosition = new Wrapper<>(0);
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
			if(input == null) {
				System.exit(-1);
			}
			
			Long parsedInput = Long.valueOf(input);
			inputs.add(parsedInput);
			return parsedInput;*/
			Point currentBallPosition = this.getBallPosition();
			int currentBumperPosition = (int) this.getBumperPosition().getX();
			int ballDirection = (int) ((currentBallPosition.getX() - lastBallPosition.get())%2);
			int nextBallPosition = (int) (currentBallPosition.getX() + ballDirection);
			
			if(this.screen.get(nextBallPosition, (int) currentBallPosition.getY()).intValue() > 0){
				nextBallPosition = (int) currentBallPosition.getX();
			}
			
			int input = (nextBallPosition - currentBumperPosition)%2;
			System.out.println(input);
			
			lastBallPosition.set((int) currentBallPosition.getX());	
			return 0L;
		});
		computer.run();
		
		
		
		return score.get();
	}
	
	Point getBallPosition() {
		return this.getObjectPosition(4);
	}
	
	Point getBumperPosition() {
		return this.getObjectPosition(3);
	}
	
	Point getObjectPosition(int obj) {
		return this.screen
				.stream()
				.filter(e -> e.getValue().equals(obj))
				.map(Entry::getKey)
				.findFirst()
				.get();
	}
	
	public Grid<Integer> getScreen(){
		return screen;
	}
	
	public long getBlockTiles() {
		return this.screen.stream().mapToInt(Entry::getValue).filter(i -> i == 2).count();
	}

}
