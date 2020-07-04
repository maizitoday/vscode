package app.poolthread;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-02 19:04:59
 * @LastEditTime: 2019-12-02 19:22:14
 * @LastEditors: 麦子
 */

public class PlayWork extends MyWork {

    private  String name;

    public PlayWork(String name) {
        this.name = name;
    }

    @Override
    public void doWork() {
        System.out.println(this.name+"-> 夺取冠军");
    }
    
    
}