package com.config.maizi.serviceconfig;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-05-16 20:18:51
 * @LastEditTime: 2019-05-16 20:49:53
 * @LastEditors: 麦子
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
