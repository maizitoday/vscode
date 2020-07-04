package app.designmode.twophasetermination;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-06 22:19:56
 * @LastEditTime: 2019-12-06 22:25:36
 * @LastEditors: 麦子
 */

public class MsgSys extends IGameSys {
 
	public MsgSys(long time, String name) {
		super(time, name);
	}
	private boolean temp=false;
	@Override
	public void event(Object... ob) throws ThreadOverException {
		if (!temp){
		System.out.println("<Thread-" + super.name() + ">[event]系统公告运行中 ><");
		temp=true;
		}
	}
 
	@Override
	public void overEvent() {
		System.out.println("<Thread" + super.name() + ">[overEvent]");
	}
 
}