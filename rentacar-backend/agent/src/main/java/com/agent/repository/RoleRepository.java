package com.agent.repository;

import com.agent.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findById(Long id);
    List<Role> findByName(String name);

}
