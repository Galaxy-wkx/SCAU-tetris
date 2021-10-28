package team.scaucs1.control;

import java.awt.event.KeyEvent;

import team.scaucs1.data.attributions.GameAttributions;
import team.scaucs1.data.structure.GameDataStructure;
import team.scaucs1.logic.GameLogic;

public class KeyControl {
	
	public static void moveLeft(KeyEvent e) {
		if(e.getKeyCode() == 37) {
			if(!GameLogic.isrunning)//判断游戏是否还在运行
				return;
			if(GameLogic.flat <= 1)//判断方块是否到达左边界
				return;
			
			int testRect = 0x8000;
			for (int i = GameLogic.depth; i < GameLogic.depth + 4; i++) {
				for (int j = GameLogic.flat; j < GameLogic.flat + 4; j++) { // 双重遍历4X4
					if ((testRect & GameLogic.currentRect) != 0) {// 找到当前下落图形的方块位置
						// 若它的左边有方块，则无法继续移动
						if(GameDataStructure.matrix[i][j-1] == 1)
							return;
					}
					testRect >>= 1;
				}
			}
			GameLogic.clearRect(GameLogic.depth, GameLogic.flat);//清除图形
			GameLogic.flat--;//水平位置减一
			GameLogic.drawRect(GameLogic.depth, GameLogic.flat);//重新绘制图形
		}
	}
	
	public static void moveRight(KeyEvent e) {
		if(e.getKeyCode() == 39) {
			if(!GameLogic.isrunning)//判断游戏是否还在运行
				return;
			
			int testRect = 0x8000;
			for (int i = GameLogic.depth; i < GameLogic.depth + 4; i++) {
				for (int j = GameLogic.flat; j < GameLogic.flat + 4; j++) { // 双重遍历4X4
					if ((testRect & GameLogic.currentRect) != 0) {// 找到当前下落图形的方块位置
						// 若它的右边有方块，则无法继续移动
						if(GameDataStructure.matrix[i][j+1] == 1)
							return;
					}
					testRect >>= 1;
				}
			}
			GameLogic.clearRect(GameLogic.depth, GameLogic.flat);//清除图形
			GameLogic.flat++;//水平位置加一
			GameLogic.drawRect(GameLogic.depth, GameLogic.flat);//重新绘制图形
		}
	}
	
	public static void moveDown(KeyEvent e) {
		if(e.getKeyCode() == 40) {
			if(!GameLogic.isrunning)//判断游戏是否还在运行
				return;
			
			if(!GameLogic.canFall(GameLogic.depth, GameLogic.flat))
				return;

			
			GameLogic.clearRect(GameLogic.depth, GameLogic.flat);//清除图形
			GameLogic.depth++;//深度加一
			GameLogic.drawRect(GameLogic.depth, GameLogic.flat);//重新绘制图形
		}
	}
	
	public static void spinRect(KeyEvent e) {
		
		if (e.getKeyChar() == KeyEvent.VK_SPACE) {//改变方块形状
			if (!GameLogic.isrunning) {
				return;
			}
			int index = 0;
			for (index = 0; index < GameAttributions.allRect.length; index++) {//循环遍历19个方块形状
				if (GameAttributions.allRect[index] == GameLogic.currentRect)//找到下落的方块对应的形状，然后进行形状改变
					break;
			}
			if (index == 0)//为正方形方块无需形状改变，为方块图形种类1
				return;
			
			int nextRect = GameLogic.currentRect;
			if (index == 1 || index == 2) {//为方块图形种类2
				nextRect = GameAttributions.allRect[index == 1 ? 2 : 1];
			}
			if (index >= 3 && index <= 6) {//为方块图形种类3
				nextRect = GameAttributions.allRect[index + 1 > 6 ? 3 : index + 1];
			}
			if (index >= 7 && index <= 10) {//为方块图形种类4
				nextRect = GameAttributions.allRect[index + 1 > 10 ? 7 : index + 1];
			}
			if (index == 11 || index == 12) {//为方块图形种类5
				nextRect = GameAttributions.allRect[index == 11 ? 12 : 11];
			}
			if (index == 13 || index == 14) {//为方块图形种类6
				nextRect = GameAttributions.allRect[index == 13 ? 14 : 13];
			}
			if (index >= 15 && index <= 18) {//为方块图形种类7
				nextRect = GameAttributions.allRect[index + 1 > 18 ? 15 : index + 1];
			}
			
			if(GameLogic.canSpin(nextRect,GameLogic.depth, GameLogic.flat)) { //只有能够旋转才旋转
				GameLogic.clearRect(GameLogic.depth, GameLogic.flat);
				GameLogic.currentRect = nextRect;
				GameLogic.drawRect(GameLogic.depth, GameLogic.flat);
			}
		}
	}

		
	
}
