package boidLogic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;

import objects.Boid;

public class Vision {

	private double visionRadius;
	private double visionAngle; 
	private Boid boid;
	
	public Vision(Boid boid, double visionRadius, double visionAngle) {
		this.boid = boid;
		this.visionRadius = visionRadius;
		this.visionAngle = visionAngle;
	}
	
	public void draw(Graphics2D g2d) {
		
		double centerX = boid.getX() ;
		double centerY = boid.getY() ;
		
		double directionAngle = Math.toDegrees(boid.getVelocity().getAngle());
		
		double startAngle = directionAngle - visionAngle/2.0;
		
		Arc2D.Double arc = new Arc2D.Double(
				centerX - visionRadius,
				centerY - visionRadius,
				visionRadius * 2,
				visionRadius * 2,
				-startAngle,
				-visionAngle,
				Arc2D.PIE
				);
		
		g2d.setColor(new Color(0, 255, 0, 80));
		g2d.fill(arc);
		g2d.setColor(Color.green);
		g2d.draw(arc);
		
	}
	
	public boolean inVisionRange(double objectX, double objectY) {
		
		double dx = objectX - boid.getX();
		double dy = objectY - boid.getY();
		
		double distance = Math.sqrt(dx*dx + dy*dy);
		
		if(distance > visionRadius) 
			return false;
		
		double objectAngle = Math.atan2(dy, dx);
		double directionAngle = boid.getVelocity().getAngle();
		
		double angleDifference = Math.toDegrees(directionAngle - objectAngle);
		
		angleDifference = ((angleDifference + 180) % 360) - 180;
		
		return Math.abs(angleDifference) <= visionAngle/2.0;
		
	}
	
}
