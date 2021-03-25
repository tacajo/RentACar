package com.adService.repository;

import com.adService.model.RentACar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentACarRepository extends JpaRepository<RentACar, Long> {
}
