package team.scaucs1.data.structure;

import team.scaucs1.data.attributions.GameAttributions;

public class GameDataStructure {
	public static int[][] matrix;
	static {//将矩阵边框部分的数据初始化为1，代表被占用
		matrix = new int[GameAttributions.gameRows][GameAttributions.gameColumns];
		for(int row = 0; row < GameAttributions.gameRows; row++) {
			matrix[row][0] = 1;//左边框
			matrix[row][GameAttributions.gameColumns - 1] = 1;//右边框
			if(row == GameAttributions.gameRows - 1) {
				for(int col = 0; col < GameAttributions.gameColumns; col++) {
					matrix[row][col] = 1;//底边框
				}
			}
		}
	}
}
