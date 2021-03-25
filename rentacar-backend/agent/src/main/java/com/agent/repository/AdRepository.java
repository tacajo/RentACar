package com.agent.repository;

import com.agent.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdRepository extends JpaRepository<Ad, Long> {

    Optional<Ad> findById(Long id);

    List<Ad> findByCity(String city);
}