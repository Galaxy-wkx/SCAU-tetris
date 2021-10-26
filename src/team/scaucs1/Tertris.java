package team.scaucs1;

import javax.swing.JFrame;

public class Tertris extends JFrame {

	public void initWindow() {
		this.setSize(600, 850);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("¶íÂÞË¹·½¿é");
	}

	public Tertris() {
		initWindow();
	}
	
	public static void main(String[] args) {
		Tertris tertris = new Tertris();
		
	}
}
