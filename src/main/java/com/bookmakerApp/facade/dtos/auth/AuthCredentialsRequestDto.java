package com.bookmakerApp.facade.dtos.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthCredentialsRequestDto {
    private String username;
    private String password;
}
