package com.homelyfooduser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HomelyfoodUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomelyfoodUserApplication.class, args);
	}

}
