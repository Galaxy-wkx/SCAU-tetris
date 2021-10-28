package team.scaucs1.graphic.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import team.scaucs1.control.KeyControl;
import team.scaucs1.data.attributions.GameAttributions;



public class GamePanel implements KeyListener{
	
	//游戏主面板
	JPanel gameMain = new JPanel();
	
	public static JTextArea[][] text;
	
	
	
	public void initGamePanel() {
		gameMain.setLayout(new GridLayout(GameAttributions.gameRows, GameAttributions.gameColumns, 1, 1));
		for(int row = 0; row < text.length; row++) {
			for(int col = 0; col < text[row].length; col++) {
				text[row][col] = new JTextArea(GameAttributions.gameRows, GameAttributions.gameColumns);
						
				text[row][col].setBackground(Color.WHITE);
				
				text[row][col].addKeyListener(this);
				if(col == 0 || col == text[row].length - 1 || row == text.length - 1){
					//边界
//					text[row][col].setOpaque(false);
					text[row][col].setBackground(Color.magenta);
					
				}
				text[row][col].setEditable(false);
				gameMain.add(text[row][col]);
			}
		}
		
	}
	
	public GamePanel() {
		text = new JTextArea[GameAttributions.gameRows][GameAttributions.gameColumns];
		this.initGamePanel();
	}

	
	public JPanel getGameMain() {
		return gameMain;
	}


	@Override
	public void keyTyped(KeyEvent e) {
		KeyControl.spinRect(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		KeyControl.moveLeft(e);
		KeyControl.moveRight(e);
		KeyControl.moveDown(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	
}
