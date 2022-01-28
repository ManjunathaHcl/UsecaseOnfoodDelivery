package com.homelyfoodproviders;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class HomelyfoodProvidersApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomelyfoodProvidersApplication.class, args);
	}

}
