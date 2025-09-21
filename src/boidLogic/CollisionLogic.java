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
		int wallSize = boid.getSim().getWallSize();
		
	    if(x < 0 + wallSize*2) {
	        boid.setX(width - (wallSize*2));
	    } else if(x > width - (wallSize*2)) {
	        boid.setX(0 + wallSize*2);
	    }

	    if(y < 0 + wallSize*2) {
	        boid.setY(height - (wallSize*2));
	    } else if(y > height - (wallSize*2)) {
	        boid.setY(0 + wallSize*2);
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
