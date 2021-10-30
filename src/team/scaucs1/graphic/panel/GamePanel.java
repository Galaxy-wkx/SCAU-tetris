package team.scaucs1.graphic.panel;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import team.scaucs1.control.KeyControl;
import team.scaucs1.data.attributions.GameAttributions;



public class GamePanel{
	
	//游戏主面板
	JPanel gameMain;
	
	public static JTextArea[][] text;
	
	
	
	public void initGamePanel() {
		gameMain.setLayout(new GridLayout(GameAttributions.gameRows, GameAttributions.gameColumns, 2, 2));
		for(int row = 0; row < text.length; row++) {
			for(int col = 0; col < text[row].length; col++) {
				text[row][col] = new JTextArea();
						
				text[row][col].setBackground(Color.WHITE);
				
				text[row][col].addKeyListener(new KeyControl());

				
				if(col == 0 || col == text[row].length - 1 || row == text.length - 1){
					//边界
//					text[row][col].setOpaque(false);
					text[row][col].setBackground(Color.DARK_GRAY);
					
				}
				text[row][col].setEditable(false);
				gameMain.add(text[row][col]);
			}
		}

	}
	
	public GamePanel() {
		text = new JTextArea[GameAttributions.gameRows][GameAttributions.gameColumns];
		this.gameMain = new JPanel();
		this.initGamePanel();
	}

	
	public JPanel getGameMain() {
		return gameMain;
	}




	
}
