package objects;

import java.awt.Color;
import java.awt.Graphics2D;

import vectorLogic.Vector;

public class Boid {

	private double x, y;
	private Vector velocity;
	private double xVel = 1;
	private double yVel = 2;
	
	public Boid(double x, double y) {
		this.x = x;
		this.y = y;
		this.velocity = new Vector(xVel, yVel);
	}
	
	public void update() {
		x += velocity.getX();
		y += velocity.getY();
	}
	
	public void draw(Graphics2D g2d) {
	    g2d.setColor(Color.black);

	    int width = 10;
	    int height = 20;
	    
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

}
