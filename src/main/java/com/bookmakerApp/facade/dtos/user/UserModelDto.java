package com.bookmakerApp.facade.dtos.user;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class UserModelDto {
    private String name;
    private String surname;
    private int age;
    private String mail;
    private BigDecimal bankBalance;
}
