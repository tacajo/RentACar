package com.adService.repository;

import com.adService.model.CarClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarClassRepository extends JpaRepository<CarClass, Long> {

    Optional<CarClass> findById(Long id);

    CarClass findByName(String fuel);
}