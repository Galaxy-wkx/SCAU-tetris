package team.scaucs1.logic;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import javafx.util.Pair;
import team.scaucs1.Tetris;
import team.scaucs1.data.attributions.GameAttributions;
import team.scaucs1.data.structure.GameDataStructure;
import team.scaucs1.graphic.panel.ExplainPanel;
import team.scaucs1.graphic.panel.GamePanel;

public class GameLogic {

	public static boolean isRunning = true;
	public static boolean isPaused = false;
	public static int currentRect;// ��ǰͼ�ζ�Ӧ����λʮ��������
	public static Color currentRectColor;//��ǰͼ�ε���ɫ
	public static int depth, flat;// ��������,���λ����ˮƽλ��
	public static int sleepTime = GameAttributions.defaultSleepTime;
	public static int score = 0;

	public static List<Pair<Integer, Integer>> posList;

	public static void gameBegin() {
		while (true) {
			if (!isRunning) {
				break;
			}
			gameRun();


		}
		gameEnd();
	}
	
	//��ʾ��ʾ��Ϣ��������Ϸ����ת�ؿ�ʼ����
	public static void gameEnd() {
		ExplainPanel.statusLabel.setText("--��Ϸ״̬������--");
		JOptionPane.showMessageDialog(null, "��Ϸ������\n���ĵ÷�Ϊ��"+score, "��Ϸ����",JOptionPane.INFORMATION_MESSAGE);
		gameReset();
		Tetris.gw.remove(Tetris.gw.gameMain);
		Tetris.gw.remove(Tetris.gw.downPanel);
		Tetris.gw.remove(Tetris.gw.rightPanel);
		Tetris.gw.addStartPanel();
		SwingUtilities.updateComponentTreeUI(Tetris.gw);
		
	}

//	public static void getNewRect() {
//		Random random = new Random();
//		// �������Χ���ڶ�������ɳ�����ĳ���֮��
//		int randomIndex = random.nextInt(GameDataStructure.allRect.length);
//		currentRect = GameDataStructure.allRect[randomIndex];
//		posList = GameDataStructure.getMatchedRectPosList(currentRect);
//	}

	public static void gameRun() {
		currentRect = GameDataStructure.getNewRect();
		posList = GameDataStructure.getMatchedRectPosList(currentRect);
		depth = 0;
		flat = GameDataStructure.getRandomFlat();
		currentRectColor = GameDataStructure.getRandomColor();
		drawRect(depth,flat);
		
		for (int i = 0; i < GameAttributions.gameRows; i++) {
			try {
				Thread.sleep(sleepTime);
				if(!isRunning) {
					break;
				}
				if(isPaused) {
					i--;
					continue;
				}
				if (canFall(depth, flat)) {
					depth++;
					fall(depth, flat);
				} else {
					setOccupied(depth, flat);// ��������ռ��
					checkRow(depth);// ����Ƿ��������
					if (isEnded()) {// �����Ϸ�Ƿ����
						isRunning = false;
					}
					break;
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
	
	public static void gameReset() {
		isRunning = true;
		isPaused = false;
		sleepTime = GameAttributions.defaultSleepTime;
		score = 0;
		ExplainPanel.statusLabel.setText("--��Ϸ״̬������--");
		ExplainPanel.scoreLabel.setText("--��Ϸ�÷֣�"+score+"--");
		GameDataStructure.initMatrix();
		
	}

	public static void checkRow(int row) {
		for (int i = row, j = 1; i < row + 4; i++) {
			for (j = 1; j <= GameAttributions.gameColumns - 2; j++) {
				if (GameDataStructure.matrix[i][j] != 1)
					break;
			}
			if (j > GameAttributions.gameColumns - 2)
				removeRow(i);
		}
	}

	public static boolean isEnded() {
		for (int col = 1; col <= GameAttributions.gameColumns - 2; col++) {
			if (GameDataStructure.matrix[3][col] == 1) {
				// ended
				return true;
			}
		}
		return false;
	}

	public static boolean canFall(int depth, int flat) {
		
		for(Pair<Integer, Integer> pos :posList) {
			if(GameDataStructure.matrix[depth+pos.getKey()+1][flat+pos.getValue()] == 1)
				return false;
		}
		return true;
	}

	public static void setOccupied(int depth, int flat) {

		for(Pair<Integer, Integer> pos :posList) {
			GameDataStructure.matrix[depth+pos.getKey()][flat+pos.getValue()] = 1;
		}
	}
	
	public static void removeRow(int row) {
		for(int i = row;i >= 1;i--) {
			for(int j = 1;j <= GameAttributions.gameColumns - 2;j++) {
				GameDataStructure.matrix[i][j] = GameDataStructure.matrix[i-1][j];//���ϲ����ݸ��Ǹ��²㣬�ﵽ����Ч��
				GamePanel.text[i][j].setBackground(GamePanel.text[i-1][j].getBackground());//�ϲ���ɫ���Ǹ��²�
			}
		}
		//�ӿ�����
		if(sleepTime - GameAttributions.reducedSleepTime >= GameAttributions.minSleepTime) {
			sleepTime -= GameAttributions.reducedSleepTime;
		}
		score += GameAttributions.bonusScore;
		ExplainPanel.scoreLabel.setText("--��Ϸ�÷֣�"+score+"--");
	}
	
//	public static void refresh(int row) {
//		for(int i = row; i >= 1; i--) {
//			for(int j = 1; j <= GameAttributions.gameColumns - 2;j++) {
//				if(GameDataStructure.matrix[i][j] == 1) {
//					GamePanel.text[i][j].setBackground(Color.BLUE);
//				}
//				else {
//					GamePanel.text[i][j].setBackground(Color.WHITE);
//				}
//				
//			}
//		}
//		
//	}
	
	public static void fall(int depth, int flat) {
		if(depth > 0) {
			clearRect(depth-1 ,flat);//�����һ��
		}
		drawRect(depth,flat);//����һ�����»���
	}
	
	public static void clearRect(int depth, int flat) {
		
		for(Pair<Integer, Integer> pos :posList) {
			GamePanel.text[depth+pos.getKey()][flat+pos.getValue()].setBackground(Color.WHITE);
		}
	}
	
	public static void drawRect(int depth, int flat) {
		

		for(Pair<Integer, Integer> pos :posList) {
			GamePanel.text[depth+pos.getKey()][flat+pos.getValue()].setBackground(currentRectColor);
		}
		
	}
	
	public static boolean canSpin(int nextRect, int depth, int flat) {
		List<Pair<Integer, Integer>> nextPosList = GameDataStructure.getMatchedRectPosList(nextRect);
		for(Pair<Integer, Integer> pos :nextPosList) {
			if(GameDataStructure.matrix[depth+pos.getKey()][flat+pos.getValue()] == 1)
				return false;
		}
		return true;
	}
	
	
}
