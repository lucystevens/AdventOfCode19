package uk.co.lukestevens.utils;

import java.awt.Point;
import java.util.HashMap;

public class Grid<T> {
	
	private final HashMap<Point, T> internalMap = new HashMap<>();
	
    /**
     * Associates the specified value with the specified point in this grid.
     * If the grid previously contained a value at this point, the old
     * value is replaced.
     *
     * @param point point at which the new value will be inserted
     * @param value value to be inserted at the specified point
     */
	public void set(Point point, T value) {
		this.internalMap.put(point, value);
	}
	
    /**
     * Associates the specified value with the specified point in this grid, 
     * determined by a set of xy coordinates.
     * If the grid previously contained a value at this point, the old
     * value is replaced.
     *
     * @param x x coordinate for the point at which the new value will be inserted
     * @param y y coordinate for the point at which the new value will be inserted
     * @param value value to be inserted at the specified point
     */
	public void set(int x, int y, T value) {
		this.set(new Point(x, y), value);
	}
	
    /**
     * Removes the value from the specified point if present
     *
     * @param point point at which to remove the value
     */
	public void remove(Point point) {
		this.internalMap.remove(point);
	}
	
    /**
     * Removes the value from a specified point, determined by a
     * set of xy coordinates, if present
     *
     * @param x x coordinate for the point at which to remove the value
     * @param y y coordinate for the point at which to remove the value
     */
	public void remove(int x, int y) {
		this.remove(new Point(x, y));
	}
	
	/**
	 * Returns the value at the specified point, or null if there is no value.
	 * @param point point whose value is to be returned
	 * @return the value at the specified point, or null if there is no value.
	 */
	public T get(Point point) {
		return this.internalMap.get(point);
	}
	
	/**
	 * Returns the value at the specified point, determined by a
     * set of xy coordinate, or null if there is no value.
	 * @param point point whose value is to be returned
	 * @return the value at the specified point, or null if there is no value.
	 */
	public T get(int x, int y) {
		return this.get(new Point(x, y));
	}

}
