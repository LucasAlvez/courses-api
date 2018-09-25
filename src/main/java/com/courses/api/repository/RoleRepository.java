package com.courses.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courses.api.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>{

}
