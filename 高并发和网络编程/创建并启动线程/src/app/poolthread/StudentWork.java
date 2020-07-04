package app.poolthread;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-02 19:04:34
 * @LastEditTime: 2019-12-02 19:22:32
 * @LastEditors: 麦子
 */

public class StudentWork extends MyWork  {

    private  String name;

    public StudentWork(String name) {
        this.name = name;
    }

    @Override
    public void doWork() {
        System.out.println(this.name+"-> 读书");
    }
    
}