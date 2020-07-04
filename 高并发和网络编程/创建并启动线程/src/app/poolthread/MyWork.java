package app.poolthread;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-02 18:53:16
 * @LastEditTime: 2019-12-02 19:29:24
 * @LastEditors: 麦子
 */

public abstract class MyWork {

    private void beforeWork() {
        System.out.println("----------------------->"+Thread.currentThread().getName() + " 开始工作");
    }

    public void work(){
        beforeWork();
        doWork();
        afterWork();
    }

    protected abstract void doWork();
       

    private void afterWork() {
        System.out.println("----------------------->"+Thread.currentThread().getName() + " 结束工作");
    }
       
}