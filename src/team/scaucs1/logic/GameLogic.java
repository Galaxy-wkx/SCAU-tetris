package team.scaucs1.logic;

import java.awt.Color;
import java.util.Random;

import team.scaucs1.data.attributions.GameAttributions;
import team.scaucs1.data.structure.GameDataStructure;
import team.scaucs1.graphic.panel.ExplainPanel;
import team.scaucs1.graphic.panel.GamePanel;

public class GameLogic {

	public static boolean isrunning = true;
	public static int currentRect;// 当前图形对应的四位十六进制数
	public static int depth, flat;// 方块坐标,深度位置与水平位置
	public static int sleepTime = GameAttributions.defaultSleepTime;
	public static int score = 0;


	public static void gameBegin() {
		while (true) {
			if (!isrunning) {
				break;
			}

			gameRun();

		}
		ExplainPanel.statusLabel.setText("游戏状态：结束");

	}

	public static void getNewRect() {
		Random random = new Random();
		// 随机数范围是在定义的生成池数组的长度之内
		int randomIndex = random.nextInt(GameAttributions.allRect.length);
		currentRect = GameAttributions.allRect[randomIndex];
	}

	public static void gameRun() {
		getNewRect();
		depth = 0;
		flat = 5;

		for (int i = 0; i < GameAttributions.gameRows; i++) {
			try {
				Thread.sleep(sleepTime);
				if (canFall(depth, flat)) {
					depth++;
					fall(depth, flat);
				} else {
					setOccupied(depth, flat);// 方块锁定占用
					checkRow(depth);// 检查是否可以消除
					if (isEnded()) {// 检查游戏是否结束
						isrunning = false;
					}
					break;
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
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
		int testRect = 0x8000;
		for (int i = depth; i < depth + 4; i++) {
			for (int j = flat; j < flat+ 4; j++) { // 双重遍历4X4
				if ((testRect & currentRect) != 0) {// 找到当前下落图形的方块位置
					// 它的下一行不是空的，则不能继续下落了
					if(GameDataStructure.matrix[i+1][j] == 1)
						return false;
				}
				testRect >>= 1;
			}
		}
		return true;
	}

	public static void setOccupied(int depth, int flat) {
		int testRect = 0x8000;
		for (int i = depth; i < depth + 4; i++) {
			for (int j = flat; j < flat+ 4; j++) { // 双重遍历4X4
				if ((testRect & currentRect) != 0) {// 找到当前下落图形的方块位置
					// 设置为被占用
					GameDataStructure.matrix[i][j] = 1;
				}
				testRect >>= 1;
			}
		}
	}
	
	public static void removeRow(int row) {
		for(int i = row;i >= 1;i--) {
			for(int j = 1;j <= GameAttributions.gameColumns - 2;j++) {
				GameDataStructure.matrix[i][j] = GameDataStructure.matrix[i-1][j];//将上层数据覆盖给下层，达到消除效果
			}
		}
		//加快下落
		if(sleepTime - GameAttributions.reducedSleepTime >= GameAttributions.minSleepTime) {
			sleepTime -= GameAttributions.reducedSleepTime;
		}
		refresh(row);
		score += GameAttributions.bonusScore;
		ExplainPanel.scoreLabel.setText("游戏得分："+score);
	}
	
	public static void refresh(int row) {
		for(int i = row; i >= 1; i--) {
			for(int j = 1; j <= GameAttributions.gameColumns - 2;j++) {
				if(GameDataStructure.matrix[i][j] == 1) {
					GamePanel.text[i][j].setBackground(Color.BLUE);
				}
				else {
					GamePanel.text[i][j].setBackground(Color.WHITE);
				}
				
			}
		}
		
	}
	
	public static void fall(int depth, int flat) {
		if(depth > 0) {
			clearRect(depth-1 ,flat);//清除上一层
		}
		drawRect(depth,flat);//在下一层重新绘制
	}
	
	public static void clearRect(int depth, int flat) {
		int testRect = 0x8000;
		for (int i = depth; i < depth + 4; i++) {
			for (int j = flat; j < flat+ 4; j++) { // 双重遍历4X4
				if ((testRect & currentRect) != 0) {// 找到当前下落图形的方块位置
					// 设置为白色背景，即清除
					GamePanel.text[i][j].setBackground(Color.WHITE);
				}
				testRect >>= 1;
			}
		}
	}
	
	public static void drawRect(int depth, int flat) {
		int testRect = 0x8000;
		for (int i = depth; i < depth + 4; i++) {
			for (int j = flat; j < flat+ 4; j++) { // 双重遍历4X4
				if ((testRect & currentRect) != 0) {// 找到当前下落图形的方块位置
					// 设置背景颜色，绘制出图形
					GamePanel.text[i][j].setBackground(Color.BLUE);
				}
				testRect >>= 1;
			}
		}
	}
	
	public static boolean canSpin(int nextRect, int depth, int flat) {
		int testRect = 0x8000;
		for (int i = depth; i < depth + 4; i++) {
			for (int j = flat; j < flat+ 4; j++) { // 双重遍历4X4
				if ((testRect & nextRect) != 0) {// 找到尝试旋转后后图形的方块位置
					// 如果该位置已经被其他方块占用，则不能旋转
					if(GameDataStructure.matrix[i][j] == 1) {
						return false;
					}
				}
				testRect >>= 1;
			}
		}
		return true;
	}
	
	
}
