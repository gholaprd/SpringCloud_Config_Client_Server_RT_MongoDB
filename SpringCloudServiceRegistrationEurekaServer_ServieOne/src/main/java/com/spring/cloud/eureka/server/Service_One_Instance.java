package com.spring.cloud.eureka.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service_One_Instance {
	@GetMapping("/service-instances/getData")
	public String getName() {
		return "from service one instance";
	}

	
}
