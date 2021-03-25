package com.rentACarService.repository;

import com.rentACarService.model.RentACar;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentACarRepository extends JpaRepository<RentACar, Long> {

    List<RentACar> findAll();

}

