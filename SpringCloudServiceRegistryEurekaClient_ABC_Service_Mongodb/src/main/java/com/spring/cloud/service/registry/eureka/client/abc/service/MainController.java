package com.spring.cloud.service.registry.eureka.client.abc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MainController {

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private LoadBalancerClient loadBalancer;

	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {

		return "<a href='showAllServiceIds'>Show All Service Ids</a>";
	}

	@ResponseBody
	@RequestMapping(value = "/showAllServiceIds", method = RequestMethod.GET)
	public String showAllServiceIds() {

		List<String> serviceIds = this.discoveryClient.getServices();

		if (serviceIds == null || serviceIds.isEmpty()) {
			return "No services found!";
		}
		String html = "<h3>Service Ids:</h3>";
		for (String serviceId : serviceIds) {
			html += "<br><a href='showService?serviceId=" + serviceId + "'>" + serviceId + "</a>";
		}
		return html;
	}

	@ResponseBody
	@RequestMapping(value = "/showService", method = RequestMethod.GET)
	public String showFirstService(@RequestParam(defaultValue = "") String serviceId) {

		// (Need!!) eureka.client.fetchRegistry=true
		List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);

		if (instances == null || instances.isEmpty()) {
			return "No instances for service: " + serviceId;
		}
		String html = "<h2>Instances for Service Id: " + serviceId + "</h2>";

		for (ServiceInstance serviceInstance : instances) {
			html += "<h3>Instance: " + serviceInstance.getUri() + "</h3>";
			html += "Host: " + serviceInstance.getHost() + "<br>";
			html += "Port: " + serviceInstance.getPort() + "<br>";
		}

		return html;
	}

	// A REST method, To call from another service.
	// See in Lesson "Load Balancing with Ribbon".
	@ResponseBody
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {

		return "<html>Hello from ABC-SERVICE-MONGODB</html>";
	}

	@ResponseBody
	@RequestMapping(value = "/testCallAbcService", method = RequestMethod.GET)
	public String showFirstService() {

		String serviceId = "ABC-SERVICE".toLowerCase();

		// (Need!!) eureka.client.fetchRegistry=true
		List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);

		if (instances == null || instances.isEmpty()) {
			return "No instances for service: " + serviceId;
		}
		String html = "<h2>Instances for Service Id: " + serviceId + "</h2>";

		for (ServiceInstance serviceInstance : instances) {
			html += "<h3>Instance :" + serviceInstance.getUri() + "</h3>";
		}

		// Create a RestTemplate.
		RestTemplate restTemplate = new RestTemplate();

		html += "<br><h4>Call /hello of service: " + serviceId + "</h4>";

		try {
			// May be throw IllegalStateException (No instances available)
			ServiceInstance serviceInstance = this.loadBalancer.choose(serviceId);

			html += "<br>===> Load Balancer choose: " + serviceInstance.getUri();

			String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/hello";

			html += "<br>Make a Call: " + url;
			html += "<br>";

			String result = restTemplate.getForObject(url, String.class);

			html += "<br>Result: " + result;
		} catch (IllegalStateException e) {
			html += "<br>loadBalancer.choose ERROR: " + e.getMessage();
			e.printStackTrace();
		} catch (Exception e) {
			html += "<br>Other ERROR: " + e.getMessage();
			e.printStackTrace();
		}
		return html;
	}

	@Autowired
	private ProductRepo productRepo;

	@GetMapping(value = "getAll")
	public ResponseEntity<?> getAllProducts() {

		List<Products> list = (List<Products>) productRepo.findAll();

		
		return new ResponseEntity<List<Products>>(list, HttpStatus.OK);
	}
}