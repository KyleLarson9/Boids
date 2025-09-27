package boidLogic;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ray {

	private double x1, y1;
	private double x2, y2;
	
	public Ray(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.blue);
		g2d.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
	}
}
