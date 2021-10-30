package team.scaucs1.graphic.window;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import team.scaucs1.data.attributions.GameAttributions;
import team.scaucs1.graphic.panel.ExplainPanel;
import team.scaucs1.graphic.panel.GamePanel;
import team.scaucs1.graphic.panel.StartPanel;

public class GameWindow extends JFrame {
	
	public JPanel gameMain;
	public JPanel downPanel;
	public JPanel rightPanel;
	public JPanel startPanel;
	
	
	public void initWindow() {
		this.setSize(GameAttributions.width, GameAttributions.height);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("¶íÂÞË¹·½¿é");
	}
	
	
	public void addGameMain() {
		gameMain = new GamePanel().getGameMain();
		this.add(gameMain,BorderLayout.CENTER);
		
	}

	public void addExplain() {
		downPanel = new ExplainPanel().getdownPanel();
		this.add(downPanel,BorderLayout.SOUTH);
		
		rightPanel = new ExplainPanel().getRightPanel();
		this.add(rightPanel,BorderLayout.EAST);
		
	}
	
	public void addStartPanel() {
		startPanel = new StartPanel().getStartPanel();
		this.add(startPanel,BorderLayout.CENTER);
		
	}

	
	public GameWindow() {
		
		this.setLayout(new BorderLayout());
		this.addStartPanel();
		this.initWindow();
		
	}
	
}
