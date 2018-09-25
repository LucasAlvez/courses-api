package com.courses.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.courses.api.builder.UserBuilder;
import com.courses.api.entity.RoleEntity;
import com.courses.api.entity.UserEntity;
import com.courses.api.repository.RoleRepository;
import com.courses.api.repository.UserRepository;
import com.courses.api.request.UserRequest;
import com.courses.api.response.UserResponse;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	BCryptPasswordEncoder criptPass;
	
	public UserResponse create (UserRequest request) {
		request.setPass(criptPass.encode(request.getPass()));
		List<RoleEntity> roles = roleRepository.findAllById(request.getRoles());
		UserEntity user = UserBuilder.buildRequest(request, roles);
		UserEntity newUser = userRepository.save(user);
		
		//emailService.confirmationOfRegistration(newUser);
		
		return UserBuilder.buildResponse(newUser);
	}

}
