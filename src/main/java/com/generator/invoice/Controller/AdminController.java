package com.generator.invoice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generator.invoice.DTO.ProductDTO;
import com.generator.invoice.ExceptionHandling.BusinessException;
import com.generator.invoice.Serivce.AdminService;
import com.generator.invoice.Utils.Constants;

@RestController
@RequestMapping(Constants.API_PREFIX+"/admin/")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PostMapping("save_products")
	public String saveProducts(@RequestBody List<ProductDTO> productList) throws BusinessException {
		adminService.saveProducts(productList);
		return "success";
	}
}
