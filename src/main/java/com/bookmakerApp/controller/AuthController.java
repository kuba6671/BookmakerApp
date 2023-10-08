package com.bookmakerApp.controller;

import com.bookmakerApp.config.security.JwtUtil;
import com.bookmakerApp.facade.dtos.auth.AuthCredentialsRequestDto;
import com.bookmakerApp.facade.impl.UserFacade;
import com.bookmakerApp.model.UserModel;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserFacade userFacade;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthCredentialsRequestDto request) {
        try {
            final Authentication authenticate = authenticationManager.
                    authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(), request.getPassword()));

            final User user = (User) authenticate.getPrincipal();
            final String id = userFacade.getUserIdByUsername(user.getUsername());

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
        return userFacade.addUser(newUser);
    }

    @PutMapping("changePassword")
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
