package uk.co.lukestevens.utils;

import java.util.Objects;

/**
 * A wrapper class for allowing objects to be set within lambda functions
 * 
 * @author luke.stevens
 *
 * @param <T> The type of object that this wraps
 */
public class Wrapper<T> {
	
	private T value;
	
	/**
	 * Creates a new wrapper without a value
	 */
	public Wrapper(){
		this(null);
	}
	
	/**
	 * Creates a wrapper with an initial value
	 * @param value The initial value of the wrapped object
	 */
	public Wrapper(T value){
		this.value = value;
	}
	
	/**
	 * @return The wrapped instance
	 */
	public T get() {
		return value;
	}

	/**
	 * Set the wrapped instance
	 * @param value
	 */
	public void set(T value) {
		this.value = value;
	}
	
	/**
	 * @return true if the wrapped instance is null
	 */
	public boolean isNull() {
		return value == null;
	}
	
	@Override
	public String toString() {
		return value == null? "null" : value.toString();
	}
	
	@Override
	public int hashCode() {
		return value == null? 0 : value.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return Objects.equals(obj, value);
	}
}
