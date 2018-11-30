package com.courses.api.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.courses.api.builder.AccountBuilder;
import com.courses.api.builder.CourseBuilder;
import com.courses.api.builder.StudentBuilder;
import com.courses.api.entity.AccountEntity;
import com.courses.api.entity.CourseEntity;
import com.courses.api.entity.StudentEntity;
import com.courses.api.entity.UserEntity;
import com.courses.api.enums.CourseSort;
import com.courses.api.enums.SortOrder;
import com.courses.api.enums.StudentSort;
import com.courses.api.repository.AccountRepository;
import com.courses.api.repository.CourseRepository;
import com.courses.api.repository.StudentRepository;
import com.courses.api.request.AccountRequest;
import com.courses.api.response.AccountUpdateResponse;
import com.courses.api.response.CourseResponse;
import com.courses.api.response.StudentResponse;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CourseRepository courseRepository;
    
    @Autowired
    StudentRepository studentRepository;


    public AccountUpdateResponse update(AccountRequest request, Long userId) throws Exception, ResourceNotFoundException {
        Optional<UserEntity> userLogged = Optional.ofNullable(UserService.getUserLogged());
        userLogged.orElseThrow(() -> new Exception("Usuário não encontrado"));

        UserEntity user = userLogged.get();

        AccountEntity accountById = getAccountByUserId(userId);

        validateAccount(accountById, user);

        AccountEntity accountUpdated = AccountBuilder.buildUpdateRequest(request, accountById, user);

        AccountEntity newAccount = accountRepository.save(accountUpdated);
        return AccountBuilder.buildResponseDetails(newAccount);
    }

    public AccountUpdateResponse returnAccountLogged() throws Exception {
        Optional<UserEntity> userLogged = Optional.ofNullable(UserService.getUserLogged());
		userLogged.orElseThrow(() -> new Exception("Usuário não encontrado"));

        AccountEntity account = userLogged.get().getAccount();
        
        return AccountBuilder.buildResponseDetails(account);
    }

    public Page<CourseResponse> listAllCoursesUserLogged(Integer page, Integer size, CourseSort sortBy, SortOrder sortOrder) throws Exception {
        Optional<UserEntity> userLogged = Optional.ofNullable(UserService.getUserLogged());
        userLogged.orElseThrow(() -> new Exception("Usuário não encontrado"));

        AccountEntity account = userLogged.get().getAccount();

        PageRequest pageRequest = null;

		if (SortOrder.ASC.equals(sortOrder)) {
			pageRequest = PageRequest.of(page, size, Direction.ASC, sortBy.toString());
		}

		if (SortOrder.DESC.equals(sortOrder)) {
			pageRequest = PageRequest.of(page, size, Direction.DESC, sortBy.toString());
		}

		List<CourseEntity> courses = courseRepository.findByAccount(account);

		List<CourseResponse> coursesResponse = new ArrayList<>();
		for (CourseEntity c : courses) {
			coursesResponse.add(CourseBuilder.buildResponse(c));
		}

		return new PageImpl<>(coursesResponse, pageRequest, coursesResponse.size());
    }
    
   public Page<StudentResponse> listAllStudentsUserLogged(Integer page, Integer size, StudentSort sortBy, SortOrder sortOrder) throws Exception {
        Optional<UserEntity> userLogged = Optional.ofNullable(UserService.getUserLogged());
        userLogged.orElseThrow(() -> new Exception("Usuário não encontrado"));

        AccountEntity account = userLogged.get().getAccount();

        PageRequest pageRequest = null;

		if (SortOrder.ASC.equals(sortOrder)) {
			pageRequest = PageRequest.of(page, size, Direction.ASC, sortBy.toString());
		}

		if (SortOrder.DESC.equals(sortOrder)) {
			pageRequest = PageRequest.of(page, size, Direction.DESC, sortBy.toString());
		}

		List<CourseEntity> courses = courseRepository.findByAccount(account);
		
		Set<StudentEntity> studentsSet = new HashSet<>();
		courses.forEach(course -> {
			studentsSet.addAll(course.getStudents());
		});
		
		List<StudentResponse> studentsReponse = new ArrayList<>();
		studentsSet.forEach(student -> {
			studentsReponse.add(StudentBuilder.buildResponse(student));
		});

		return new PageImpl<>(studentsReponse, pageRequest, studentsReponse.size());
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
