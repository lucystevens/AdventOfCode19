package uk.co.lukestevens.challenges.intcode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class IntCodeComputerMemory {
	
	private final List<Long> memory;
	private final AtomicInteger cursor = new AtomicInteger();
	private int relativeBase = 0;
	
	public IntCodeComputerMemory(Long[] program) {
		this.memory = new ArrayList<Long>();
		for(Long i : program) {
			memory.add(i);
		}
	}
	
	private IntCodeComputerMemory(List<Long> memory, int cursor, int relativeBase) {
		this.memory = memory;
		this.cursor.set(cursor);
		this.relativeBase = relativeBase;
	}
	
	public IntCodeComputerMemory deepClone() {
		List<Long> memoryCopy = memory.stream().map(Long::longValue).collect(Collectors.toList());
		return new IntCodeComputerMemory(memoryCopy, cursor.get(), relativeBase);
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
	public Long getValue(int index) {
		this.padToIndex(index);
		return memory.get(index);
	}
	
	public Long getRelativeValue(int offset) {
		return this.getValue(this.relativeBase + offset);
	}
	
	public Long getOffsetValue(int offset) {
		return this.getValue(cursor.get() + offset);
	}
	
	public Long getValue() {
		return this.getValue(cursor.get());
	}
	
	// Single point of entry
	public void setValue(int index, Long value) {
		this.padToIndex(index);
		memory.set(index, value);
	}
	
	public void setOffsetValue(int offset, Long value) {
		this.setValue(cursor.get() + offset, value);
	}
	
	public void setValue(Long value) {
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
	
	/*public boolean hasNext() {
		return cursor.get() < memory.size();
	}*/

	public List<Long> getBuffer() {
		return memory;
	}
	
	private void padToIndex(int index) {
		while(index >= this.memory.size()) {
			this.memory.add(0L);
		}
	}

}
