package team.scaucs1.data.attributions;

/**
 * 
 * @author Crystal_Xuan 
 * define game attributions 定义游戏属性常量
 * 
 */

public class GameAttributions {

	//窗口属性
	public static int width = 600;
	public static int height = 850;

	//游戏区域行列数
	public static final int gameRows = 39;
	public static final int gameColumns = 18;

	//线程默认初始休眠时间(毫秒)
	public static final int defaultSleepTime = 1000;
	//每次成功得分后，减少休眠的时间(毫秒)
	public static final int reducedSleepTime = 100;
	//最短休眠时间(毫秒)
	public static final int minSleepTime = 200;

	//成功消除行数的奖励得分
	public static final int bonusScore = 100;
	
	//所有随机生成图形的可能情况
	public static final int[] allRect = new int[] { 0x00cc, 0x8888, 0x000f, 0x0c44, 0x002e, 0x088c,
	0x00e8, 0x0c88, 0x00e2, 0x044c, 0x008e, 0x08c4, 0x006c, 0x04c8, 0x00c6, 0x08c8, 0x004e, 0x04c4, 0x00e4 };
	
	
}
