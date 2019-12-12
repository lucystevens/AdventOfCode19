package uk.co.lukestevens.utils;

import java.util.Objects;

public class Point3D {
	
	private int x;
	private int y;
	private int z;
	
	public Point3D(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Point3D(Point3D point) {
		this.x = point.getX();
		this.y = point.getY();
		this.z = point.getZ();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	public void translate(int dx, int dy, int dz) {
		this.x += dx;
		this.y += dy;
		this.z += dz;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Point3D) {
			Point3D point = (Point3D) obj;
			return point.getX() == this.x &&
				   point.getY() == this.y &&
				   point.getZ() == this.z;
		}
		else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}

	@Override
	public String toString() {
		return "Point3D(" + x + "," + y + "," + z + ")";
	}

}
