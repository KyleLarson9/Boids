package main;

import java.awt.Graphics2D;
import java.util.ArrayList;

import objects.Boid;

public class Simulation implements Runnable {

	private SimFrame frame;
	private SimPanel panel;
	
	private Thread thread;
	
	private final int FPS = 120;
	private final int UPS = 200;
	
	private final static int TILES_DEFAULT_SIZE = 32;
	private final static float SCALE = 1.5f;
	private final static int TILES_IN_WIDTH = 26;
	private final static int TILES_IN_HEIGHT = 16;
	private final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	private final static int SIM_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	private final static int SIM_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
	
	private ArrayList<Boid> boids = new ArrayList<>();
	private int totalBoids = 500;
	public Simulation() {
		initializeClasses();
		
		panel = new SimPanel(this);
		frame = new SimFrame(panel);
		panel.setFocusable(true);
		panel.requestFocus();
		
		startSimLoop();
	}
	
	public void update() {
				
		for(Boid boid : boids) {
			boid.update();
		}
		
	}
	
	public void render(Graphics2D g2d) {

		for(Boid boid : boids) {
			boid.draw(g2d);
		}
//		drawSightLines(g2d);
		
	}
	
	public void drawSightLines(Graphics2D g2d) {
		for(int i = 0; i < boids.size(); i++) {
			
			Boid boid1 = boids.get(i);
			
			for(int j = i + 1; j < boids.size(); j++) {
				
				if(i == j) 
					continue;
				
				Boid boid2 = boids.get(j);
				
				if(boid1.getVision().inVisionRange(boid2.getX(), boid2.getY())) {
					g2d.drawLine((int) boid1.getX(), (int) boid1.getY(), (int) boid2.getX(), (int) boid2.getY());
				}
				
			}
			
		}
	}
	
	private void initializeClasses() {
		
		for(int i = 0; i < totalBoids; i++) {
			double randX = Math.random() * SIM_WIDTH;
			double randY = Math.random() * SIM_HEIGHT;
			boids.add(new Boid(this, randX, randY));
		}
	}
	
	private void startSimLoop() {
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		double timePerFrame = 1000000000.0 / FPS;
		double timePerUpdate = 1000000000.0 / UPS;
 
		long previousTime = System.nanoTime();

		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();

		double deltaU = 0;
		double deltaF = 0;

		while(true) {
			long currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			if(deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}

			if(deltaF >= 1) {
				panel.repaint();
				frames++;
				deltaF--;
			}

			if(System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
			    System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;

			}
		}
	}

	public int getSimHeight() {
		return SIM_HEIGHT;
	}
	
	public int getSimWidth() {
		return SIM_WIDTH;
	}
	
	public ArrayList<Boid> getBoids() {
		return boids;
	}
	
}
