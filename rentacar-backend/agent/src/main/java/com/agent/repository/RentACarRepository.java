package com.agent.repository;

import com.agent.model.RentACar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentACarRepository extends JpaRepository<RentACar, Long> {

    List<RentACar> findAll();

}

