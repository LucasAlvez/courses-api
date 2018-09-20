package com.courses.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.courses.api.entity.CourseEntity;

@Repository
public interface CourseRepository extends CrudRepository<CourseEntity, Long>, JpaRepository<CourseEntity, Long>{
}
