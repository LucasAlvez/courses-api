package com.courses.api.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.http.auth.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.courses.api.entity.UserEntity;
import com.courses.api.request.UserRequest;
import com.courses.api.response.AccountResponse;
import com.courses.api.response.UserResponse;
import com.courses.api.security.JWTUtil;
import com.courses.api.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "users", description = "Usuários")
@RequestMapping(value = "/v1/users")
public class UserController extends Controller {
	
	@Autowired
	UserService userService;
	
	@Autowired
	JWTUtil jwtUtil;
	
	@ApiOperation(value = "Cria um usuário")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public UserResponse create(@RequestBody @Valid UserRequest request, BindingResult result)
			throws ResourceNotFoundException, Exception {
		verifyInvalidParam(result);
		return userService.create(request);
	}
	
	@ApiOperation(value = "Busca usuário pelo seu id")
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public UserResponse findById(@PathVariable("userId") @Valid Long userId) throws Exception {
		return userService.findById(userId);
	}
	
	@ApiOperation(value = "retorna usuário loagdo")
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public AccountResponse returnUserLogged() throws Exception {
		return userService.returnUserLogged();
	}
	
	@ApiOperation(value = "Atuzaliza Token")
	@RequestMapping(value="/auth/refresh_token", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) throws AuthenticationException {
	UserEntity user = UserService.getUserLogged();
	String token = jwtUtil.generateToken(user.getUsername());
	response.addHeader("Authorization", "Bearer " + token);
	return ResponseEntity.noContent().build();
	}

}
