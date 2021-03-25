package com.authService.repository;

import com.authService.model.User;
import com.authService.registration.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository
        extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByConfirmationToken(String confirmationToken);

    VerificationToken findByUser(User user);
}