package com.spring.cloud.config.client;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends CrudRepository<Products, Serializable> {

	List<Products> findByPid(String pid);

	Products findByPidAndPrice(String pid, String price);
}
