package com.agent.controller;

import com.agent.dto.UserResponseDTO;
import com.agent.model.User;
import com.agent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "https://localhost:4201")
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "")
    @PreAuthorize("hasAuthority('user')")
    public User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping(value = "/get/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id){
        Optional<User> u = userService.findById(id);
        return new UserResponseDTO(u.get().getId(), u.get().getFirstName(), u.get().getLastName());
    }

}
