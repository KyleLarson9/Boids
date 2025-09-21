package boidLogic;

import java.util.ArrayList;

import vectorLogic.Vector;

public class FlockingLogic {

	private Boid boid;
	
	public FlockingLogic(Boid boid) {
		this.boid = boid;
	}
	
	public void alignment() {
		
		ArrayList<Boid> boids = boid.getSim().getBoids();
		
		Vector averageVelocity = new Vector(0, 0);
		int boidsInVision = 0;
		double averageSpeed = 0;
		
		for(Boid other : boids) {
			if(other == this.boid) 
				continue;
			
			if(boid.getVision().inVisionRange(other.getX(), other.getY())) {
				boidsInVision++;
				averageVelocity.add(other.getVelocity());
				averageSpeed += other.getVelocity().magnitude();
			}
		}
		
		if(boidsInVision > 0) {
			
			averageVelocity.divide(boidsInVision);
			averageSpeed /= boidsInVision;
			
			// set the boids velocity to turn gradually to new velocity
			Vector desiredVelocity = averageVelocity.normalized();
			desiredVelocity.scale(averageSpeed);
			
			double angle = desiredVelocity.getAngle();
			boid.turnTowardsAngle(angle, boid.getTargetedTurnRate());
			
		}
		
	}
	
	public void cohesion() {
		
		ArrayList<Boid> boids = boid.getSim().getBoids();

		Vector averagePosition = new Vector(0, 0);
		double averageSpeed = 0;
		int boidsInVision = 0;
		
		for(Boid other : boids) {
			if(other == this.boid)
				continue;
			
			if(boid.getVision().inVisionRange(other.getX(), other.getY())) {
				boidsInVision++;
				Vector boidsPosition = new Vector(other.getX(), other.getY());
				averagePosition.add(boidsPosition);
				averageSpeed += other.getVelocity().magnitude();
			}
			
		}
		
		if(boidsInVision > 0) {
			averagePosition.divide(boidsInVision);
			averageSpeed /= boidsInVision;
			
			double dx = averagePosition.getX() - boid.getX();
			double dy = averagePosition.getY() - boid.getY();
			
			Vector desiredDirection = new Vector(dx, dy).normalized();
			
			double angle = desiredDirection.getAngle();
			
			boid.turnTowardsAngle(angle, boid.getTargetedTurnRate()*.9);
			
		}
		
	}
	
}
