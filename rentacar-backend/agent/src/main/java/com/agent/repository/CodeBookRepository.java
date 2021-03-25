package com.agent.repository;

import com.agent.model.CodeBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodeBookRepository extends JpaRepository<CodeBook, Long> {

    Optional<CodeBook> findById(Long id);
}