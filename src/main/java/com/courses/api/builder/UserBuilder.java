package com.courses.api.builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.courses.api.entity.RoleEntity;
import com.courses.api.entity.UserEntity;
import com.courses.api.request.UserRequest;
import com.courses.api.response.RoleResponse;
import com.courses.api.response.UserResponse;

public class UserBuilder {
	
	public static UserEntity buildRequest(UserRequest request) {
		UserEntity entity = new UserEntity();
		entity.setName(request.getName());
		entity.setEmail(request.getEmail());
		entity.setPass(request.getPass());
		entity.setCreateDate(LocalDateTime.now());
		entity.setUpdateDate(LocalDateTime.now());
		return entity;
	}
	
	private static Set<RoleResponse> build(Set<RoleEntity> roles) {
		Set<RoleResponse> responseRoles = new HashSet<>();
		
		roles.forEach(role -> {
			responseRoles.add(new RoleResponse(role.getId(), role.getRole()));
		});
		
		return responseRoles;
	}

	public static UserResponse buildResponse(UserEntity entity) {
		UserResponse response = new UserResponse();
		response.setId(entity.getId());
		response.setName(entity.getName());
		response.setEmail(entity.getEmail());
		response.setRoles(build(entity.getRoles()));
		response.setCreateDate(entity.getCreateDate().toString());
		response.setUpdateDate(entity.getUpdateDate().toString());
		return response;
	}

	public static List<UserResponse> to(List<UserEntity> entity) {
		List<UserResponse> list = new ArrayList<>();
		if (entity.isEmpty() || entity == null)
			return list;

		entity.forEach(user -> {
			list.add(buildResponse(user));
		});
		return list;
	}

}
