package com.spring.cloud.service.registry.eureka.client.abc.service;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * 
 * @author Rameshwar.Gholap
 *
 */
@Document(collection="products")
public class Products implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	private ObjectId oid; 
	private String pid;
	private String pname;
	private String price;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Products [pid=" + pid + ", pname=" + pname + ", price=" + price + "]";
	}

}
