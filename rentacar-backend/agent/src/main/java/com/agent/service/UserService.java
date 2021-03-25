package com.agent.service;

import com.agent.model.User;
import com.agent.registration.VerificationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long id);
    User findByUsername(String username);
    User save(User user);
}
