package com.example.databasedemo;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-05-29 14:46:49
 * @LastEditTime: 2019-05-29 19:32:07
 * @LastEditors: 麦子
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan(value = "com.example.databasedemo.mybatis") // 扫描你mapper所在的文件夹
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
