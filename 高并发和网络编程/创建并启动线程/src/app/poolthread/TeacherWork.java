package app.poolthread;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-02 19:05:19
 * @LastEditTime: 2019-12-02 19:17:29
 * @LastEditors: 麦子
 */

public class TeacherWork extends MyWork {

    private String name;

    public TeacherWork(String name) {
        this.name = name;
    }

    @Override
    public void doWork() {
        System.out.println(this.name + "-> 教书");
    }

}