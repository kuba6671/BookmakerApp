package com.bookmakerApp.facade.impl;

import com.bookmakerApp.facade.dtos.user.UserModelDto;
import com.bookmakerApp.facade.mappers.UserModelDtoMapper;
import com.bookmakerApp.model.UserModel;
import com.bookmakerApp.service.impl.user.UserService;
import com.bookmakerApp.service.impl.user.UserDetailsService;
import com.bookmakerApp.service.impl.user.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserService userService;
    private final UserRegistrationService userRegistrationService;
    private final UserDetailsService userDetailsService;

    public UserModelDto getUserById(Long idUser) {
        if (ObjectUtils.isNotEmpty(userService.getUserById(idUser))) {
            return UserModelDtoMapper.mapToUserModelDto(userService.getUserById(idUser));
        } else {
            return null;
        }
    }

    public UserModelDto changePassword(String oldPassword, String newPassword) {
        UserModel user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        try {
            UserModel changedUser = userService.changePassword(user, oldPassword, newPassword);
            if (ObjectUtils.isNotEmpty(changedUser)) {
                return UserModelDtoMapper.mapToUserModelDto(changedUser);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserModel changeUserData(final UserModel user) {
        return userService.changeUserPersonalData(user);
    }

    public UserModel addUser(UserModel newUser) {
        if (ObjectUtils.isEmpty(newUser)) {
            throw new IllegalArgumentException("User is empty or null");
        } else {
            return userRegistrationService.registerUserAccount(newUser);
        }
    }

    public String getUserIdByUsername(String username) {
        return userDetailsService.getUserIdByUsername(username).toString();
    }
}
