package com.courses.api.builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.courses.api.entity.RoleEntity;
import com.courses.api.entity.UserEntity;
import com.courses.api.enums.UserType;
import com.courses.api.request.UserRequest;
import com.courses.api.response.UserResponse;

public class UserBuilder {
	
	public static UserEntity buildRequest(UserRequest request, List<RoleEntity> roles) {
		UserEntity entity = new UserEntity();
		entity.setName(request.getName());
		entity.setEmail(request.getEmail());
		entity.setPass(request.getPass());
		entity.setRoles(roles);
		entity.setType(UserType.STUDENT.name());
		entity.setCreateDate(LocalDateTime.now());
		entity.setUpdateDate(LocalDateTime.now());
		return entity;
	}

	public static UserEntity buildUpdateRequest(UserRequest request, UserEntity entity, List<RoleEntity> roles) {
		entity.setName(request.getName());
		entity.setEmail(request.getEmail());
		entity.setPass(request.getPass());
		entity.setRoles(roles);
	//	entity.setType(UserType);
		entity.setUpdateDate(LocalDateTime.now());
		return entity;
	}

	public static UserResponse buildResponse(UserEntity entity) {
		UserResponse response = new UserResponse();
		response.setId(entity.getId());
		response.setName(entity.getName());
		response.setEmail(entity.getEmail());
		response.setType(entity.getType());
		response.setRoles(RoleBuilder.toName(entity.getRoles()));
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
