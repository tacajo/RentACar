package com.agent.repository;

import com.agent.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findById(Long id);

    List<Car> findByModel(Model model);

    List<Car> findByBrand(Brand brand);

    List<Car> findByFuel(Fuel fuel);

    List<Car> findByCarClass(CarClass carClass);

    List<Car> findByGearShift(GearShift gearShift);

}