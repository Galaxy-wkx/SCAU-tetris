package team.scaucs1.logic;

import java.awt.Color;
import java.util.Random;

import team.scaucs1.data.attributions.GameAttributions;
import team.scaucs1.data.structure.GameDataStructure;
import team.scaucs1.graphic.panel.ExplainPanel;
import team.scaucs1.graphic.panel.GamePanel;

public class GameLogic {

	public static boolean isrunning = true;
	public static int currentRect;// ��ǰͼ�ζ�Ӧ����λʮ��������
	public static int depth, flat;// ��������,���λ����ˮƽλ��
	public static int sleepTime = GameAttributions.defaultSleepTime;
	public static int score = 0;


	public static void gameBegin() {
		while (true) {
			if (!isrunning) {
				break;
			}

			gameRun();

		}
		ExplainPanel.statusLabel.setText("��Ϸ״̬������");

	}

	public static void getNewRect() {
		Random random = new Random();
		// �������Χ���ڶ�������ɳ�����ĳ���֮��
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
					setOccupied(depth, flat);// ��������ռ��
					checkRow(depth);// ����Ƿ��������
					if (isEnded()) {// �����Ϸ�Ƿ����
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
			for (int j = flat; j < flat+ 4; j++) { // ˫�ر���4X4
				if ((testRect & currentRect) != 0) {// �ҵ���ǰ����ͼ�εķ���λ��
					// ������һ�в��ǿյģ����ܼ���������
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
			for (int j = flat; j < flat+ 4; j++) { // ˫�ر���4X4
				if ((testRect & currentRect) != 0) {// �ҵ���ǰ����ͼ�εķ���λ��
					// ����Ϊ��ռ��
					GameDataStructure.matrix[i][j] = 1;
				}
				testRect >>= 1;
			}
		}
	}
	
	public static void removeRow(int row) {
		for(int i = row;i >= 1;i--) {
			for(int j = 1;j <= GameAttributions.gameColumns - 2;j++) {
				GameDataStructure.matrix[i][j] = GameDataStructure.matrix[i-1][j];//���ϲ����ݸ��Ǹ��²㣬�ﵽ����Ч��
			}
		}
		//�ӿ�����
		if(sleepTime - GameAttributions.reducedSleepTime >= GameAttributions.minSleepTime) {
			sleepTime -= GameAttributions.reducedSleepTime;
		}
		refresh(row);
		score += GameAttributions.bonusScore;
		ExplainPanel.scoreLabel.setText("��Ϸ�÷֣�"+score);
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
			clearRect(depth-1 ,flat);//�����һ��
		}
		drawRect(depth,flat);//����һ�����»���
	}
	
	public static void clearRect(int depth, int flat) {
		int testRect = 0x8000;
		for (int i = depth; i < depth + 4; i++) {
			for (int j = flat; j < flat+ 4; j++) { // ˫�ر���4X4
				if ((testRect & currentRect) != 0) {// �ҵ���ǰ����ͼ�εķ���λ��
					// ����Ϊ��ɫ�����������
					GamePanel.text[i][j].setBackground(Color.WHITE);
				}
				testRect >>= 1;
			}
		}
	}
	
	public static void drawRect(int depth, int flat) {
		int testRect = 0x8000;
		for (int i = depth; i < depth + 4; i++) {
			for (int j = flat; j < flat+ 4; j++) { // ˫�ر���4X4
				if ((testRect & currentRect) != 0) {// �ҵ���ǰ����ͼ�εķ���λ��
					// ���ñ�����ɫ�����Ƴ�ͼ��
					GamePanel.text[i][j].setBackground(Color.BLUE);
				}
				testRect >>= 1;
			}
		}
	}
	
	public static boolean canSpin(int nextRect, int depth, int flat) {
		int testRect = 0x8000;
		for (int i = depth; i < depth + 4; i++) {
			for (int j = flat; j < flat+ 4; j++) { // ˫�ر���4X4
				if ((testRect & nextRect) != 0) {// �ҵ�������ת���ͼ�εķ���λ��
					// �����λ���Ѿ�����������ռ�ã�������ת
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
