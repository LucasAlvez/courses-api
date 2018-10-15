package com.courses.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courses.api.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

}
