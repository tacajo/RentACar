package com.agent.repository;

import com.agent.model.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuelRepository extends JpaRepository<Fuel, Long> {

    Optional<Fuel> findById(Long id);

    Fuel findByName(String fuel);
}