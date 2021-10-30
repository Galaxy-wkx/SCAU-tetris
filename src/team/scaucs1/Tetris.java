package team.scaucs1;

import javax.swing.UIManager;

import team.scaucs1.graphic.window.GameWindow;
import team.scaucs1.logic.GameLogic;

public class Tetris{

	public static GameWindow gw;
	
	public static void main(String[] args) {
		//设置消息框为系统默认样式
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
		gw = new GameWindow();//生成开始界面
	}
}
