package com.bookmakerApp.controller;

import com.bookmakerApp.config.security.JwtUtil;
import com.bookmakerApp.facade.dtos.AuthCredentialsRequestDto;
import com.bookmakerApp.facade.dtos.UserModelDto;
import com.bookmakerApp.facade.impl.DefaultUserFacadeImpl;
import com.bookmakerApp.model.UserModel;
import com.bookmakerApp.service.impl.UserDetailsServiceImpl;
import com.bookmakerApp.service.impl.UserRegistrationServiceImpl;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    //@Autowired
    private final AuthenticationManager authenticationManager;
    //@Autowired
    private final JwtUtil jwtUtil;
    //@Autowired
    private final UserRegistrationServiceImpl userRegistrationService;
    //@Autowired
    private final UserDetailsServiceImpl userDetailsService;
    private final DefaultUserFacadeImpl userFacade;

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

    @PostMapping("registration")
    public UserModel addUser(@RequestBody UserModel newUser) {
        if (ObjectUtils.isEmpty(newUser)) {
            throw new IllegalArgumentException("User is empty or null");
        } else {
            return userRegistrationService.registerUserAccount(newUser);
        }
    }

    @PostMapping("changePassword")
    public ResponseEntity<?> changePassword(@RequestBody final ObjectNode objectNode) {
        final String oldPassword = String.valueOf(objectNode.get("oldPassword")).replaceAll("\"", "");
        final String newPassword = String.valueOf(objectNode.get("newPassword")).replaceAll("\"", "");
        if (ObjectUtils.isNotEmpty(userFacade.changePassword(oldPassword, newPassword))) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
