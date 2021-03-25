package com.rentACarService.repository;

import com.rentACarService.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdRepository extends JpaRepository<Ad, Long> {

    List<Ad> findAll();

    Optional<Ad> findById(Long id);
}