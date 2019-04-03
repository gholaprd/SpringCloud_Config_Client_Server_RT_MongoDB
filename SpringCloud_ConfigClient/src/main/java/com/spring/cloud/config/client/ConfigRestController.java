package com.spring.cloud.config.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigRestController {

	@Autowired
	private ConfigPropertiesHolder configProperties;

	@RequestMapping("/showConfig")
	@ResponseBody
	public String showConfig() {
		System.out.println("in showConfig");
		/*
		 * String configInfo = "Copy Right: " + configProperties.getCopyright() //
		 * 
		 * + "<br/>spring.datasource.url=" + configProperties.getUrl() // +
		 * "<br/>spring.datasource.username=" + configProperties.getUserName() // +
		 * "<br/>spring.datasource.password=" + configProperties.getPassword();
		 */
		return configProperties.toString();
		// return configInfo;
	}
}
