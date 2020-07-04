package app.poolthread;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-02 16:38:21
 * @LastEditTime: 2019-12-03 11:21:31
 * @LastEditors: 麦子
 */

import java.util.ArrayList;
import java.util.List;

public class ThreadPoolTest {

    public static void main(String[] args) {

        List<MyWork> workers = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            MyWork studentWork = new StudentWork(i + "号小明同学");
            workers.add(studentWork);
        }

        for (int i = 1; i <= 5; i++) {
            MyWork teacherWork = new TeacherWork(i + "号高老师");
            workers.add(teacherWork);
        }

        for (int i = 1; i <= 5; i++) {
            MyWork playWork = new PlayWork(i + "号太空易");
            // workers.add(playWork);
        }
        // 1. 启动5条线程， 从队列里面去获取任务， 如果没有任务的话，就进行wait等待。
        final ThreadPool threadPool = new ThreadPool(5, workers.size());
        // 2. 添加任务
        threadPool.execute(workers);
    }

}