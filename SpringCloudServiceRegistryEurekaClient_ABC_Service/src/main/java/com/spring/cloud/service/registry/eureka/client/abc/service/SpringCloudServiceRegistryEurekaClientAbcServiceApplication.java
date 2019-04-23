package com.spring.cloud.service.registry.eureka.client.abc.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringCloudServiceRegistryEurekaClientAbcServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudServiceRegistryEurekaClientAbcServiceApplication.class, args);
	}

}
