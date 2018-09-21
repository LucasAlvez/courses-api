package com.courses.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.courses.api.request.CategoryRequest;
import com.courses.api.response.CategoryResponse;
import com.courses.api.service.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "categories", description = "Categorias")
@RequestMapping(value = "/v1/categories")
public class CategoryController extends Controller {
	
	@Autowired
	CategoryService categoryService;
	
	@ApiOperation(value = "Cria uma categoria")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public CategoryResponse create(@RequestBody @Valid CategoryRequest request, BindingResult result)
			throws ResourceNotFoundException, Exception {
		verifyInvalidParam(result);
		return categoryService.create(request);
	}

}
