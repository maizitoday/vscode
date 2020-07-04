package com.eureka.service.eurekaservice;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-05-17 02:00:26
 * @LastEditTime: 2019-05-17 02:03:03
 * @LastEditors: 麦子
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
