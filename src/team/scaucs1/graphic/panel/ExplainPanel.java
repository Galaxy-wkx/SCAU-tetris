package team.scaucs1.graphic.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class ExplainPanel {
	
	//左右说明面板
	ImageIcon explainImg = new ImageIcon(System.getProperty("user.dir") + "\\picture\\explain.jpg");
	Image infoImg = new ImageIcon(System.getProperty("user.dir") + "\\picture\\info.jpg").getImage();
	JPanel downPanel = new JPanel();
	JPanel rightPanel = new JPanel() {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(infoImg, 0, 0,this.getWidth(), this.getHeight(), this);
		}
	};
	
	//游戏状态标签
	public static JLabel statusLabel = new JLabel("--游戏状态：运行--");
	public static JLabel scoreLabel = new JLabel("--游戏得分：0--");
	
	public void initExplainPanel() {
		

		downPanel.setLayout(new GridLayout(1,1));
		rightPanel.setLayout(new GridLayout(2,1));
		

//		leftPanel.add(new JLabel("空格 - 方块旋转"));
//		leftPanel.add(new JLabel("← - 方块左移"));
//		leftPanel.add(new JLabel("→ - 方块右移"));
//		leftPanel.add(new JLabel("↓ - 加速下落"));
		JLabel imgLabel = new JLabel(explainImg);
		downPanel.add(imgLabel);
		
		statusLabel.setForeground(Color.BLACK);
		scoreLabel.setForeground(Color.BLACK);
		statusLabel.setFont(new Font("微软雅黑", 1, 25));
		scoreLabel.setFont(new Font("微软雅黑", 1, 25));
		rightPanel.add(statusLabel);
		rightPanel.add(scoreLabel);
		
	}
	
	public ExplainPanel() {
		initExplainPanel();
	}

	public JPanel getdownPanel() {
		return downPanel;
	}

	public JPanel getRightPanel() {
		return rightPanel;
	}
	
	
	
}
