package team.scaucs1.graphic.window;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import team.scaucs1.data.attributions.GameAttributions;
import team.scaucs1.graphic.panel.ExplainPanel;
import team.scaucs1.graphic.panel.GamePanel;

public class Window extends JFrame {
	
	public void initWindow() {
		this.setSize(GameAttributions.width, GameAttributions.height);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("¶íÂÞË¹·½¿é");
	}
	
	
	public void addGameMain() {
		JPanel gameMain = new GamePanel().getGameMain();
		this.add(gameMain,BorderLayout.CENTER);
	}

	public void addExplain() {
		JPanel leftPanel = new ExplainPanel().getLeftPanel();
		this.add(leftPanel,BorderLayout.SOUTH);
		
		JPanel rightPanel = new ExplainPanel().getRightPanel();
		this.add(rightPanel,BorderLayout.EAST);
	}
	
	public Window() {
		this.setLayout(new BorderLayout());
		this.addGameMain();
		this.addExplain();
		this.initWindow();
		
		
		
	}
	
}
