package com.example.demo;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-05-15 17:37:12
 * @LastEditTime : 2020-07-04 16:53:26
 * @LastEditors  : Do not edit
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
