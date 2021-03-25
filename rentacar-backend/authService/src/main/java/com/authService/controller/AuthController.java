package com.authService.controller;

import com.authService.dto.UserDTO;
import com.authService.model.Role;
import com.authService.model.TokenValidationResponse;
import com.authService.model.User;
import com.authService.model.UserTokenState;
import com.authService.registration.VerificationToken;
import com.authService.repository.RoleRepository;
import com.authService.repository.VerificationTokenRepository;
import com.authService.security.TokenUtils;
import com.authService.security.auth.JwtAuthenticationRequest;
import com.authService.service.AuthService;
import com.authService.service.EmailService;
import com.authService.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private EmailService emailService;


    @PostMapping(value = "/login")
    public ResponseEntity<?> loginUser(@RequestBody JwtAuthenticationRequest authenticationRequest) {
        System.out.println("usao + " + authenticationRequest.getUsername());
        User user = userService.findByUsername(authenticationRequest.getUsername());
        if (user != null && user.isEnabledAccount()) {

            if (BCrypt.checkpw(authenticationRequest.getPassword(), user.getPassword())) {
                logger.info(String.format("Successful login! Username: '%s'.", authenticationRequest.getUsername()));
            } else {
                logger.error(String.format("Incorrect password! Username: '%s'.", authenticationRequest.getUsername()));
                return new ResponseEntity<>(new UserTokenState("error", 0), HttpStatus.OK);

            }

            if (!user.isEnabled()) {
                return new ResponseEntity<>(new UserTokenState("notActivated", 0), HttpStatus.OK);

            }

            final Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println( SecurityContextHolder.getContext().getAuthentication());
            user = (User) authentication.getPrincipal();
            String jwt = tokenUtils.generateToken(user.getUsername());
            int expiresIn = tokenUtils.getExpiredIn();
            return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
        } else {
            logger.error(String.format("No user found with username '%s'.", authenticationRequest.getUsername()));
            return new ResponseEntity<>(new UserTokenState("error", 0), HttpStatus.OK);
        }
    }

    // Endpoint za registraciju novog korisnika
    @PostMapping("/signup")
    public ResponseEntity<User> addUser(@RequestBody UserDTO userRequest) {

        logger.info("Registration start...");
        User existUser = this.userService.findByUsername(userRequest.getUsername());
        if (existUser != null) {
            logger.error(String.format("Username already exists '%s'.", userRequest.getUsername()));
            return new ResponseEntity("Username already exists", HttpStatus.FORBIDDEN);
        }
        User user = conversionService.convert(userRequest, User.class);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setEnabled(true);
        user.setDisableReservation(false);
        user.setObligation(0d);
        user.setEnabledAccount(false);
        List<Role> roles = new ArrayList<>();
        Optional<Role> role = roleRepository.findById(1L);
        roles.add(role.get());
        user.setRoles(roles);
        logger.info("Successful registration!");
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(user.getId()).toUri());
        user = userService.save(user);

        VerificationToken confirmationToken = new VerificationToken(user);
        verificationTokenRepository.save(confirmationToken);

        //user.setVerificationToken(confirmationToken);
        System.out.println(user);
        System.out.println(confirmationToken);

        try {
            emailService.sendNotificaitionAsync(user, confirmationToken);
            logger.info("Registration mail sent to: " + user.getUsername() + " - with confirmation token: " + confirmationToken.getConfirmationToken());
        }catch( Exception e ){
            System.out.println("nije poslata poruka");
            return new ResponseEntity("Email is not valid", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping(value="/confirmAccount/{token}")
    public User confirmAccount(@PathVariable("token") String confirmationToken){

        VerificationToken token = verificationTokenRepository.findByConfirmationToken(confirmationToken);
        User user = userService.findByUsername(token.getUser().getUsername());

        user.setEnabledAccount(true);
        user = userService.save(user);
        logger.info("Successfully enabled account for user: " + user.getUsername());

        return token.getUser();
    }

    @RequestMapping(value = "/validate-token")
    public ResponseEntity<TokenValidationResponse> validateToken(@RequestHeader(value="Authorization") String token){
        return ResponseEntity.ok(authService.validateToken(token));
    }

    @PostMapping(value = "/single-sign-on")
    public ResponseEntity<?> singleSignOn(@RequestBody JwtAuthenticationRequest authenticationRequest) {
        System.out.println("usao + " + authenticationRequest.getUsername());
        User user = userService.findByUsername(authenticationRequest.getUsername());
        if (user != null) {

            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            headers.add("Content-Type", "application/json");
            HttpEntity<JwtAuthenticationRequest> HReq=new HttpEntity<JwtAuthenticationRequest>(authenticationRequest,headers);
            ResponseEntity<?> res1 = restTemplate.postForEntity("https://localhost:8443/auth/single-sign-on", HReq, JwtAuthenticationRequest.class);
            System.out.println(res1);
            return ResponseEntity.ok(res1);
        } else {
            logger.error(String.format("No user found with username '%s'.", authenticationRequest.getUsername()));
            return new ResponseEntity<>(new UserTokenState("error", 0), HttpStatus.OK);
        }
    }


}
