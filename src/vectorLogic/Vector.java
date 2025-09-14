package vectorLogic;

public class Vector {

	private double x, y;
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double magnitude() {
		return Math.sqrt(x*x + y*y);
	}
	
	public void normalize() {
		double magnitude = magnitude();
		
		if(magnitude != 0) {
			x/=magnitude;
			y/=magnitude;
		}
			
	}
	
	public void add(double x2, double y2) {
		x += x2;
		y += y2;
	}
	
	public void rotateVector(double angle) {
		x = x*(Math.cos(angle)) + y*(-Math.sin(angle));
		y = x*(Math.sin(angle)) + y*(Math.cos(angle));	
	}
	
	public double getAngle() {
		return Math.atan2(y, x); // angle from positive x axis
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	
}
