package com.bookmakerApp.controller;

import com.bookmakerApp.model.UserModel;
import com.bookmakerApp.service.impl.UserRegistrationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private UserRegistrationServiceImpl userRegistrationService;

    @PostMapping("/users")
    public UserModel addUser(@RequestBody UserModel newUser) {
        if (ObjectUtils.isEmpty(newUser)) {
            throw new IllegalArgumentException("User is empty or null");
        } else {
            return userRegistrationService.registerUserAccount(newUser);
        }
    }
}
