package com.training.coauth;

import com.training.coauth.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class CoAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoAuthApplication.class, args);
	}

}
