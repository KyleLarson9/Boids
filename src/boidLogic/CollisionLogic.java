package boidLogic;

import objects.Boid;
import vectorLogic.Vector;

public class CollisionLogic {

	private Boid boid;
	
	public CollisionLogic(Boid boid) {
		this.boid = boid;
	}
	
	public void checkCollisions() {
		
		double x = boid.getX();
		double y = boid.getY();
		double width = boid.getSim().getSimWidth();
		double height = boid.getSim().getSimHeight();
		
	    if(x < 0) {
	        boid.setX(width);;
	    } else if(x > width) {
	        boid.setX(0);
	    }

	    if(y < 0) {
	        boid.setY(height);
	    } else if(y > height) {
	        boid.setY(0);
	    }
	}
	
	public void avoidWallsUsingVision() {
		   
		Vector velocity = boid.getVelocity();
		double visionAngle = boid.getVisionAngle();
		double visionRadius = boid.getVisionRadius();
		
		double startAngle = velocity.getAngle() - Math.toRadians(visionAngle)/2;
		double endAngle = velocity.getAngle() + Math.toRadians(visionAngle)/2;
		int totalPoints = 20;
		double deltaAngle =  Math.toRadians(visionAngle/totalPoints);
		
		for(double angle = startAngle; angle <= endAngle; angle+=deltaAngle) {
			// calculate ray endpoint
			double rayX = boid.getX() + visionRadius * Math.cos(angle);
			double rayY = boid.getY() + visionRadius * Math.sin(angle);
			
		}		
	}
	
}
