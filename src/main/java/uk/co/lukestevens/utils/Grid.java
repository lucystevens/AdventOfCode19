package uk.co.lukestevens.utils;

import java.awt.Point;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Stream;

public class Grid<T> {
	
	private final HashMap<Point, T> internalMap = new HashMap<>();
	
	protected int minX = 0;
	protected int minY = 0;
	protected int maxX = 0;
	protected int maxY = 0;
	
	
    /**
     * Associates the specified value with the specified point in this grid.
     * If the grid previously contained a value at this point, the old
     * value is replaced.
     *
     * @param point point at which the new value will be inserted
     * @param value value to be inserted at the specified point
     */
	public void set(Point point, T value) {
		minX = (int) Math.min(minX, point.getX());
		minY = (int) Math.min(minY, point.getY());
		maxX = (int) Math.max(maxX, point.getX());
		maxY = (int) Math.max(maxY, point.getY());
		
		this.internalMap.put(new Point(point), value);
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
		
		Comparator<Double> sortMin = (d1, d2) -> (int)(d1 - d2);
		Comparator<Double> sortMax = (d1, d2) -> (int)(d2 - d1);
		
		int removedX = (int) point.getX();
		int removedY = (int) point.getY();
		
		if(removedX == this.minX) {
			this.minX = this.getMinMax(sortMin, Point::getX);
		}
		if(removedX == this.maxX) {
			this.minX = this.getMinMax(sortMax, Point::getX);
		}
		if(removedY == this.minY) {
			this.minX = this.getMinMax(sortMin, Point::getY);
		}
		if(removedY == this.maxY) {
			this.minX = this.getMinMax(sortMax, Point::getY);
		}
		
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
	
	public T get(double x, double y) {
		return this.get((int)x, (int)y);
	}
	
	/**
	 * @return A sequential stream with the collection backing this Grid as it's source
	 */
	public Stream<Entry<Point, T>> stream(){
		return this.internalMap.entrySet().stream();
	}
	
	public Grid<T> deepClone(){
		Grid<T> clone = new Grid<>();
		this.stream().forEach(e -> clone.set(e.getKey(), e.getValue()));
		return clone;
	}
	
	int getMinMax(Comparator<Double> sort, Function<Point, Double> axis) {
		return this.stream()
				.map(Entry::getKey)
				.map(axis)
				.sorted(sort)
				.findFirst()
				.orElse(0.0)
				.intValue();
	}
	
	public int getMinX() {
		return minX;
	}

	public int getMinY() {
		return minY;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	@Override
	public String toString() {
		return this.toString(" ");
	}
	
	public String toString(String nulls) {
		StringBuilder builder = new StringBuilder();
		for(int y = minY; y <= maxY; y++) {
			for(int x = minX; x <= maxX; x++) {
				T value = this.get(x, y);
				builder.append(value == null? nulls : value.toString());
			}
			builder.append("\n");
		}
		return builder.toString();
	}
	
	

}
