package com.agent.repository;

import com.agent.model.User;
import com.agent.registration.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository
        extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByConfirmationToken(String confirmationToken);

    VerificationToken findByUser(User user);
}