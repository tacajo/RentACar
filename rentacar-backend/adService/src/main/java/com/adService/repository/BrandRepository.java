package com.adService.repository;

import com.adService.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findById(Long id);

    Brand findByName(String brand);
}