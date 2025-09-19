package vectorLogic;

public class Vector {

	private double x, y;
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

    // ----------------- Basic operations -----------------

	public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public double getAngle() {
        return Math.atan2(y, x);
    }

    public double dot(Vector v) {
        return this.x * v.x + this.y * v.y;
    }

    public double angleBetween(Vector v) {
        double magProduct = this.magnitude() * v.magnitude();
        if(magProduct == 0) return 0;
        return Math.acos(dot(v) / magProduct);
    }
	
    // ----------------- Mutating operations -----------------
	
    public void add(Vector v) {
        x += v.x;
        y += v.y;
    }

    public void subtract(Vector v) {
        x -= v.x;
        y -= v.y;
    }

    public void scale(double s) {
        x *= s;
        y *= s;
    }

    public void divide(double d) {
        if(d != 0) {
            x /= d;
            y /= d;
        }
    }

    public void normalize() {
        double m = magnitude();
        if(m != 0) {
            x /= m;
            y /= m;
        }
    }

    public void rotate(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double newX = x * cos - y * sin;
        double newY = x * sin + y * cos;
        x = newX;
        y = newY;
    }
    
    // ----------------- Non-mutating operations -----------------

    public Vector added(Vector v) {
        return new Vector(x + v.x, y + v.y);
    }

    public Vector subtracted(Vector v) {
        return new Vector(x - v.x, y - v.y);
    }

    public Vector scaled(double s) {
        return new Vector(x * s, y * s);
    }

    public Vector divided(double d) {
        return (d != 0) ? new Vector(x / d, y / d) : new Vector(0, 0);
    }

    public Vector normalized() {
        double m = magnitude();
        return (m != 0) ? new Vector(x / m, y / m) : new Vector(0, 0);
    }

    public Vector rotated(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return new Vector(x * cos - y * sin, x * sin + y * cos);
    }

    // ----------------- Getters & Setters -----------------
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
