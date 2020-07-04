package app.designmode.threadlocal;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-05 16:01:22
 * @LastEditTime: 2019-12-05 16:01:39
 * @LastEditors: 麦子
 */

public class ThreadUtil {

    private static ThreadLocal<String> nameLocal = new ThreadLocal<String>();

    public static String getName() {
        return nameLocal.get();
    }

    public static void setName(String name) {
        nameLocal.set(name);
    }
}
