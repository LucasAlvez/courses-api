package com.courses.api.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.http.auth.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.courses.api.entity.UserEntity;
import com.courses.api.enums.SortOrder;
import com.courses.api.enums.UserSort;
import com.courses.api.request.EmailRequest;
import com.courses.api.request.RoleRequest;
import com.courses.api.request.UserRequest;
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

	@ApiOperation(value = "retorna usuário loagdo")
	@RequestMapping(value = "/logged", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	// @PreAuthorize(Permissions.DEFAULT)
	public UserResponse returnUserLogged() throws Exception {
		return userService.returnUserLogged();
	}

	@ApiOperation(value = "Lista todos os usuários")
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	// @PreAuthorize(Permissions.ADMIN)
	public Page<UserResponse> listAllAccounts(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "24") Integer size,
			@RequestParam(value = "sortBy", defaultValue = "id") UserSort sortBy,
			@RequestParam(value = "sortOrder", defaultValue = "DESC") SortOrder sortOrder) throws Exception {
		return userService.listAll(page, size, sortBy, sortOrder);
	}

	@ApiOperation(value = "Atuzaliza Token")
	@RequestMapping(value = "/auth/refresh_token", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	// @PreAuthorize(Permissions.DEFAULT)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) throws AuthenticationException {
		UserEntity user = UserService.getUserLogged();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Adiciona permissão ao usuario")
	@RequestMapping(value = "/{userId}/roles", method = RequestMethod.PATCH)
	@ResponseStatus(value = HttpStatus.OK)
	// @PreAuthorize(Permissions.ADMIN)
	public void patchRoles(@PathVariable("userId") @Valid Long userId, @RequestBody @Valid RoleRequest request,
			BindingResult result) throws Exception {
		verifyInvalidParam(result);
		userService.patchRoles(userId, request);
	}

	@ApiOperation(value = "Envia nova senha do usuário")
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	// @PreAuthorize(Permissions.DEFAULT)
	public void sendNewPassword(@RequestBody @Valid EmailRequest request, BindingResult result) throws Exception {
		verifyInvalidParam(result);
		userService.sendNewPassword(request);
	}
}
