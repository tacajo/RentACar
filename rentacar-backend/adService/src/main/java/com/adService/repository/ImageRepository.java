package com.adService.repository;
import java.util.Optional;
import com.adService.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepository extends JpaRepository<ImageModel, Long> {
    Optional<ImageModel> findByName(String name);
}