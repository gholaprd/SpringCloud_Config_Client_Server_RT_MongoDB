package com.spring.cloud.service.discovery.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudServiceDiscoveryClientTwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudServiceDiscoveryClientTwoApplication.class, args);
	}

}
