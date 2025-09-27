package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class Obstacle {

    private double x, y;
    private double width, height;

    private Line2D.Double[] lines;

    private int borderWidth = 2;
    private int borderHeight = 2;

    public Obstacle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.lines = new Line2D.Double[4]; // 4 sides

        createLines(); 
    }

    private void createLines() {
        double x1 = x;
        double y1 = y;
        double x2 = x + width;
        double y2 = y + height;

        // top
        lines[0] = new Line2D.Double(x1, y1, x2, y1);
        // right
        lines[1] = new Line2D.Double(x2, y1, x2, y2);
        // bottom
        lines[2] = new Line2D.Double(x2, y2, x1, y2);
        // left
        lines[3] = new Line2D.Double(x1, y2, x1, y1);
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.fillRect((int) x, (int) y, (int) width, (int) height);

        g2d.setColor(Color.white);
        g2d.fillRect((int) (x + borderWidth), (int) (y + borderHeight),
                     (int) (width - (borderWidth * 2)),
                     (int) (height - (borderHeight * 2)));
    }

    public Line2D.Double[] getLineComponents() {
        return lines;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }
}
