package com.bookmakerApp.controller;

import com.bookmakerApp.facade.dtos.UserModelDto;
import com.bookmakerApp.facade.impl.DefaultUserFacadeImpl;
import com.bookmakerApp.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final DefaultUserFacadeImpl userFacade;

    @GetMapping("/user/{idUser}")
    public UserModelDto getUserById(@PathVariable Long idUser){
        return userFacade.getUserById(idUser);
    }

    @PutMapping("/user/changeUserData")
    public UserModel changeUserData(@RequestBody UserModel user){
        return userFacade.changeUserData(user);
    }

}
