package com.agent.repository;

import com.agent.model.GearShift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GearShiftRepository extends JpaRepository<GearShift, Long> {

    Optional<GearShift> findById(Long id);

    GearShift findByName(String gearShift);
}