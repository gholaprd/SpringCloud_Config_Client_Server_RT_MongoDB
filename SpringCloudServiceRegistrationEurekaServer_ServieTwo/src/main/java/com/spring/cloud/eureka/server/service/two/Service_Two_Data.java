package com.spring.cloud.eureka.server.service.two;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service_Two_Data {
	
	@GetMapping("/service-instances/getData")
	public String getName() {
		return "from service two instance";
	}

}
