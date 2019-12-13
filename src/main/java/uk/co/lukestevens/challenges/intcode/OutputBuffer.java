package uk.co.lukestevens.challenges.intcode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class OutputBuffer<T> {
	
	private final int bufferSize;
	private Consumer<List<T>> outputCallback;
	
	private List<T> buffer = new ArrayList<>();

	public OutputBuffer(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	public void setOutputCallback(Consumer<List<T>> outputCallback) {
		this.outputCallback = outputCallback;
	}
	
	boolean isFull() {
		return this.buffer.size() == this.bufferSize;
	}
	
	void add(T item) {
		this.buffer.add(item);
		if(this.isFull()) {
			this.outputCallback.accept(buffer);
			this.buffer = new ArrayList<>();
		}
	}

}
