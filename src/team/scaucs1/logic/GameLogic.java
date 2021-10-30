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
	public static int currentRect;// 当前图形对应的四位十六进制数
	public static Color currentRectColor;//当前图形的颜色
	public static int depth, flat;// 方块坐标,深度位置与水平位置
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
	
	//显示提示消息，重置游戏，跳转回开始界面
	public static void gameEnd() {
		ExplainPanel.statusLabel.setText("--游戏状态：结束--");
		JOptionPane.showMessageDialog(null, "游戏结束！\n您的得分为："+score, "游戏结束",JOptionPane.INFORMATION_MESSAGE);
		gameReset();
		Tetris.gw.remove(Tetris.gw.gameMain);
		Tetris.gw.remove(Tetris.gw.downPanel);
		Tetris.gw.remove(Tetris.gw.rightPanel);
		Tetris.gw.addStartPanel();
		SwingUtilities.updateComponentTreeUI(Tetris.gw);
		
	}

//	public static void getNewRect() {
//		Random random = new Random();
//		// 随机数范围是在定义的生成池数组的长度之内
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
					setOccupied(depth, flat);// 方块锁定占用
					checkRow(depth);// 检查是否可以消除
					if (isEnded()) {// 检查游戏是否结束
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
		ExplainPanel.statusLabel.setText("--游戏状态：运行--");
		ExplainPanel.scoreLabel.setText("--游戏得分："+score+"--");
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
				GameDataStructure.matrix[i][j] = GameDataStructure.matrix[i-1][j];//将上层数据覆盖给下层，达到消除效果
				GamePanel.text[i][j].setBackground(GamePanel.text[i-1][j].getBackground());//上层颜色覆盖给下层
			}
		}
		//加快下落
		if(sleepTime - GameAttributions.reducedSleepTime >= GameAttributions.minSleepTime) {
			sleepTime -= GameAttributions.reducedSleepTime;
		}
		score += GameAttributions.bonusScore;
		ExplainPanel.scoreLabel.setText("--游戏得分："+score+"--");
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
			clearRect(depth-1 ,flat);//清除上一层
		}
		drawRect(depth,flat);//在下一层重新绘制
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
