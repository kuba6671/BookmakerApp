package com.bookmakerApp.controller;

import com.bookmakerApp.config.security.JwtUtil;
import com.bookmakerApp.facade.dtos.AuthCredentialsRequestDto;
import com.bookmakerApp.model.UserModel;
import com.bookmakerApp.service.impl.UserDetailsServiceImpl;
import com.bookmakerApp.service.impl.UserRegistrationServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRegistrationServiceImpl userRegistrationService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthCredentialsRequestDto request) {
        try {
            Authentication authenticate = authenticationManager.
                    authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(), request.getPassword()));

            User user = (User) authenticate.getPrincipal();
            String id = userDetailsService.getUserIdByUsername(user.getUsername()).toString();

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtUtil.generateToken(user))
                    .header("id-user", id)
                    .body(user);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/registration")
    public UserModel addUser(@RequestBody UserModel newUser) {
        if (ObjectUtils.isEmpty(newUser)) {
            throw new IllegalArgumentException("User is empty or null");
        } else {
            return userRegistrationService.registerUserAccount(newUser);
        }
    }
}
