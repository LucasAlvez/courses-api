package com.courses.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.courses.api.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	@Transactional(readOnly = true)
	UserEntity findByEmail(String email);
}
