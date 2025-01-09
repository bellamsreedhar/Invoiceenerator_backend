package com.generator.invoice.Serivce;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.generator.invoice.DTO.StringResponseDTO;
import com.generator.invoice.DTO.UserMasterDTO;
import com.generator.invoice.Entities.UserMaster;
import com.generator.invoice.ExceptionHandling.BusinessException;
import com.generator.invoice.ExceptionHandling.ExceptionCodes;
import com.generator.invoice.ExceptionHandling.ExceptionMessages;
import com.generator.invoice.Repository.UserMasterRepository;
import com.generator.invoice.Utils.JWTUtils;

@Service
public class AuthService {
	
	@Autowired
	private UserMasterRepository userMasterRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public String registerUser(UserMasterDTO userMasterDTO) throws BusinessException {

		checkUserExistance(userMasterDTO.getEmail()); 
		UserMaster user = constructUser(userMasterDTO);
		userMasterRepository.save(user);
		return "User Created";
	}
	
	public StringResponseDTO Login(UserMasterDTO userMasterDTO) throws BusinessException {
		UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(userMasterDTO.getEmail(), userMasterDTO.getPassword());
		Authentication authentication = authenticationManager.authenticate(userAuth);
		if(!authentication.isAuthenticated()) {
			throw new BusinessException(ExceptionCodes.USER_INVALID,ExceptionMessages.USER_INVALID);
		}
		String token = jwtUtils.generateToken(userMasterDTO.getEmail());
		StringResponseDTO resp = new StringResponseDTO();
		resp.setOutput(token);
		return resp;
	}
	
	private UserMaster constructUser(UserMasterDTO userMasterDTO) {
		UserMaster user = new UserMaster();
		user.setAdmin(Boolean.FALSE);
		user.setEmail(userMasterDTO.getEmail());
		user.setFirstName(userMasterDTO.getFirstName());
		user.setLastName(userMasterDTO.getLastName());
		user.setAddress(userMasterDTO.getAddress());
		user.setPhone(userMasterDTO.getPhone());
		user.setPassword(passwordEncoder.encode(userMasterDTO.getPassword()));
		return user;
	}
	
	private void checkUserExistance(String email) throws BusinessException {
		Optional<UserMaster> users = userMasterRepository.findByEmail(email);
		if(users.isPresent()) {
			throw new BusinessException(ExceptionCodes.USER_ALREADY_EXISTED,ExceptionMessages.USER_ALREADY_EXISTED);
		}
	}
}
