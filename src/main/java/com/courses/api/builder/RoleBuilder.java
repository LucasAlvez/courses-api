package com.courses.api.builder;

import java.util.ArrayList;
import java.util.List;

import com.courses.api.entity.RoleEntity;
import com.courses.api.request.RoleRequest;
import com.courses.api.response.RoleResponse;

public class RoleBuilder {
	public static RoleEntity buildRequest (RoleRequest request) {
		RoleEntity entity = new RoleEntity();
		entity.setName(request.getName());
		return entity;
	}
	
	public static RoleEntity buildUpdateRequest(RoleRequest request, RoleEntity entity) {
		entity.setName(request.getName());
		return entity;
	}
	
	public static RoleResponse buildResponse (RoleEntity entity) {
		RoleResponse response = new RoleResponse();
		response.setId(entity.getId());
		response.setName(entity.getName());
		return response;
	}
	
	public static List<RoleResponse> to (List<RoleEntity> entity) {
		List<RoleResponse> list = new ArrayList<>();
		if (entity.isEmpty() || entity == null)
			return list;
		
		entity.forEach(role -> {
			list.add(buildResponse(role));
		});
		return list;
	}
	
	public static List<String> toName(List<RoleEntity> entity) {
		List<String> list = new ArrayList<>();
		if (entity.isEmpty() || entity == null)
			return list;

		entity.forEach(role -> {
			list.add(role.getName());
		});
		return list;
	}
}
