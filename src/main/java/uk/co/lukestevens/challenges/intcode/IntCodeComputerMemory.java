package uk.co.lukestevens.challenges.intcode;

import java.util.concurrent.atomic.AtomicInteger;

public class IntCodeComputerMemory {
	
	private final int[] memory;
	private final AtomicInteger cursor = new AtomicInteger();
	
	public IntCodeComputerMemory(int[] program) {
		this.memory = new int[program.length];
		for(int i = 0; i<program.length; i++) {
			memory[i] = program[i];
		}
	}
	
	public int getValue(int index) {
		return memory[index];
	}
	
	public int getOffsetValue(int offset) {
		return memory[cursor.get() + offset];
	}
	
	public int getValue() {
		return memory[cursor.get()];
	}
	
	public void setValue(int index, int value) {
		memory[index] = value;
	}
	
	public void setOffsetValue(int offset, int value) {
		memory[cursor.get() + offset] = value;
	}
	
	public void setValue(int value) {
		memory[cursor.get()] = value;
	}
	
	public void incrementCursor(int value) {
		cursor.addAndGet(value);
	}
	
	public void incrementCursor() {
		cursor.incrementAndGet();
	}
	
	public void jumpCursor(int position) {
		cursor.set(position);
	}
	
	public boolean hasNext() {
		return cursor.get() < memory.length;
	}

	public int[] getBuffer() {
		return memory;
	}
	
	

}
