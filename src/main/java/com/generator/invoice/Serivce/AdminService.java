package com.generator.invoice.Serivce;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generator.invoice.DTO.ProductDTO;
import com.generator.invoice.Entities.Products;
import com.generator.invoice.Repository.ProductRepository;

@Service
public class AdminService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public void saveProducts(List<ProductDTO> productList) {
		List<Products> products = buildProducts(productList);
		productRepository.saveAll(products);
	}
	
	private List<Products> buildProducts(List<ProductDTO> productList){
		List<Products> products = new ArrayList<>();
		for(ProductDTO pdto: productList) {
			Products p = new Products();
			p.setDescription(pdto.getDescription());
			p.setPrice(pdto.getPrice());
			p.setProductName(pdto.getProductName());
			products.add(p);
		}
		return products;
	}
}
