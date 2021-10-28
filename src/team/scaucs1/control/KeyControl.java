package team.scaucs1.control;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.List;

import javafx.util.Pair;
import team.scaucs1.data.attributions.GameAttributions;
import team.scaucs1.data.structure.GameDataStructure;
import team.scaucs1.graphic.panel.GamePanel;
import team.scaucs1.logic.GameLogic;

public class KeyControl {
	
	public static void moveLeft(KeyEvent e) {
		if(e.getKeyCode() == 37) {
			if(!GameLogic.isrunning)//�ж���Ϸ�Ƿ�������
				return;
			if(GameLogic.flat <= 1)//�жϷ����Ƿ񵽴���߽�
				return;
			
			List<Pair<Integer, Integer>> posList = GameDataStructure.getMatchedRectPosList(GameLogic.currentRect);
			for(Pair<Integer, Integer> pos :posList) {
				if(GameDataStructure.matrix[GameLogic.depth+pos.getKey()][GameLogic.flat+pos.getValue()-1] == 1)
					return;
			}
			
			
			GameLogic.clearRect(GameLogic.depth, GameLogic.flat);//���ͼ��
			GameLogic.flat--;//ˮƽλ�ü�һ
			GameLogic.drawRect(GameLogic.depth, GameLogic.flat);//���»���ͼ��
		}
	}
	
	public static void moveRight(KeyEvent e) {
		if(e.getKeyCode() == 39) {
			if(!GameLogic.isrunning)//�ж���Ϸ�Ƿ�������
				return;
			
			List<Pair<Integer, Integer>> posList = GameDataStructure.getMatchedRectPosList(GameLogic.currentRect);
			for(Pair<Integer, Integer> pos :posList) {
				if(GameDataStructure.matrix[GameLogic.depth+pos.getKey()][GameLogic.flat+pos.getValue()+1] == 1)
					return;
			}
			
			GameLogic.clearRect(GameLogic.depth, GameLogic.flat);//���ͼ��
			GameLogic.flat++;//ˮƽλ�ü�һ
			GameLogic.drawRect(GameLogic.depth, GameLogic.flat);//���»���ͼ��
		}
	}
	
	public static void moveDown(KeyEvent e) {
		if(e.getKeyCode() == 40) {
			if(!GameLogic.isrunning)//�ж���Ϸ�Ƿ�������
				return;
			
			if(!GameLogic.canFall(GameLogic.depth, GameLogic.flat))
				return;

			
			GameLogic.clearRect(GameLogic.depth, GameLogic.flat);//���ͼ��
			GameLogic.depth++;//��ȼ�һ
			GameLogic.drawRect(GameLogic.depth, GameLogic.flat);//���»���ͼ��
		}
	}
	
	public static void spinRect(KeyEvent e) {
		
		if (e.getKeyChar() == KeyEvent.VK_SPACE) {//�ı䷽����״
			if (!GameLogic.isrunning) {
				return;
			}
			int index = 0;
			for (index = 0; index < GameDataStructure.allRect.length; index++) {//ѭ������19��������״
				if (GameDataStructure.allRect[index] == GameLogic.currentRect)//�ҵ�����ķ����Ӧ����״��Ȼ�������״�ı�
					break;
			}
			if (index == 0)//Ϊ�����η���������״�ı䣬Ϊ����ͼ������1
				return;
			
			int nextRect = GameLogic.currentRect;
			if (index == 1 || index == 2) {//Ϊ����ͼ������2
				nextRect = GameDataStructure.allRect[index == 1 ? 2 : 1];
			}
			if (index >= 3 && index <= 6) {//Ϊ����ͼ������3
				nextRect = GameDataStructure.allRect[index + 1 > 6 ? 3 : index + 1];
			}
			if (index >= 7 && index <= 10) {//Ϊ����ͼ������4
				nextRect = GameDataStructure.allRect[index + 1 > 10 ? 7 : index + 1];
			}
			if (index == 11 || index == 12) {//Ϊ����ͼ������5
				nextRect = GameDataStructure.allRect[index == 11 ? 12 : 11];
			}
			if (index == 13 || index == 14) {//Ϊ����ͼ������6
				nextRect = GameDataStructure.allRect[index == 13 ? 14 : 13];
			}
			if (index >= 15 && index <= 18) {//Ϊ����ͼ������7
				nextRect = GameDataStructure.allRect[index + 1 > 18 ? 15 : index + 1];
			}
			
			if(GameLogic.canSpin(nextRect,GameLogic.depth, GameLogic.flat)) { //ֻ���ܹ���ת����ת
				GameLogic.clearRect(GameLogic.depth, GameLogic.flat);
				GameLogic.currentRect = nextRect;
				GameLogic.drawRect(GameLogic.depth, GameLogic.flat);
			}
		}
	}

		
	
}
