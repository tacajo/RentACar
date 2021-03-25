package com.agent.service;

import com.agent.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Optional<Role> findById(Long id);
    List<Role> findByname(String name);
}
