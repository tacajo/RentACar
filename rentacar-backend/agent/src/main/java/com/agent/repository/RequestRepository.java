package com.agent.repository;

import com.agent.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findAll();

    Optional<Request> findById(Long id);

    List<Request> findByUserID(Long id);
}