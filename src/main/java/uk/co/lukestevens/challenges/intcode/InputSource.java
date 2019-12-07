package uk.co.lukestevens.challenges.intcode;

import java.util.ArrayList;
import java.util.List;

public class InputSource<T> {
	
	private final List<T> queue = new ArrayList<>();
	
	public void add(T item) {
		this.queue.add(item);
	}
	
	public T get() {
		while(queue.isEmpty()) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return queue.remove(0);
	}

}
