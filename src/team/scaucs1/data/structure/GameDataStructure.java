package team.scaucs1.data.structure;

import team.scaucs1.data.attributions.GameAttributions;

public class GameDataStructure {
	public static int[][] matrix;
	static {//������߿򲿷ֵ����ݳ�ʼ��Ϊ1������ռ��
		matrix = new int[GameAttributions.gameRows][GameAttributions.gameColumns];
		for(int row = 0; row < GameAttributions.gameRows; row++) {
			matrix[row][0] = 1;//��߿�
			matrix[row][GameAttributions.gameColumns - 1] = 1;//�ұ߿�
			if(row == GameAttributions.gameRows - 1) {
				for(int col = 0; col < GameAttributions.gameColumns; col++) {
					matrix[row][col] = 1;//�ױ߿�
				}
			}
		}
	}
}
