package com.eureka5003;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Eureka5003Application {

	public static void main(String[] args) {
		SpringApplication.run(Eureka5003Application.class, args);
	}

}
