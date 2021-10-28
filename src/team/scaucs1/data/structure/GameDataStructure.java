package team.scaucs1.data.structure;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javafx.util.Pair;
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
	
	//�����������ͼ�εĿ������
	public static final int[] allRect = new int[] { 0x00cc, 0x8888, 0x000f, 0x0c44, 0x002e, 0x088c,
	0x00e8, 0x0c88, 0x00e2, 0x044c, 0x008e, 0x08c4, 0x006c, 0x04c8, 0x00c6, 0x08c8, 0x004e, 0x04c4, 0x00e4 };
	
	//�������һ��ͼ�Σ�����ͼ�ζ�Ӧ����λʮ��������
	public static int getNewRect() {
		Random random = new Random();
		// �������Χ���ڶ�������ɳ�����ĳ���֮��
		int randomIndex = random.nextInt(allRect.length);
		return allRect[randomIndex];
	}
	
	public static List<Pair<Integer, Integer>> getMatchedRectPosList(int rect) {
		int singleRect = 0x8000;
		List<Pair<Integer, Integer>> posList = new LinkedList<Pair<Integer, Integer>>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) { // ˫�ر���4X4
				if ((singleRect & rect) != 0) {// �ҵ���ǰͼ�εķ�������
					posList.add(new Pair<>(i,j)); //������Ϣ�����list
				}
				singleRect >>= 1; //����һλ������ƥ��
			}
		}
		return posList;
	}
	
}
