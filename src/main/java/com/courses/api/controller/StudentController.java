package com.courses.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.courses.api.request.StudentRequest;
import com.courses.api.request.StudentUpdateRequest;
import com.courses.api.response.StudentResponse;
import com.courses.api.service.StudentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "students", description = "Alunos")
@RequestMapping(value = "/v1/students")
public class StudentController extends Controller {
	
	@Autowired
	StudentService studentService;
	
	@ApiOperation(value = "Cria um aluno")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public StudentResponse create(@RequestBody @Valid StudentRequest request, BindingResult result)
			throws ResourceNotFoundException, Exception {
		verifyInvalidParam(result);
		return studentService.create(request);
	}
	
	@ApiOperation(value = "Atuzaliza um aluno")
	@RequestMapping(value = "/{studentId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public StudentResponse update(@RequestBody @Valid StudentUpdateRequest request, @PathVariable("studentId") @Valid Long studentId,
			BindingResult result) throws Exception {
		verifyInvalidParam(result);
		return studentService.update(request, studentId);
	}
	
	@ApiOperation(value = "Busca aluno pelo seu id")
	@RequestMapping(value = "/{studentId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public StudentResponse findById(@PathVariable("studentId") @Valid Long studentId) throws Exception {
		return studentService.findById(studentId);
	}
}
