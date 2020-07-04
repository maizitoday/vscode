package app.designmode.twophasetermination;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-06 22:19:27
 * @LastEditTime: 2019-12-06 22:25:10
 * @LastEditors: 麦子
 */

public class ActivitySys extends IGameSys {

    public ActivitySys(long time, String name) {
        super(time, name);
    }

    private boolean temp = false;

    @Override
    public void event(Object... ob) throws ThreadOverException {
        if (!temp) {
            System.out.println("<Thread-" + super.name() + ">[event]活动系统运行中 ><");
            temp = true;
        }
    }

    @Override
    public void overEvent() {
        System.out.println("<Thread" + super.name() + ">[overEvent]");
    }

}