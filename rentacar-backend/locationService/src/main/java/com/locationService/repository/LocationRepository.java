package com.locationService.repository;

import com.locationService.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository  extends JpaRepository<Location, Long> {
}
