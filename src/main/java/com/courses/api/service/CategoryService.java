package com.courses.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courses.api.builder.CategoryBuilder;
import com.courses.api.entity.CategoryEntity;
import com.courses.api.repository.CategoryRepository;
import com.courses.api.request.CategoryRequest;
import com.courses.api.response.CategoryResponse;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public CategoryResponse create (CategoryRequest request) {
		
		CategoryEntity category = CategoryBuilder.buildRequest(request);
		CategoryEntity newCategory = categoryRepository.save(category);
		
		return CategoryBuilder.buildResponse(newCategory);
	}

}
