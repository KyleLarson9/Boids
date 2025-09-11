package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class SimPanel extends JPanel {

	private Simulation sim;
	private Dimension size;
	
	public SimPanel(Simulation sim) {
		this.sim = sim;
		
		setPanelSize();
		
		this.setBackground(Color.DARK_GRAY);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		sim.render(g2d);
	}
	
	private void setPanelSize() {
		size = new Dimension(sim.getSimWidth(), sim.getSimHeight());
		setMinimumSize(size);
		setMaximumSize(size);
		setPreferredSize(size);
	}
	
}
