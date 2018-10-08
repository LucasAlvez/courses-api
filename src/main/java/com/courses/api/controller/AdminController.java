package com.courses.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.courses.api.enums.CourseSort;
import com.courses.api.enums.SortOrder;
import com.courses.api.enums.StudentSort;
import com.courses.api.enums.UserSort;
import com.courses.api.request.CategoryRequest;
import com.courses.api.request.UserRolesRequest;
import com.courses.api.response.CategoryResponse;
import com.courses.api.response.CourseResponse;
import com.courses.api.response.StudentResponse;
import com.courses.api.response.UserResponse;
import com.courses.api.service.CategoryService;
import com.courses.api.service.CourseService;
import com.courses.api.service.StudentService;
import com.courses.api.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "admin", description = "Administrativo")
@RequestMapping(value = "/v1/admin")
public class AdminController extends Controller {

	@Autowired
	CategoryService categoryService;

	@Autowired
	CourseService courseService;

	@Autowired
	StudentService studentService;
	
	@Autowired
	UserService userService;

	@ApiOperation(value = "Cria uma categoria")
	@RequestMapping(value = "categories", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	//@PreAuthorize(Permissions.ADMIN)
	public CategoryResponse create(@RequestBody @Valid CategoryRequest request, BindingResult result)
			throws ResourceNotFoundException, Exception {
		verifyInvalidParam(result);
		return categoryService.create(request);
	}

	@ApiOperation(value = "Atuazaliza uma categoria")
	@RequestMapping(value = "/categories/{categoryId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	//@PreAuthorize(Permissions.ADMIN)
	public CategoryResponse update(@RequestBody @Valid CategoryRequest request,
			@PathVariable("categoryId") @Valid Long categoryId, BindingResult result) throws Exception {
		verifyInvalidParam(result);
		return categoryService.update(request, categoryId);
	}

	@ApiOperation(value = "Adiciona permissão ao usuario")
	@RequestMapping(value = "/{userId}/roles", method = RequestMethod.PATCH)
	@ResponseStatus(value = HttpStatus.OK)
	//@PreAuthorize(Permissions.ADMIN)
	public void patchRoles(@PathVariable("userId") @Valid Long userId,
			@RequestBody @Valid UserRolesRequest request, BindingResult result) throws Exception {
		verifyInvalidParam(result);
		userService.patchRoles(userId, request);
	}

	@ApiOperation(value = "Lista todos os alunos")
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	//@PreAuthorize(Permissions.ADMIN)
	public Page<StudentResponse> listAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "24") Integer size,
			@RequestParam(value = "sortBy", defaultValue = "id") StudentSort sortBy,
			@RequestParam(value = "sortOrder", defaultValue = "DESC") SortOrder sortOrder) throws Exception {
		return studentService.listAll(page, size, sortBy, sortOrder);
	}
	
	@ApiOperation(value = "Lista todos os usuários")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	//@PreAuthorize(Permissions.ADMIN)
	public Page<UserResponse> listAllAccounts(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "24") Integer size,
			@RequestParam(value = "sortBy", defaultValue = "id") UserSort sortBy,
			@RequestParam(value = "sortOrder", defaultValue = "DESC") SortOrder sortOrder) throws Exception {
		return userService.listAll(page, size, sortBy, sortOrder);
	}

	@ApiOperation(value = "Lista todos os cursos")
	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	//@PreAuthorize(Permissions.ADMIN)
	public Page<CourseResponse> listAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "24") Integer size,
			@RequestParam(value = "sortBy", defaultValue = "id") CourseSort sortBy,
			@RequestParam(value = "sortOrder", defaultValue = "DESC") SortOrder sortOrder) throws Exception {
		return courseService.listAll(page, size, sortBy, sortOrder);
	}

	@ApiOperation(value = "Deleta uma categoria")
	@RequestMapping(value = "/categories/{categoryId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	//@PreAuthorize(Permissions.ADMIN)
	public void delete( @PathVariable("categoryId") @Valid Long categoryId) {
		categoryService.delete(categoryId);
	}
	
	@ApiOperation(value = "Deleta um aluno")
	@RequestMapping(value = "/students/{studentId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	//@PreAuthorize(Permissions.ADMIN)
	public void deleteStudent( @PathVariable("studentId") @Valid Long studentId) {
		studentService.delete(studentId);
	}
	
	@ApiOperation(value = "Deleta um usuário")
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	//@PreAuthorize(Permissions.ADMIN)
	public void deleteUser( @PathVariable("userId") @Valid Long userId) {
		userService.delete(userId);
	}
}
