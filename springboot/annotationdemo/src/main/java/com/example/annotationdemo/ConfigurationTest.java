package com.example.annotationdemo;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-06-11 16:37:13
 * @LastEditTime: 2019-06-19 12:06:59
 * @LastEditors: 麦子
 */

import org.springframework.context.annotation.Bean;

// @Configuration
public class ConfigurationTest {

    @Bean
    public Dog getDog() {
        return new Dog();
    }

    @Bean
    public Cat getCat() {
        return new Cat();
    }

    // public ConfigurationTest() {
    // System.out.println("容器已经加载");
    // }

    // @Bean(name = "myname")
    // public BeanTest getBeanTest() {
    // return new BeanTest();
    // }

    // public static void main(String[] args) {
    // ApplicationContext context = new
    // AnnotationConfigApplicationContext(ConfigurationTest.class);
    // BeanTest tb = (BeanTest) context.getBean("myname");
    // String str = tb.message();
    // System.out.println(str);
    // }

}