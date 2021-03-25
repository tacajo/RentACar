package com.authService.controller;

import com.authService.dto.UserDTO;
import com.authService.dto.UserResponseDTO;
import com.authService.model.Agent;
import com.authService.model.User;
import com.authService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConversionService conversionService;

    @GetMapping(value = "")
    public User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void enabledUser(@PathVariable Long id) {
        userService.enableUser(id);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity getUserDB(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @GetMapping(value = "/get/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        Optional<User> u = userService.findById(id);
        return new UserResponseDTO(u.get().getId(), u.get().getFirstName(), u.get().getLastName());
    }

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> getUsers() {
        List<User> users = userService.getSimpleUser();
        List<UserDTO> ret = new ArrayList<>();
        for (User user : users) {
            ret.add(conversionService.convert(user, UserDTO.class));
        }
        return ret;
    }

    @GetMapping(value = "/find/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        User user = userService.findById(id).get();
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    @PostMapping(value = "/add-admin")
    public ResponseEntity addAdmin(@RequestBody UserDTO userDTO) {
        Agent agent = new Agent();
        agent.setFirstName(userDTO.getFirstName());
        agent.setLastName(userDTO.getLastName());
        agent.setUsername(userDTO.getUsername());
        agent.setAddress(userDTO.getAddress());
        agent.setPassword(userDTO.getPassword());
        agent.setBusinessNumber(userDTO.getBusinessNumber());
        User user = userService.addAdmin(agent);

        if (user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(user);
    }

    @PutMapping(value = "/disable-reservation/{id}")
    public void disableReservation(@PathVariable Long id) {
        userService.disableReservation(id);
    }

    @PutMapping(value = "/rt/add-obligation")
    public void addPay(@RequestParam("price") Double price, @RequestParam("id") Long id) {
        System.out.println("usao u auth iz rentACar-a");
        userService.addObligation(price, id);
        System.out.println("uradjeno add Obligation");
    }

    @GetMapping(value = "/pay-obligation/{id}")
    public User payObligation(@PathVariable Long id) {
        return userService.payObligation(id);
    }

}
