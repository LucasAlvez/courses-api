package com.courses.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courses.api.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}
