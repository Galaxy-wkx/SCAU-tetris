package team.scaucs1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GamePanel implements KeyListener {
	
	JTextArea[][] text;
	int[][] data;
	
	public JPanel initGamePanel() {
		JPanel gameMain = new JPanel();
		gameMain.setLayout(new GridLayout(GameAttributions.gameRows, GameAttributions.gameColumns, 1, 1));
		
		for(int row = 0; row < text.length; row++) {
			for(int col = 0; col < text[row].length; col++) {
				text[row][col] = new JTextArea(GameAttributions.gameRows, GameAttributions.gameColumns);
				text[row][col].setBackground(Color.WHITE);
				text[row][col].addKeyListener(this);
				if(col == 0 || col == text[row].length - 1 || row == text.length - 1){
					//±ß½ç
					text[row][col].setBackground(Color.MAGENTA);
					data[row][col] = 1;
				}
				text[row][col].setEditable(false);
				gameMain.add(text[row][col]);
			}
		}
	
		return gameMain;
		
	}
	
	public GamePanel() {
		text = new JTextArea[GameAttributions.gameRows][GameAttributions.gameColumns];
		data = new int[GameAttributions.gameRows][GameAttributions.gameColumns];
		
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
