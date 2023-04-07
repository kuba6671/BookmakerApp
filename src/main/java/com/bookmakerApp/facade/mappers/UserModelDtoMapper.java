package com.bookmakerApp.facade.mappers;

import com.bookmakerApp.facade.dtos.UserModelDto;
import com.bookmakerApp.model.UserModel;

public class UserModelDtoMapper {
    private UserModelDtoMapper() {
    }

    public static UserModelDto mapToUserModelDto(UserModel user) {
        return UserModelDto.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .mail(user.getMail())
                .bankBalance(user.getAccount().getBankBalance())
                .build();
    }
}
