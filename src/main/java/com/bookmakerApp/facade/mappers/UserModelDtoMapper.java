package com.bookmakerApp.facade.mappers;

import com.bookmakerApp.facade.dtos.user.UserModelDto;
import com.bookmakerApp.model.UserModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserModelDtoMapper {

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
