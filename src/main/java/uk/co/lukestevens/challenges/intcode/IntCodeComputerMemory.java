package uk.co.lukestevens.challenges.intcode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class IntCodeComputerMemory {
	
	private final List<Integer> memory;
	private final AtomicInteger cursor = new AtomicInteger();
	private int relativeBase = 0;
	
	public IntCodeComputerMemory(int[] program) {
		this.memory = new ArrayList<Integer>();
		for(int i : program) {
			memory.add(i);
		}
	}
	
	public void modifyRelativeBase(int offset) {
		this.relativeBase = this.relativeBase + offset;
	}
	
	protected int getRelativeBase() {
		return relativeBase;
	}

	public int getCursorPosition() {
		return cursor.get();
	}
	
	// Single point of entry
	public int getValue(int index) {
		return memory.get(index);
	}
	
	public int getRelativeValue(int offset) {
		return this.getValue(this.relativeBase + offset);
	}
	
	public int getOffsetValue(int offset) {
		return this.getValue(cursor.get() + offset);
	}
	
	public int getValue() {
		return this.getValue(cursor.get());
	}
	
	// Single point of entry
	public void setValue(int index, int value) {
		memory.set(index, value);
	}
	
	public void setOffsetValue(int offset, int value) {
		this.setValue(cursor.get() + offset, value);
	}
	
	public void setValue(int value) {
		this.setValue(cursor.get(), value);
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
		return cursor.get() < memory.size();
	}

	public Integer[] getBuffer() {
		return memory.toArray(new Integer[0]);
	}
	
	

}
