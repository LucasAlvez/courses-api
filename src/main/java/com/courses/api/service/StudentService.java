package com.courses.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.courses.api.builder.CourseBuilder;
import com.courses.api.entity.AccountEntity;
import com.courses.api.entity.CourseEntity;
import com.courses.api.entity.StudentEntity;
import com.courses.api.entity.UserEntity;
import com.courses.api.enums.CourseSort;
import com.courses.api.enums.SortOrder;
import com.courses.api.response.CourseResponse;

@Service
public class StudentService {

	public Page<CourseResponse> listCoursesByStudent(Integer page, Integer size, CourseSort sortBy, SortOrder sortOrder)
			throws Exception {
		Optional<UserEntity> userLogged = Optional.ofNullable(UserService.getUserLogged());
		userLogged.orElseThrow(() -> new Exception("Usuário não encontrado"));

		AccountEntity account = userLogged.get().getAccount();

		StudentEntity student = account.getStudent();

		PageRequest pageRequest = null;

		if (SortOrder.ASC.equals(sortOrder)) {
			pageRequest = PageRequest.of(page, size, Direction.ASC, sortBy.toString());
		}

		if (SortOrder.DESC.equals(sortOrder)) {
			pageRequest = PageRequest.of(page, size, Direction.DESC, sortBy.toString());
		}

		List<CourseResponse> response = new ArrayList<>();

		if (student != null) {
			for (CourseEntity course : student.getCourses()) {
				response.add(CourseBuilder.buildResponse(course));
			}
		}

		return new PageImpl<>(response, pageRequest, response.size());
	}
}
