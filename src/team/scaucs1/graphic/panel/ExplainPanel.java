package team.scaucs1.graphic.panel;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExplainPanel {
	
	//左右说明面板
	JPanel leftPanel = new JPanel();
	JPanel rightPanel = new JPanel();
	
	//游戏状态标签
	public static JLabel statusLabel = new JLabel("游戏状态：运行");
	public static JLabel scoreLabel = new JLabel("游戏得分：0");
	
	public void initExplainPanel() {
		

		leftPanel.setLayout(new GridLayout(4,1));
		rightPanel.setLayout(new GridLayout(2,1));
		
		leftPanel.add(new JLabel("空格 - 方块旋转"));
		leftPanel.add(new JLabel("← - 方块左移"));
		leftPanel.add(new JLabel("→ - 方块右移"));
		leftPanel.add(new JLabel("↓ - 加速下落"));
		
		statusLabel.setForeground(Color.RED);
		scoreLabel.setForeground(Color.RED);
		rightPanel.add(statusLabel);
		rightPanel.add(scoreLabel);
		
	}
	
	public ExplainPanel() {
		initExplainPanel();
	}

	public JPanel getLeftPanel() {
		return leftPanel;
	}

	public JPanel getRightPanel() {
		return rightPanel;
	}
	
	
	
}
