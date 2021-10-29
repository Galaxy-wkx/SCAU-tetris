package team.scaucs1.graphic.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import team.scaucs1.data.attributions.GameAttributions;


public class ExplainPanel {
	
	//����˵�����
	ImageIcon explainImg = new ImageIcon(System.getProperty("user.dir") + "\\picture\\explain.jpg");
	Image infoImg = new ImageIcon(System.getProperty("user.dir") + "\\picture\\info.jpg").getImage();
	JPanel leftPanel = new JPanel();
	JPanel rightPanel = new JPanel() {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(infoImg, 0, 0,this.getWidth(), this.getHeight(), this);
		}
	};
	
	//��Ϸ״̬��ǩ
	public static JLabel statusLabel = new JLabel("--��Ϸ״̬������--");
	public static JLabel scoreLabel = new JLabel("--��Ϸ�÷֣�0--");
	
	public void initExplainPanel() {
		

		leftPanel.setLayout(new GridLayout(1,1));
		rightPanel.setLayout(new GridLayout(2,1));
		

//		leftPanel.add(new JLabel("�ո� - ������ת"));
//		leftPanel.add(new JLabel("�� - ��������"));
//		leftPanel.add(new JLabel("�� - ��������"));
//		leftPanel.add(new JLabel("�� - ��������"));
		JLabel imgLabel = new JLabel(explainImg);
		leftPanel.add(imgLabel);
		
		statusLabel.setForeground(Color.BLACK);
		scoreLabel.setForeground(Color.BLACK);
		statusLabel.setFont(new Font("΢���ź�", 1, 25));
		scoreLabel.setFont(new Font("΢���ź�", 1, 25));
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
