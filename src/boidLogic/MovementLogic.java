package boidLogic;

import objects.Boid;
import vectorLogic.Vector;

public class MovementLogic {

	private Boid boid;
	
	public MovementLogic(Boid boid) {
		this.boid = boid;
	}
	
	
	public void clampSpeed() {
		
		Vector velocity = boid.getVelocity();
		double minSpeed = boid.getMinSpeed();
		double maxSpeed = boid.getMaxSpeed();
		
		double speed = velocity.magnitude();
		
		if(speed < minSpeed) {
			velocity = velocity.normalized().scaled(minSpeed);
		} else if(speed > maxSpeed) { 
			velocity = velocity.normalized().scaled(maxSpeed);
		}
	}
	
	public Vector setRandomVelocity() {
		
		double minSpeed = boid.getMinSpeed();
		double maxSpeed = boid.getMaxSpeed();
		
		double vX = Math.random() * 2 - 1;
		double vY = Math.random() * 2 - 1;
		
		Vector randVelocity = new Vector(vX, vY);
		randVelocity = randVelocity.normalized(); // if you want speeds to be the same for all boids
		
		double speed = minSpeed + Math.random() * (maxSpeed - minSpeed);
		
		return randVelocity.scaled(speed);
	}
	
	public void turn() {
		
	    // occasionally pick a new target angle
	    if(!boid.isTurning() && Math.random() < 0.008) {
	        double randOffset = Math.toRadians((Math.random() * 60) - 30);
	        double currentAngle = boid.getVelocity().getAngle();
	        boid.setTargetAngle(currentAngle + randOffset);
	        boid.setTurning(true);
	    }

	    if(boid.isTurning()) {
		    graduallyTurn(boid.getTargetAngle(), boid.getRoamingTurnRate());
	    }
	}
	
	public void graduallyTurn(double targetAngle, double turnRate) {
		
		Vector velocity = boid.getVelocity();
		
	    double currentAngle = velocity.getAngle();

	    double diff = targetAngle - currentAngle;
	    diff = Math.atan2(Math.sin(diff), Math.cos(diff)); // normalize

	    double speed = velocity.magnitude();

	    if(Math.abs(diff) <= turnRate) {
	        // reached the target
	        velocity.setX(Math.cos(targetAngle) * speed);
	        velocity.setY(Math.sin(targetAngle) * speed);
	        boid.setTurning(false); // clear it, so a new one can be chosen later
	    } else {
	        double newAngle = currentAngle + Math.signum(diff) * turnRate;
	        velocity.setX(Math.cos(newAngle) * speed);
	        velocity.setY(Math.sin(newAngle) * speed);
	    }
	}
}
