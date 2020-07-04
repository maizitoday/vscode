package app.collection;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-19 14:32:32
 * @LastEditTime: 2020-01-01 17:53:44
 * @LastEditors: 麦子
 */

import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;

public class CollectionTest {

    public static void main(String[] args) {

        ArrayDeque<Person> list = new ArrayDeque<Person>();

        Person pp1 = new Person("小米", 10);
        Person pp2 = new Person("小花", 2);
        Person pp3 = new Person("小猫", 1);

        Person pp4 = pp3;

        System.out.println(pp3.hashCode() + "---" + pp4.hashCode());

    }
}

class Person implements Comparable<Person> {

    public String name;
    public int age;

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name + ":" + this.age;
    }

    @Override
    public int compareTo(Person o) {

        return this.age - o.age;
    }

}