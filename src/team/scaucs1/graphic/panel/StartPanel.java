package team.scaucs1.graphic.panel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import team.scaucs1.Tetris;
import team.scaucs1.logic.GameLogic;


public class StartPanel {

	Image startImg = new ImageIcon(System.getProperty("user.dir") + "\\picture\\start.jpg").getImage();
	JPanel startPanel = new JPanel() {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(startImg, 0, 0,this.getWidth(), this.getHeight(), this);
		}
	};
	
	public StartPanel() {
		initStartPanel();
	}
	
	public void initStartPanel() {
		startPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,300));
		JButton newGame = new JButton("新游戏");
		newGame.setPreferredSize(new Dimension(350,50));
		JButton exit = new JButton("退出");
		exit.setPreferredSize(new Dimension(350,50));
		newGame.setFont(new Font("微软雅黑", 0, 20));
		exit.setFont(new Font("微软雅黑", 0, 20));
		
		newGame.setFocusable(false);
		exit.setFocusable(false);
		startPanel.setFocusable(false);

		newGame.setLayout(new FlowLayout(FlowLayout.CENTER,0,300));
		exit.setLayout(new FlowLayout(FlowLayout.CENTER,0,500));
		
		//此处跳转至游戏界面
		newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		
            	Tetris.gw.remove(startPanel);
            	Tetris.gw.addExplain();
            	Tetris.gw.addGameMain();
            	
            	SwingUtilities.updateComponentTreeUI(Tetris.gw);
            	GamePanel.text[0][0].setFocusable(true);
            	GamePanel.text[0][0].requestFocus();
            	
            	new Thread(() -> GameLogic.gameBegin()).start();
            	//Lambda表达式，创建一个游戏线程
            	
            }
            
		});
		
		exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Tetris.gw.dispose();
            }
		});
		
		startPanel.add(newGame);
		startPanel.add(exit);
	}
	
	

	public JPanel getStartPanel() {
		return startPanel;
	}
}
