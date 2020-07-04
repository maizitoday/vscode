package app.time;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-26 16:44:12
 * @LastEditTime: 2020-01-01 15:01:26
 * @LastEditors: 麦子
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TimeTest {

    public static void main(final String[] args) throws InterruptedException, FileNotFoundException {

        try (FileInputStream in = new FileInputStream("word.txt");) {// 读取文件
            in.read();// 读取一个字节
        } catch (final IOException e1) {
            e1.printStackTrace();
        } // try..catch语句结束后自动关闭in
    }
}