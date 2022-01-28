package com.allmarteureka.allmarteureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AllmartEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllmartEurekaApplication.class, args);
	}

}
