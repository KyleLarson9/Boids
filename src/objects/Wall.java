package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Wall {

	private double x, y;
	private double width, height;
	
	private int borderWidth = 2;
	private int borderHeight = 2;
	
	public Wall(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics2D g2d) {
		
		g2d.setColor(Color.black);
		g2d.fillRect((int) x, (int) y, (int) width, (int) height);
		
		g2d.setColor(Color.white);
		g2d.fillRect((int) (x + borderWidth), (int) (y + borderHeight), (int) (width - (borderWidth*2)) ,(int) (height - (borderHeight*2)));
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}
	
	public double getX() { return x; }
    public double getY() { return y; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }
    
}
