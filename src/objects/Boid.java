package objects;

import java.awt.Color;
import java.awt.Graphics2D;

import main.Simulation;
import vectorLogic.Vector;

public class Boid {

	private Simulation sim;

	private Vector velocity;
	private Vision vision;
	
	private Color color;
	
	private double x, y;
	private double width = 6, height = 12;
		
	private boolean isTurning = false;
	private double targetAngle;
	
	private double visionRadius = 60;
	private double visionAngle = 120; // degrees
	
	public Boid(Simulation sim, double x, double y) {
		this.x = x;
		this.y = y;
		this.sim = sim;
		this.velocity = setRandomVelocity();
		this.vision = new Vision(this, visionRadius, visionAngle);
		
	}
	
	public void update() {
		x += velocity.getX();
		y += velocity.getY();
		
		checkCollisions();
		
		turn();
	}
	
	private void turn() {
	    // occasionally pick a new target angle
	    if(!isTurning && Math.random() < 0.004) {
	        double randOffset = Math.toRadians((Math.random() * 60) - 30);
	        double currentAngle = velocity.getAngle();
	        targetAngle = currentAngle + randOffset;
	        isTurning = true;
	    }

	    if(isTurning) {
		    graduallyTurn(targetAngle);
	    }
	}

	private void graduallyTurn(double targetAngle) {
	    double turnRate = Math.toRadians(0.3);
	    double currentAngle = velocity.getAngle();

	    double diff = targetAngle - currentAngle;
	    diff = Math.atan2(Math.sin(diff), Math.cos(diff)); // normalize

	    double speed = velocity.magnitude();

	    if(Math.abs(diff) <= turnRate) {
	        // reached the target
	        velocity.setX(Math.cos(targetAngle) * speed);
	        velocity.setY(Math.sin(targetAngle) * speed);
	        isTurning = false; // clear it, so a new one can be chosen later
	    } else {
	        double newAngle = currentAngle + Math.signum(diff) * turnRate;
	        velocity.setX(Math.cos(newAngle) * speed);
	        velocity.setY(Math.sin(newAngle) * speed);
	    }
	}
	
	private void checkCollisions() {
	    if(x < 0) {
	        x = sim.getSimWidth();
	    } else if(x > sim.getSimWidth()) {
	        x = 0;
	    }

	    if(y < 0) {
	        y = sim.getSimHeight();
	    } else if(y > sim.getSimHeight()) {
	        y = 0;
	    }
	}

	private Vector setRandomVelocity() {
		
		double vX = Math.random() * 2 - 1;
		double vY = Math.random() * 2 - 1;
		
		Vector randVelocity = new Vector(vX, vY);
//		randVelocity.normalize();
		
		return randVelocity;
	}
	
	public void draw(Graphics2D g2d) {
		
		vision.draw(g2d);
		
	    g2d.setColor(Color.black);
	    
	    int[] xVerticies = {(int) x, (int) (x + width/2), (int) (x - width/2)};
	    int[] yVerticies = {(int) (y-height/2), (int) (y + height/2), (int) (y + height/2)};
	    
	    int[] xVerticiesRotated = new int[3];
	    int[] yVerticiesRotated = new int[3];
	    
	    
	    // polygon rotated by velocity vector direction
	    double angle = velocity.getAngle() + Math.PI/2; // so 0 rads corresponds to the triangle pointing up
	    
	    for(int i = 0; i < 3; i++) {
	    	double dx = xVerticies[i] - x;
	    	double dy = yVerticies[i] - y;
	    	
	    	double dxRot = dx*(Math.cos(angle)) - dy*(Math.sin(angle));
	    	double dyRot = dx*(Math.sin(angle)) + dy*(Math.cos(angle));
	    	
	    	xVerticiesRotated[i] = (int) (dxRot + x);
	    	yVerticiesRotated[i] = (int) (dyRot + y);
	    	
	    }
	    
	    g2d.fillPolygon(xVerticiesRotated, yVerticiesRotated, 3);
	    
	}

	public void setColor(Color color) {
		this.color = color;
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

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public Vector getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}
	
	public Vision getVision() {
		return vision;
	}

	
	
}
