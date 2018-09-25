package com.courses.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courses.api.builder.RoleBuilder;
import com.courses.api.entity.RoleEntity;
import com.courses.api.repository.RoleRepository;
import com.courses.api.request.RoleRequest;
import com.courses.api.response.RoleResponse;

@Service
public class RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	public RoleResponse create (RoleRequest request) {
		
		RoleEntity role = RoleBuilder.buildRequest(request);
		RoleEntity newRole = roleRepository.save(role);
		
		return RoleBuilder.buildResponse(newRole);
	}

}
