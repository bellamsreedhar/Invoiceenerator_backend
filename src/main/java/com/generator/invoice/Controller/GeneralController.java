package com.generator.invoice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generator.invoice.DTO.OrderInputDTO;
import com.generator.invoice.DTO.ProductDTO;
import com.generator.invoice.Serivce.GeneralService;
import com.generator.invoice.Utils.Constants;

@RestController
@RequestMapping(Constants.API_PREFIX+"/user")
public class GeneralController {
	
	@Autowired
	private GeneralService generalService;
	
	@GetMapping("/getProducts")
	public List<ProductDTO> getProducts(){
		return generalService.getProductList();
	}
	
	@PostMapping("/place_order")
	public String placeOrder(@RequestBody OrderInputDTO order) {
		generalService.placeOrder(order);
		return "Order Placed";
	}

}
