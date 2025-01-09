package com.generator.invoice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generator.invoice.DTO.StringResponseDTO;
import com.generator.invoice.DTO.UserMasterDTO;
import com.generator.invoice.ExceptionHandling.BusinessException;
import com.generator.invoice.Serivce.AuthService;
import com.generator.invoice.Utils.Constants;

@RestController
@RequestMapping(Constants.API_PREFIX+"/auth")
public class AuthController {
	
	@Autowired
	private AuthService authservice;
	
	@PostMapping("/login")
	public StringResponseDTO Login(@RequestBody UserMasterDTO user) throws BusinessException {
		return authservice.Login(user);
	}
	
	@PostMapping("/register")
	public String register(@RequestBody UserMasterDTO user) throws BusinessException {
		return authservice.registerUser(user);
	}
}
