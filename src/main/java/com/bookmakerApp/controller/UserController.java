package com.bookmakerApp.controller;

import com.bookmakerApp.facade.dtos.UserModelDto;
import com.bookmakerApp.facade.impl.DefaultUserFacadeImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final DefaultUserFacadeImpl userFacade;

    @GetMapping("/user/{idUser}")
    public UserModelDto getUserById(@PathVariable Long idUser){
        return userFacade.getUserById(idUser);
    }

}
