package com.authService.service;

import com.authService.model.Agent;
import com.authService.model.Role;
import com.authService.model.User;
import com.authService.repository.RoleRepository;
import com.authService.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        User user = userRepository.findById(id).get();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            role.getUsers().remove(user);
            roleRepository.save(role);
        }
        userRepository.delete(user);
    }

    public User enableUser(Long id) {
        User user = userRepository.findById(id).get();
        user.setEnabled(!user.isEnabled());
        return userRepository.save(user);
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getSimpleUser() {
        List<User> users = userRepository.findAll();
        List<User> simpleUsers = new ArrayList<>();
        Role role = roleRepository.findById(1L).get();
        for (User user : users) {
            if (user.getRoles().contains(role)) {
                simpleUsers.add(user);
            }
        }
        return simpleUsers;
    }

    public User addAdmin(Agent agent) {
        User existUser = findByUsername(agent.getUsername());
        if (existUser != null) {
            logger.error(String.format("Username already exists '%s'.", agent.getUsername()));
            return null;
        }

        agent.setPassword(passwordEncoder.encode(agent.getPassword()));
        agent.setEnabled(true);
        List<Role> roles = new ArrayList<>();
        Optional<Role> role = roleRepository.findById(3L);
        roles.add(role.get());
        agent.setRoles(roles);
        logger.info("Successful registration!");
        return save(agent);
    }

    public void disableReservation(Long id) {
        User user = userRepository.findById(id).get();
        user.setDisableReservation(!user.isDisableReservation());
        userRepository.save(user);
    }

    public void addObligation(Double price, Long id) {
        User user = userRepository.findById(id).get();
        user.setObligation(user.getObligation() + price);
        user.setDisableReservation(true);
        userRepository.save(user);
    }

    public User payObligation(Long id) {
        User user = userRepository.findById(id).get();
        user.setObligation(0d);
        user.setDisableReservation(false);
        return userRepository.save(user);
    }
}
