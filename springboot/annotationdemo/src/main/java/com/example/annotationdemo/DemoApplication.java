package com.example.annotationdemo;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-06-11 16:21:11
 * @LastEditTime: 2019-06-19 12:15:19
 * @LastEditors: 麦子
 */

import com.example.annotationdemo.com.scan.MainScanConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
// @Import(ConfigurationTest.class)
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		// System.out.println(context.getBean(Dog.class));
		// System.out.println(context.getBean(Cat.class));

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				MainScanConfig.class);
		String[] definitionNames = applicationContext.getBeanDefinitionNames();
		for (String name : definitionNames) {
			System.out.println(name);
		}
		context.close();
		// applicationContext.close();
	}

}
