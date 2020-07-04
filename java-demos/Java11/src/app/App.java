package app;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-04-21 15:14:13
 * @LastEditTime : 2020-06-29 16:28:39
 * @LastEditors  : Do not edit
 */

import java.util.HashSet;
import java.util.Iterator;

public class App implements jdk18 {

    public void show() {
        int count = 0;
        System.out.println(++count);
    }

    public static void main(final String[] args) {

        User user = new User();
        user.setUsername("1");

        User user2 = new User();
        user2.setUsername("1");

        HashSet<User> hashSet = new HashSet<User>();
        hashSet.add(user);
        hashSet.add(user2);

        Iterator<User> iterator = hashSet.iterator();

        while (iterator.hasNext()) {
            User next = iterator.next();
            System.out.println(next);
        }

    }
}