package com.sentinel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sentinel.model.DNA;

@Repository
public interface SentinelRepository extends JpaRepository<DNA, Long> {

}
