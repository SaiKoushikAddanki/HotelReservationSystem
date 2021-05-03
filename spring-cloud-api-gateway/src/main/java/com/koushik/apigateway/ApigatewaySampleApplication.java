package com.koushik.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableFeignClients(basePackages= {"com.koushik.apigateway.configuration", "com.koushik.apigateway.filters", "com.koushik.apigateway.service"})
public class ApigatewaySampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigatewaySampleApplication.class, args);
	}

}
