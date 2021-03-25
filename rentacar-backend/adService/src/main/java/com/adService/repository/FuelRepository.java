package com.adService.repository;

import com.adService.model.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuelRepository extends JpaRepository<Fuel, Long> {

    Optional<Fuel> findById(Long id);

    Fuel findByName(String fuel);
}