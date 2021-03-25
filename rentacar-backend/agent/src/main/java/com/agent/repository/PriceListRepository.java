package com.agent.repository;
import com.agent.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PriceListRepository extends JpaRepository<PriceList, Long> {

    Optional<PriceList> findById(Long id);
}