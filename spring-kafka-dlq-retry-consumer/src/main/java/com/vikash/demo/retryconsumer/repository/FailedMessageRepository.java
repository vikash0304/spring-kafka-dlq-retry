package com.vikash.demo.retryconsumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vikash.demo.retryconsumer.entity.FailedMessage;

@Repository
public interface FailedMessageRepository extends JpaRepository<FailedMessage, Long> {
}

