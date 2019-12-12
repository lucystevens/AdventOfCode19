package uk.co.lukestevens.challenges.moons;

import uk.co.lukestevens.utils.Point3D;

public class Moon {
	
	private Point3D location;
	private Point3D velocity = new Point3D(0, 0, 0);
	
	public Moon(Point3D location) {
		this.location = location;
	}
	
	public Moon(int x, int y, int z) {
		this(new Point3D(x, y, z));
	}
	
	public Point3D getLocation() {
		return location;
	}
	
	public Point3D getVelocity() {
		return velocity;
	}
	
	public int getPotentialEnergy() {
		return Math.abs(location.getX()) + Math.abs(location.getY()) + Math.abs(location.getZ());
	}
	
	public int getKineticEnergy() {
		return Math.abs(velocity.getX()) + Math.abs(velocity.getY()) + Math.abs(velocity.getZ());
	}
	
	public int getTotalEnergy() {
		return this.getKineticEnergy() * this.getPotentialEnergy();
	}
	
	@Override
	public String toString() {
		return "pos=" + this.location + ", vel=" + this.velocity;
	}

}
