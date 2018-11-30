package com.courses.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.courses.api.request.AccountRequest;
import com.courses.api.response.AccountUpdateResponse;
import com.courses.api.response.CourseResponse;
import com.courses.api.response.StudentResponse;
import com.courses.api.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "accounts", description = "Contas")
@RequestMapping(value = "/v1/accounts")
public class AccountController extends Controller {

	@Autowired
	AccountService accountService;

	@ApiOperation(value = "Retorna dados da conta do loagdo")
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	// @PreAuthorize(Permissions.DEFAULT) public
	AccountUpdateResponse returnAccountLogged() throws Exception {
		return accountService.returnAccountLogged();
	}

	@ApiOperation(value = "Atuzaliza conta de um usuário")
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	// @PreAuthorize(Permissions.DEFAULT)
	public AccountUpdateResponse update(@RequestBody @Valid AccountRequest request,
			@PathVariable("userId") @Valid Long userId, BindingResult result) throws Exception {
		verifyInvalidParam(result);
		return accountService.update(request, userId);
	}

	@ApiOperation(value = "Lista todos os cursos da conta")
	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	// @PreAuthorize(Permissions.DEFAULT)
	public Page<CourseResponse> listAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "24") Integer size,
			@RequestParam(value = "sortBy", defaultValue = "id") CourseSort sortBy,
			@RequestParam(value = "sortOrder", defaultValue = "DESC") SortOrder sortOrder) throws Exception {
		return accountService.listAllCoursesUserLogged(page, size, sortBy, sortOrder);
	}

	@ApiOperation(value = "Lista todos os estudantes da conta")
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	// @PreAuthorize(Permissions.DEFAULT)
	public Page<StudentResponse> listAllStudents(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "24") Integer size,
			@RequestParam(value = "sortBy", defaultValue = "id") StudentSort sortBy,
			@RequestParam(value = "sortOrder", defaultValue = "DESC") SortOrder sortOrder) throws Exception {
		return accountService.listAllStudentsUserLogged(page, size, sortBy, sortOrder);
	}

}
