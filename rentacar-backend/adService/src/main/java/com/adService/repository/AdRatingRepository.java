package com.adService.repository;

import com.adService.model.Ad;
import com.adService.model.AdRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdRatingRepository extends JpaRepository<AdRating, Long> {

    List<AdRating> findByAd(Long id);
}