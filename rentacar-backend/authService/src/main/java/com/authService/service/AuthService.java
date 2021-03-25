package com.authService.service;

import com.authService.model.TokenValidationResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    void login();

    void register();

    void addToCart();

    void blockUser();

    void activateUser();

    void removeUser();

    void addAgent();

    TokenValidationResponse validateToken(String token);
}
