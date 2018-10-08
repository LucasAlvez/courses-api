package com.courses.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.courses.api.entity.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}
