package team.scaucs1;

import javax.swing.UIManager;

import team.scaucs1.graphic.window.GameWindow;
import team.scaucs1.logic.GameLogic;

public class Tetris{

	public static GameWindow gw;
	
	public static void main(String[] args) {
		//������Ϣ��ΪϵͳĬ����ʽ
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
		gw = new GameWindow();//���ɿ�ʼ����
	}
}
