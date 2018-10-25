package com.courses.api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.http.auth.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.courses.api.builder.AccountBuilder;
import com.courses.api.builder.UserBuilder;
import com.courses.api.entity.AccountEntity;
import com.courses.api.entity.UserEntity;
import com.courses.api.enums.SortOrder;
import com.courses.api.enums.UserSort;
import com.courses.api.repository.AccountRepository;
import com.courses.api.repository.UserRepository;
import com.courses.api.request.UserRequest;
import com.courses.api.request.UserRolesRequest;
import com.courses.api.response.AccountResponse;
import com.courses.api.response.UserResponse;
import com.courses.api.service.email.EmailService;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	EmailService emailService;

	@Autowired
	BCryptPasswordEncoder criptPass;

	public UserResponse create(UserRequest request) {

		request.setPass(criptPass.encode(request.getPass()));

		UserEntity user = UserBuilder.buildRequest(request);
		UserEntity newUser = userRepository.save(user);
		
		createAccount(newUser);
		
		emailService.confirmationOfRegistration(newUser);

		return UserBuilder.buildResponse(newUser);
	}
	
	private void createAccount(UserEntity user) {
		AccountEntity account = new AccountEntity();
		account.setName(user.getName());
		account.setEmail(user.getEmail());
		account.setPass(user.getPass());
		account.setUser(user);
		account.setCreateDate(LocalDateTime.now());
		account.setUpdateDate(LocalDateTime.now());
		accountRepository.save(account);
		
		user.setAccount(account);
		userRepository.save(user);
	}
	
	public AccountResponse returnUserLogged () throws Exception {
		Optional<UserEntity> userLogged = Optional.ofNullable(UserService.getUserLogged());
		userLogged.orElseThrow(() -> new Exception("Usuário não encontrado"));
		
		AccountEntity account = userLogged.get().getAccount();
		
		return AccountBuilder.buildResponse(account);
	}

	public void patchRoles(Long userId, UserRolesRequest request) {
		UserEntity user = getUserById(userId);
		user.getRoles().add(request.getRole());;
		userRepository.save(user);
	}

	public Page<UserResponse> listAll(Integer page, Integer size, UserSort sortBy, SortOrder sortOrder) {
		PageRequest pageRequest = null;

		if (SortOrder.ASC.equals(sortOrder)) {
			pageRequest = PageRequest.of(page, size, Direction.ASC, sortBy.toString());
		}

		if (SortOrder.DESC.equals(sortOrder)) {
			pageRequest = PageRequest.of(page, size, Direction.DESC, sortBy.toString());
		}

		Page<UserEntity> users = userRepository.findAll(pageRequest);

		List<UserResponse> userResponse = new ArrayList<>();
		for (UserEntity u : users) {
			userResponse.add(UserBuilder.buildResponse(u));
		}

		return new PageImpl<>(userResponse, pageRequest, userRepository.findAll(pageRequest).getSize());
	}

	private UserEntity getUserById(Long userId) throws ResourceNotFoundException {
		Optional<UserEntity> user = userRepository.findById(userId);
		user.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrada"));
		return user.get();
	}
	
	public static UserEntity getUserLogged() throws AuthenticationException {
		try {
			UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return user;
		} catch (Exception e) {
			throw new  AuthenticationException("Não foi possivel encontrar dados do usuario logado");
		}
	}
}
