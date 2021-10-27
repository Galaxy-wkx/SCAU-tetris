package team.scaucs1;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {
	
	public void initWindow() {
		this.setSize(600, 850);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("¶íÂÞË¹·½¿é");
	}
	
	
	public void addGameMain() {
		JPanel gameMain = new GamePanel().initGamePanel();
		this.setLayout(new BorderLayout());
		this.add(gameMain,BorderLayout.CENTER);
	}

	public Window() {
		
		this.addGameMain();
		this.initWindow();
		
	}
	
}
