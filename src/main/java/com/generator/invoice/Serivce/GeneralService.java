package com.generator.invoice.Serivce;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generator.invoice.DTO.OrderInputDTO;
import com.generator.invoice.DTO.ProductDTO;
import com.generator.invoice.Entities.BillDetails;
import com.generator.invoice.Entities.OrderDetails;
import com.generator.invoice.Entities.Products;
import com.generator.invoice.Repository.BillRepository;
import com.generator.invoice.Repository.OrderRepository;
import com.generator.invoice.Repository.ProductRepository;

@Service
public class GeneralService {
	
	@Autowired
	private ProductRepository produRepository;
	
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<ProductDTO> getProductList(){
		List<Products> products = produRepository.findAll();
		return constructProductsDto(products);
	}
	
	private List<ProductDTO> constructProductsDto(List<Products> products){
		List<ProductDTO> list = new ArrayList<>();
		for(Products p: products) {
			ProductDTO pdto = new ProductDTO();
			pdto.setId(p.getId());
			pdto.setDescription(p.getDescription());
			pdto.setPrice(p.getPrice());
			pdto.setProductName(p.getProductName());
			list.add(pdto);
		}
		return list;
	}
	
	public void placeOrder(OrderInputDTO orderDetails) {
		BillDetails billDetails = new BillDetails();
		billDetails.setUserId(orderDetails.getUser().getId());
		billDetails.setPurchaseDate(new Date());
		BillDetails savedBillDetails = billRepository.save(billDetails);
		double amount = 0;
		List<OrderDetails> orders = new ArrayList<>();
		for(ProductDTO pdto : orderDetails.getProducts()) {
			OrderDetails order = new OrderDetails();
			order.setBillId(savedBillDetails.getId());
			order.setProductId(pdto.getId());
			order.setQuantity(pdto.getQuantity());
			amount += pdto.getPrice()*pdto.getQuantity();
			orders.add(order);
		}
		orderRepository.saveAll(orders);
		savedBillDetails.setBillAmount(amount);
		billRepository.save(savedBillDetails);
	}
}
