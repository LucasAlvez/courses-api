package com.courses.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.courses.api.entity.AccountEntity;
import com.courses.api.entity.CourseEntity;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long>{
	
	List<CourseEntity> findByAccount(AccountEntity account);
}
