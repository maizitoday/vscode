package app.poolthread;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-02 16:33:54
 * @LastEditTime: 2019-12-03 10:19:34
 * @LastEditors: 麦子
 */

import java.util.List;

public interface Service {

    // 关闭线程池
    void shutdown();

    // 查看线程池是否已经被shutdown
    boolean isShutdown();

    // 关掉多余的几条线程
    void shutdownCount(int closeCount);

    // 提交任务到线程池
    void execute(MyWork myWork);

    // 提交任务到线程池
    void execute(List<MyWork> myWorkList);

}