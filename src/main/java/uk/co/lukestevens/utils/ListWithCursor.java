package uk.co.lukestevens.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ListWithCursor<T> {
	
	private final AtomicInteger cursor = new AtomicInteger();
	private final List<T> list;

	public ListWithCursor(List<T> list) {
		this.list = list;
	};
	
	@SafeVarargs
	public ListWithCursor(T...values) {
		this(Arrays.asList(values));
	}
	
	public ListWithCursor() {
		this(new ArrayList<>());
	};
	
	public T next() {
		return list.get(cursor.getAndIncrement());
	}
	
	public boolean hasNext() {
		return cursor.get() < list.size();
	}
	
	public void reset() {
		this.cursor.set(0);
	}

}
