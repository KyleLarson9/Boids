package main;

import javax.swing.JFrame;

public class SimFrame extends JFrame {

	public SimFrame(SimPanel panel) {
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
}
