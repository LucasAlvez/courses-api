package com.courses.api.builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.courses.api.entity.UserEntity;
import com.courses.api.request.UserRequest;
import com.courses.api.request.UserUpdateRequest;
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

	public static UserEntity buildUpdateRequest(UserUpdateRequest request, UserEntity entity) {
		entity.setName(request.getName());
		entity.setEmail(request.getEmail());
		entity.setPass(request.getPass());
		entity.setUpdateDate(LocalDateTime.now());
		return entity;
	}

	public static UserResponse buildResponse(UserEntity entity) {
		UserResponse response = new UserResponse();
		response.setId(entity.getId());
		response.setName(entity.getName());
		response.setEmail(entity.getEmail());
		response.setRoles(entity.getRoles().toString());
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
