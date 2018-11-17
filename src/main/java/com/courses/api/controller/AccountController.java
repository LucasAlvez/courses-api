package com.courses.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.courses.api.request.AccountRequest;
import com.courses.api.response.AccountResponse;
import com.courses.api.response.CourseResponse;
import com.courses.api.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "account", description = "Contas")
@RequestMapping(value = "/v1/accounts")
public class AccountController extends Controller {

	@Autowired
	AccountService accountService;

	@ApiOperation(value = "retorna dados da conta do loagdo")
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK) // @PreAuthorize(Permissions.DEFAULT) public
	AccountResponse returnAccountLogged() throws Exception {
		return accountService.returnAccountLogged();
	}

	@ApiOperation(value = "Atuzaliza conta de um usuário")
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	// @PreAuthorize(Permissions.DEFAULT)
	public AccountResponse update(@RequestBody @Valid AccountRequest request,
			@PathVariable("userId") @Valid Long userId, BindingResult result) throws Exception {
		verifyInvalidParam(result);
		return accountService.update(request, userId);
	}

	@ApiOperation(value = "Lista todos os cursos da conta")
	@RequestMapping(value = "courses", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	// @PreAuthorize(Permissions.DEFAULT)
	public List<CourseResponse> listAll() throws Exception {
		return accountService.listAllCoursesUserLogged();
	}

}
