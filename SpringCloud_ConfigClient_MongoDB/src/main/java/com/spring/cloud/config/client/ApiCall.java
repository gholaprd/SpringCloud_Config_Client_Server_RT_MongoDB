package com.spring.cloud.config.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



/**
 * 
 * @author Rameshwar.Gholap
 *
 */

@SuppressWarnings({ "rawtypes", "unused" })
@RestController

public class ApiCall {

	@Autowired
	private ProductRepo productRepo;

	/**
	 * 
	 * @return list of products
	 */

	@GetMapping(value = "getAll")
	public ResponseEntity<?> getAllProducts() {

		List<Products> list = (List<Products>) productRepo.findAll();

		System.out.println("Compilation Done!!! ...");
		return new ResponseEntity<List<Products>>(list, HttpStatus.OK);
	}

	@GetMapping(value = "findByPid")
	public ResponseEntity<?> getDetailsByPid(@RequestParam("pid") String pid) {

		List<Products> listOfProducts = productRepo.findByPid(pid);
		if (listOfProducts.isEmpty()) {
			return new ResponseEntity<String>("unable to find products of this==" + pid, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Products>>(listOfProducts, HttpStatus.OK);
	}

	@PostMapping(value = "findByPidAndPrice")
	public ResponseEntity<?> getDetailsByPidAndPrice(@RequestBody Products products) {

		Products product = productRepo.findByPidAndPrice(products.getPid(), products.getPrice());
		if (products == null) {
			return new ResponseEntity<String>("unable to find products of this==" + products.getPid(),
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Products>(product, HttpStatus.OK);
	}
}
