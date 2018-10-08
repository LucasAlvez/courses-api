package com.courses.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

	@ApiOperation(value = "Busca categoria pelo seu id")
	@RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public CategoryResponse findById(@PathVariable("categoryId") @Valid Long categoryId) throws Exception {
		return categoryService.findById(categoryId);
	}

	@ApiOperation(value = "Lista todas as categorias")
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<CategoryResponse> listAll() {
		return categoryService.listAll();
	}
}
