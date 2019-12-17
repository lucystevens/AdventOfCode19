package uk.co.lukestevens.challenges.scaffold;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import uk.co.lukestevens.challenges.intcode.IntCodeComputer;
import uk.co.lukestevens.challenges.intcode.IntCodeComputerFactory;
import uk.co.lukestevens.utils.Grid;
import uk.co.lukestevens.utils.Wrapper;

public class ScaffoldMap {
	
	private final IntCodeComputerFactory factory;
	private final Grid<String> map = new Grid<>();
	
	public ScaffoldMap(Long[] input) {
		this.factory = new IntCodeComputerFactory(input);
		this.draw();
	}
	
	void draw() {
		IntCodeComputer computer = factory.createComputer();
		
		Point currentPoint = new Point();
		computer.setOutputCallback(out -> {
			if(out == 10) {
				currentPoint.setLocation(0.0, currentPoint.getY() + 1);;
			}
			else {
				String value = String.valueOf(((char)out.intValue()));
				map.set(currentPoint, value);
				currentPoint.translate(1, 0);
			}
		});
		
		computer.run();
	}
	
	public List<Point> getAllIntersections(){
		return this.map
			.stream()
			.filter(e -> e.getValue().equals("#"))
		    .map(Entry::getKey)
		    .filter(point -> {
		    	String up = this.map.get(point.getX(), point.getY()-1);
		    	String right = this.map.get(point.getX()+1, point.getY());
		    	String down = this.map.get(point.getX(), point.getY()+1);
		    	String left = this.map.get(point.getX()-1, point.getY());
		    	
		    	return up != null && up.equals("#")
		    		&& right != null && right.equals("#")
		    		&& down != null && down.equals("#")
		    		&& left != null && left.equals("#");
		    })
		    .collect(Collectors.toList());
	}
	
	public void markIntersections() {
		this.getAllIntersections().forEach(p -> this.map.set(p, "O"));
	}
	
	public int getAlignmentSum() {
		return this.getAllIntersections().stream().mapToInt(p -> (int)(p.getX() * p.getY())).sum();
	}
	
	public Grid<String> getMap(){
		return map;
	}
	
	public Long moveVacuumRobot(String functionOrder, String functionA, String functionB, String functionC) {
		IntCodeComputer computer = factory.createComputer();
		computer.getMemory().setValue(0, 2L);
		
		this.getInputFromString(functionOrder).forEach(computer::addInput);
		this.getInputFromString(functionA).forEach(computer::addInput);
		this.getInputFromString(functionB).forEach(computer::addInput);
		this.getInputFromString(functionC).forEach(computer::addInput);
		computer.addInput((long)'n');
		computer.addInput(10L);
		
		Wrapper<Long> output = new Wrapper<>(0L);
		computer.setOutputCallback(output::set);
		computer.run();
		return output.get();
	}
	
	List<Long> getInputFromString(String function){
		List<Long> input = new ArrayList<>();
		for(char c : function.toCharArray()) {
			input.add((long)c);
		}
		input.add(10L);
		return input;
	}

}
