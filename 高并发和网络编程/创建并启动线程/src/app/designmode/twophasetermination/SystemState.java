package app.designmode.twophasetermination;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-06 22:21:16
 * @LastEditTime: 2019-12-06 22:25:49
 * @LastEditors: 麦子
 */

public final class SystemState {
	/**
	 * 饿汉单例
	 */
	private static SystemState instance = new SystemState();
 
	private SystemState() {
	}
 
	public static SystemState getInstance() {
		return instance;
	}
 
	private volatile boolean state = false;
 
	public boolean isState() {
		return state;
	}
 
	/**
	 * 发送状态 
	 * @author Allen
	 * @date 2017年2月21日
	 */
	public void sendState() {
		this.state = true;
 	}

}