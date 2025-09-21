package objects;

import java.awt.Color;
import java.awt.Graphics2D;

import boidLogic.CollisionLogic;
import boidLogic.FlockingLogic;
import boidLogic.MovementLogic;
import boidLogic.Vision;
import main.Simulation;
import vectorLogic.Vector;

public class Boid {

	private Simulation sim;

	private MovementLogic movementLogic;
	private FlockingLogic flockingLogic;
	private CollisionLogic collisionLogic;
	
	private Vector velocity;
	private Vision vision;
	
	private double x, y;
	private double width = 6, height = 12;
		
	private boolean isTurning = false;
	private double targetAngle;
	
	private double visionRadius = 100;
	private double visionAngle = 240; // degrees
	
	private double minSpeed = .2*2;
	private double maxSpeed = .4*2;
	
	private double roamingTurnRate = Math.toRadians(0.3);
	private double targetedTurnRate = Math.toRadians(0.6);
	
	public Boid(Simulation sim, double x, double y) {
		this.x = x;
		this.y = y;
		this.sim = sim;
		this.vision = new Vision(this, visionRadius, visionAngle);
		
		movementLogic = new MovementLogic(this);
		collisionLogic = new CollisionLogic(this);
		flockingLogic = new FlockingLogic(this);

		this.velocity = movementLogic.setRandomVelocity();
		
	}
	
	public void update() {
	
		flockingLogic.alignment();
		flockingLogic.cohesion();
		
//		collisionLogic.avoidWallsUsingVision();
		movementLogic.turn();          // adjust velocity first
		movementLogic.clampSpeed();     // ensure speed is within limits
		x += velocity.getX();
		y += velocity.getY();
		collisionLogic.checkCollisions();

	}


	public void draw(Graphics2D g2d) {
		
//		vision.draw(g2d);
		
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

	public void turnTowardsAngle(double targetAngle, double turnRate) {
	    movementLogic.graduallyTurn(targetAngle, turnRate);
	}
	
	public Simulation getSim() {
		return sim;
	}

	public void setSim(Simulation sim) {
		this.sim = sim;
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

	public void setVision(Vision vision) {
		this.vision = vision;
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

	public boolean isTurning() {
		return isTurning;
	}

	public void setTurning(boolean isTurning) {
		this.isTurning = isTurning;
	}

	public double getTargetAngle() {
		return targetAngle;
	}

	public void setTargetAngle(double targetAngle) {
		this.targetAngle = targetAngle;
	}

	public double getVisionRadius() {
		return visionRadius;
	}

	public void setVisionRadius(double visionRadius) {
		this.visionRadius = visionRadius;
	}

	public double getVisionAngle() {
		return visionAngle;
	}

	public void setVisionAngle(double visionAngle) {
		this.visionAngle = visionAngle;
	}

	public double getMinSpeed() {
		return minSpeed;
	}

	public void setMinSpeed(double minSpeed) {
		this.minSpeed = minSpeed;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public double getRoamingTurnRate() {
		return roamingTurnRate;
	}

	public void setRoamingTurnRate(double roamingTurnRate) {
		this.roamingTurnRate = roamingTurnRate;
	}

	public double getTargetedTurnRate() {
		return targetedTurnRate;
	}

	public void setTargetedTurnRate(double targetedTurnRate) {
		this.targetedTurnRate = targetedTurnRate;
	}
	
}

//public class Boid {
//
//	private Simulation sim;
//
//	private Vector velocity;
//	private Vision vision;
//	
//	private double x, y;
//	private double width = 6, height = 12;
//		
//	private boolean isTurning = false;
//	private double targetAngle;
//	
//	private double visionRadius = 100;
//	private double visionAngle = 240; // degrees
//	
//	private double minSpeed = .2*2;
//	private double maxSpeed = .4*2;
//	
//	private double roamingTurnRate = Math.toRadians(0.3);
//	private double targetedTurnRate = Math.toRadians(0.6);
//	
//	public Boid(Simulation sim, double x, double y) {
//		this.x = x;
//		this.y = y;
//		this.sim = sim;
//		this.velocity = setRandomVelocity();
//		this.vision = new Vision(this, visionRadius, visionAngle);
//		
//	}
//	
//	public void update() {
//	
//		avoidWallsUsingVision();
//		turn();          // adjust velocity first
//		clampSpeed();     // ensure speed is within limits
//		x += velocity.getX();
//		y += velocity.getY();
//		checkCollisions();
//
//	}
//
//	// Just walls for now
//	private void avoidWallsUsingVision() {
//	   
//		double startAngle = velocity.getAngle() - Math.toRadians(visionAngle)/2;
//		double endAngle = velocity.getAngle() + Math.toRadians(visionAngle)/2;
//		int totalPoints = 20;
//		double deltaAngle =  Math.toRadians(visionAngle/totalPoints);
//		
//		for(double angle = startAngle; angle <= endAngle; angle+=deltaAngle) {
//			// calculate ray endpoint
//			double rayX = x + visionRadius * Math.cos(angle);
//			double rayY = y + visionRadius * Math.sin(angle);
//			
//		}		
//	}
//	
//	private void checkCollisions() {
//	    if(x < 0) {
//	        x = sim.getSimWidth();
//	    } else if(x > sim.getSimWidth()) {
//	        x = 0;
//	    }
//
//	    if(y < 0) {
//	        y = sim.getSimHeight();
//	    } else if(y > sim.getSimHeight()) {
//	        y = 0;
//	    }
//	}
//	
//	private void clampSpeed() {
//		double speed = velocity.magnitude();
//		
//		if(speed < minSpeed) {
//			velocity = velocity.normalized().scaled(minSpeed);
//		} else if(speed > maxSpeed) { 
//			velocity = velocity.normalized().scaled(maxSpeed);
//		}
//	}
//	
//	public void alignment() {
//		
//		Vector averageVelocity = new Vector(0, 0);
//		int boidsInVision = 0;
//		double averageSpeed = 0;
//		
//		for(Boid boid : sim.getBoids()) {
//			if(boid == this) 
//				continue;
//			
//			if(this.getVision().inVisionRange(boid.getX(), boid.getY())) {
//				boidsInVision++;
//				averageVelocity.add(boid.getVelocity());
//				averageSpeed += boid.getVelocity().magnitude();
//			}
//		}
//		
//		if(boidsInVision > 0) {
//			
//			averageVelocity.divide(boidsInVision);
//			averageSpeed /= boidsInVision;
//			
//			// set the boids velocity to turn gradually to new velocity
//			Vector desiredVelocity = averageVelocity.normalized();
//			desiredVelocity.scale(averageSpeed);
//			
//			double angle = desiredVelocity.getAngle();
//			graduallyTurn(angle, targetedTurnRate);
//			
//		}
//		
//	}
//	
//	public void cohesion() {
//		
//		Vector averagePosition = new Vector(0, 0);
//		double averageSpeed = 0;
//		int boidsInVision = 0;
//		
//		for(Boid boid : sim.getBoids()) {
//			if(boid == this)
//				continue;
//			
//			if(this.getVision().inVisionRange(boid.getX(), boid.getY())) {
//				boidsInVision++;
//				Vector boidsPosition = new Vector(boid.getX(), boid.getY());
//				averagePosition.add(boidsPosition);
//				averageSpeed += boid.getVelocity().magnitude();
//			}
//			
//		}
//		
//		if(boidsInVision > 0) {
//			averagePosition.divide(boidsInVision);
//			averageSpeed /= boidsInVision;
//			
//			double dx = averagePosition.getX() - this.x;
//			double dy = averagePosition.getY() - this.y;
//			
//			Vector desiredDirection = new Vector(dx, dy).normalized();
//			
//			double angle = desiredDirection.getAngle();
//			
//			graduallyTurn(angle, targetedTurnRate*.9);
//			
//		}
//		
//	}
//	
//	private void graduallyTurn(double targetAngle, double turnRate) {
//	    double currentAngle = velocity.getAngle();
//
//	    double diff = targetAngle - currentAngle;
//	    diff = Math.atan2(Math.sin(diff), Math.cos(diff)); // normalize
//
//	    double speed = velocity.magnitude();
//
//	    if(Math.abs(diff) <= turnRate) {
//	        // reached the target
//	        velocity.setX(Math.cos(targetAngle) * speed);
//	        velocity.setY(Math.sin(targetAngle) * speed);
//	        isTurning = false; // clear it, so a new one can be chosen later
//	    } else {
//	        double newAngle = currentAngle + Math.signum(diff) * turnRate;
//	        velocity.setX(Math.cos(newAngle) * speed);
//	        velocity.setY(Math.sin(newAngle) * speed);
//	    }
//	}
//	
//	private void turn() {
//	    // occasionally pick a new target angle
//	    if(!isTurning && Math.random() < 0.008) {
//	        double randOffset = Math.toRadians((Math.random() * 60) - 30);
//	        double currentAngle = velocity.getAngle();
//	        targetAngle = currentAngle + randOffset;
//	        isTurning = true;
//	    }
//
//	    if(isTurning) {
//		    graduallyTurn(targetAngle, roamingTurnRate);
//	    }
//	}
//
//	private Vector setRandomVelocity() {
//		
//		double vX = Math.random() * 2 - 1;
//		double vY = Math.random() * 2 - 1;
//		
//		Vector randVelocity = new Vector(vX, vY);
//		randVelocity = randVelocity.normalized(); // if you want speeds to be the same for all boids
//		
//		double speed = minSpeed + Math.random() * (maxSpeed - minSpeed);
//		
//		return randVelocity.scaled(speed);
//	}
//
//	public void draw(Graphics2D g2d) {
//		
////		vision.draw(g2d);
//		
//	    g2d.setColor(Color.black);
//	    
//	    int[] xVerticies = {(int) x, (int) (x + width/2), (int) (x - width/2)};
//	    int[] yVerticies = {(int) (y-height/2), (int) (y + height/2), (int) (y + height/2)};
//	    
//	    int[] xVerticiesRotated = new int[3];
//	    int[] yVerticiesRotated = new int[3];
//	    
//	    
//	    // polygon rotated by velocity vector direction
//	    double angle = velocity.getAngle() + Math.PI/2; // so 0 rads corresponds to the triangle pointing up
//	    
//	    for(int i = 0; i < 3; i++) {
//	    	double dx = xVerticies[i] - x;
//	    	double dy = yVerticies[i] - y;
//	    	
//	    	double dxRot = dx*(Math.cos(angle)) - dy*(Math.sin(angle));
//	    	double dyRot = dx*(Math.sin(angle)) + dy*(Math.cos(angle));
//	    	
//	    	xVerticiesRotated[i] = (int) (dxRot + x);
//	    	yVerticiesRotated[i] = (int) (dyRot + y);
//	    	
//	    }
//	    
//	    g2d.fillPolygon(xVerticiesRotated, yVerticiesRotated, 3);
//	   	    
//	}
//	
//	public double getX() {
//		return x;
//	}
//
//	public void setX(double x) {
//		this.x = x;
//	}
//
//	public double getY() {
//		return y;
//	}
//
//	public void setY(double y) {
//		this.y = y;
//	}
//
//	public double getWidth() {
//		return width;
//	}
//
//	public void setWidth(double width) {
//		this.width = width;
//	}
//
//	public double getHeight() {
//		return height;
//	}
//
//	public void setHeight(double height) {
//		this.height = height;
//	}
//
//	public Vector getVelocity() {
//		return velocity;
//	}
//
//	public void setVelocity(Vector velocity) {
//		this.velocity = velocity;
//	}
//	
//	public Vision getVision() {
//		return vision;
//	}
//
//	
//	
//}
