package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import objects.Boid;
import objects.Wall;

public class Simulation implements Runnable {

	private SimFrame frame;
	private SimPanel panel;
	
	private Thread thread;
	
	private final int FPS = 120;
	private final int UPS = 200;
	
	private final static int TILES_DEFAULT_SIZE = 16;
	private final static float SCALE = 1.5f;
	private final static int TILES_IN_WIDTH = 60;
	private final static int TILES_IN_HEIGHT = 36;
	private final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	private final static int SIM_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	private final static int SIM_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
	
	private ArrayList<Wall> walls = new ArrayList<>();
	private final int WALL_SIZE = TILES_SIZE;
	
	private ArrayList<Boid> boids = new ArrayList<>();
	private Boid testBoid; // boid for testing visualizations including vision cone, rays, ect
	
	private int totalBoids = 3;
	
	public Simulation() {
		initializeClasses();
			
		testBoid = new Boid(this, SIM_WIDTH/2, SIM_HEIGHT/2, false, false);
		
		panel = new SimPanel(this);
		frame = new SimFrame(panel);
		panel.setFocusable(true);
		panel.requestFocus();
		
		startSimLoop();
	}
	
	public void update() {
			
		testBoid.update();

		for(Boid boid : boids) {
			boid.update();
		}
				
	}
	
	public void render(Graphics2D g2d) {

		g2d.setColor(Color.red);
		testBoid.draw(g2d);
		
		for(Boid boid : boids) {
			g2d.setColor(Color.black);
			boid.draw(g2d);
		}
		
		for(Wall wall : walls) {
			wall.draw(g2d);
		}
	}

	private void initializeClasses() {
		
		for(int i = 0; i < totalBoids; i++) {
			double randX = Math.random() * SIM_WIDTH;
			double randY = Math.random() * SIM_HEIGHT;
			boids.add(new Boid(this, randX, randY, false, false));
		}
		
		initializeWalls();
	}
	
	private void initializeWalls() {
		
		// Top border
		for(int i = 0 + WALL_SIZE; i < SIM_WIDTH - WALL_SIZE; i += WALL_SIZE) {
			walls.add(new Wall(i, 0 + WALL_SIZE, WALL_SIZE, WALL_SIZE));
		}
		
		// Bottom border
		for(int i = 0 + WALL_SIZE; i < SIM_WIDTH - WALL_SIZE; i += WALL_SIZE) {
			walls.add(new Wall(i, SIM_HEIGHT - WALL_SIZE - WALL_SIZE, WALL_SIZE, WALL_SIZE));
		}
		
		// Left border
		for(int i = 0 + (WALL_SIZE*2); i < SIM_HEIGHT - (WALL_SIZE*2); i += WALL_SIZE) {
			walls.add(new Wall(0 + WALL_SIZE, i, WALL_SIZE, WALL_SIZE));
		}
		
		// Right border
		for(int i = + (WALL_SIZE*2); i < SIM_HEIGHT - (WALL_SIZE*2); i += WALL_SIZE) {
			walls.add(new Wall(SIM_WIDTH - WALL_SIZE - WALL_SIZE, i, WALL_SIZE, WALL_SIZE));
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
//			    System.out.println("FPS: " + frames + " | UPS: " + updates);
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
	
	public int getWallSize() {
		return WALL_SIZE;
	}
	
}
