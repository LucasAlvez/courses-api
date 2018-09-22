package com.courses.api.builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.courses.api.entity.CategoryEntity;
import com.courses.api.request.CategoryRequest;
import com.courses.api.response.CategoryResponse;

public class CategoryBuilder {
	
	public static CategoryEntity buildRequest (CategoryRequest request) {
		CategoryEntity entity = new CategoryEntity();
		entity.setName(request.getName());
		entity.setCreateDate(LocalDateTime.now());
		entity.setUpdateDate(LocalDateTime.now());
		return entity;
	}
	
	public static CategoryEntity buildUpdateRequest(CategoryRequest request, CategoryEntity entity) {
		entity.setName(request.getName());
		entity.setUpdateDate(LocalDateTime.now());
		return entity;
	}
	
	public static CategoryResponse buildResponse (CategoryEntity entity) {
		CategoryResponse response = new CategoryResponse();
		response.setId(entity.getId());
		response.setName(entity.getName());
		response.setCreateDate(entity.getCreateDate().toString());
		response.setUpdateDate(entity.getUpdateDate().toString());
		return response;
	}
	
	public static List<CategoryResponse> to (List<CategoryEntity> entity) {
		List<CategoryResponse> list = new ArrayList<>();
		if (entity.isEmpty() || entity == null)
			return list;
		
		entity.forEach(category -> {
			list.add(buildResponse(category));
		});
		return list;
	}
	
	public static List<String> toName(List<CategoryEntity> list2) {
		List<String> list = new ArrayList<>();
		if (list2.isEmpty() || list2 == null)
			return list;

		list2.forEach(category -> {
			list.add(category.getName());
		});
		return list;
	}
}
