package com.generator.invoice.DTO;

import java.util.List;

public class OrderInputDTO {
	
	private UserMasterDTO user;
	private List<ProductDTO> products;
	
	public UserMasterDTO getUser() {
		return user;
	}
	public void setUser(UserMasterDTO user) {
		this.user = user;
	}
	public List<ProductDTO> getProducts() {
		return products;
	}
	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}
	
	
}
