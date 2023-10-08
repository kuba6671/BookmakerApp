package com.bookmakerApp.controller;

import com.bookmakerApp.facade.dtos.user.UserModelDto;
import com.bookmakerApp.facade.impl.UserFacade;
import com.bookmakerApp.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("/user/{idUser}")
    public UserModelDto getUserById(@PathVariable Long idUser) {
        return userFacade.getUserById(idUser);
    }

    @PutMapping("/user/changeUserData")
    public UserModel changeUserData(@RequestBody UserModel user) {
        return userFacade.changeUserData(user);
    }

}
