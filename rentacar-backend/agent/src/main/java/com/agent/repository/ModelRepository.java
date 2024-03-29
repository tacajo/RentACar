package com.agent.repository;

import com.agent.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Long> {

    Optional<Model> findById(Long id);

    Model findByName(String model);
}