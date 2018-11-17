package com.courses.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import com.courses.api.builder.AccountBuilder;
import com.courses.api.builder.CourseBuilder;
import com.courses.api.entity.AccountEntity;
import com.courses.api.entity.CourseEntity;
import com.courses.api.entity.UserEntity;

import com.courses.api.repository.AccountRepository;
import com.courses.api.repository.CourseRepository;
import com.courses.api.request.AccountRequest;
import com.courses.api.response.AccountResponse;
import com.courses.api.response.CourseResponse;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CourseRepository courseRepository;

    public AccountResponse update(AccountRequest request, Long userId) throws Exception, ResourceNotFoundException {
        Optional<UserEntity> userLogged = Optional.ofNullable(UserService.getUserLogged());
        userLogged.orElseThrow(() -> new Exception("Usuário não encontrado"));

        UserEntity user = userLogged.get();

        AccountEntity accountById = getAccountByUserId(userId);

        validateAccount(accountById, user);

        AccountEntity accountUpdated = AccountBuilder.buildUpdateRequest(request, accountById, user);

        AccountEntity newAccount = accountRepository.save(accountUpdated);
        return AccountBuilder.buildResponse(newAccount);
    }

    public AccountResponse returnAccountLogged() throws Exception {
        Optional<UserEntity> userLogged = Optional.ofNullable(UserService.getUserLogged());
		userLogged.orElseThrow(() -> new Exception("Usuário não encontrado"));

        AccountEntity account = userLogged.get().getAccount();
        
        return AccountBuilder.buildResponse(account);
    }

    public List<CourseResponse> listAllCoursesUserLogged() throws Exception {
        Optional<UserEntity> userLogged = Optional.ofNullable(UserService.getUserLogged());
        userLogged.orElseThrow(() -> new Exception("Usuário não encontrado"));

        AccountEntity account = userLogged.get().getAccount();

        List<CourseEntity> courses = courseRepository.findByAccount(account);

        return CourseBuilder.to(courses);
    }

    private void validateAccount(AccountEntity account, UserEntity user) throws Exception {
        if (!user.getAccount().equals(account)) {
            throw new Exception("Conta não pertence ao usuário");
        }
    }

    private AccountEntity getAccountByUserId(Long accountId) throws ResourceNotFoundException {
        Optional<AccountEntity> account = accountRepository.findById(accountId);
        account.orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada"));
        return account.get();
    }
}
