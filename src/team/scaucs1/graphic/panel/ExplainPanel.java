package team.scaucs1.graphic.panel;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExplainPanel {
	
	//����˵�����
	JPanel leftPanel = new JPanel();
	JPanel rightPanel = new JPanel();
	
	//��Ϸ״̬��ǩ
	public static JLabel statusLabel = new JLabel("��Ϸ״̬������");
	public static JLabel scoreLabel = new JLabel("��Ϸ�÷֣�0");
	
	public void initExplainPanel() {
		

		leftPanel.setLayout(new GridLayout(4,1));
		rightPanel.setLayout(new GridLayout(2,1));
		
		leftPanel.add(new JLabel("�ո� - ������ת"));
		leftPanel.add(new JLabel("�� - ��������"));
		leftPanel.add(new JLabel("�� - ��������"));
		leftPanel.add(new JLabel("�� - ��������"));
		
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
