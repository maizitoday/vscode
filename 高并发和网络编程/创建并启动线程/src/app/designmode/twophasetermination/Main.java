package app.designmode.twophasetermination;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-06 22:22:19
 * @LastEditTime: 2019-12-06 22:27:31
 * @LastEditors: 麦子
 */

import java.util.Arrays;
import java.util.Iterator;

public class Main {
    static IGameSys[] igame = { new MsgSys(500, "公告系统"), new ActivitySys(200, "活动系统") };

    public static void main(String[] args) throws InterruptedException {
        System.out.println("游戏服务器已启动");
        startThreadGroup();
        Thread.sleep(4000);
        SystemState.getInstance().sendState();

    }

    private static void startThreadGroup() throws InterruptedException {
        Iterator<IGameSys> it = Arrays.asList(igame).iterator();
        while (it.hasNext()) {
            Thread thread = new Thread(new RepairModelImpl(it.next()));
            thread.start();
        }
    }
}