package uk.co.lukestevens.challenges;

import uk.co.lukestevens.challenges.intcode.IntCodeComputer;
import uk.co.lukestevens.challenges.intcode.IntCodeComputerFactory;
import uk.co.lukestevens.challenges.robot.Robot;
import uk.co.lukestevens.utils.Grid;
import uk.co.lukestevens.utils.Wrapper;

public class HullPaintingRobot {
	
	private final Grid<String> hull = new Grid<>();
	private final IntCodeComputerFactory factory;
	
	public HullPaintingRobot(Long[] input) {
		this.factory = new IntCodeComputerFactory(input);
	}
	
	public void paintPanel(long startPanelColour) {
		IntCodeComputer computer = factory.createComputer();
		Wrapper<Boolean> isPaintOutput = new Wrapper<>(true);
		Robot robot = new Robot();
		
		computer.addInput(startPanelColour);
		computer.setOutputCallback(out -> {
			if(isPaintOutput.get()) {
				this.hull.set(robot.getX(), robot.getY(), out == 0L? "X" : " ");
				isPaintOutput.set(false);
			}
			else {
				robot.move(out.intValue());
				String value = this.hull.get(robot.getX(), robot.getY());
				long panel = value == null || value.equals("X")? 0L : 1L; 
				computer.addInput(panel);
				isPaintOutput.set(true);
			}
		});
		
		computer.run();
	}
	
	public Grid<String> getHull() {
		return hull;
	}
	
	public long getPanelsPainted() {
		return hull.stream().count();
	}

}
