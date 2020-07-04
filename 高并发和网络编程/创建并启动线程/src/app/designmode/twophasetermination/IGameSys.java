package app.designmode.twophasetermination;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-06 22:18:28
 * @LastEditTime: 2019-12-06 22:25:17
 * @LastEditors: 麦子
 */

public abstract class IGameSys {
    private long time;
    private String name;

    public IGameSys(long time, String name) {
        this.time = time;
        this.name = name;
    }

    public String name() {
        return this.name;
    }

    public long time() {
        return this.time;
    }

    public abstract void event(Object... ob) throws ThreadOverException;

    public abstract void overEvent();

}