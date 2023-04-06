package com.bookmakerApp.facade.impl;

import com.bookmakerApp.facade.dtos.UserModelDto;
import com.bookmakerApp.facade.interfaces.UserFacade;
import com.bookmakerApp.facade.mappers.UserModelDtoMapper;
import com.bookmakerApp.model.UserModel;
import com.bookmakerApp.service.impl.DefaultUserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DefaultUserFacadeImpl implements UserFacade {

    private final DefaultUserServiceImpl userService;

    @Override
    public UserModelDto getUserById(Long idUser) {
        if (ObjectUtils.isNotEmpty(userService.getUserById(idUser))) {
            return UserModelDtoMapper.mapToUserModelDto(userService.getUserById(idUser));
        } else {
            return null;
        }
    }

    @Override
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

    @Override
    public UserModel changeUserData(final UserModel user) {
        return userService.changeUserPersonalData(user);
    }
}
