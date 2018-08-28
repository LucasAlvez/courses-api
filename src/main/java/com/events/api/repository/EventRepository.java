package com.events.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.events.api.model.EventEntity;

@Repository
public interface EventRepository extends CrudRepository<EventEntity, Long>, JpaRepository<EventEntity, Long>{
}
