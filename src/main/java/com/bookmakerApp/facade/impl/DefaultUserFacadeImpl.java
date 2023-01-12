package com.bookmakerApp.facade.impl;

import com.bookmakerApp.facade.dtos.UserModelDto;
import com.bookmakerApp.facade.interfaces.UserFacade;
import com.bookmakerApp.facade.mappers.UserModelDtoMapper;
import com.bookmakerApp.service.impl.DefaultUserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DefaultUserFacadeImpl implements UserFacade {

    private final DefaultUserServiceImpl userService;

    private UserModelDtoMapper userModelDtoMapper;

    @Override
    public UserModelDto getUserById(Long idUser) {
        if (ObjectUtils.isNotEmpty(userService.getUserById(idUser))) {
            return userModelDtoMapper.mapToUserModelDto(userService.getUserById(idUser));
        } else {
            return null;
        }
    }
}
