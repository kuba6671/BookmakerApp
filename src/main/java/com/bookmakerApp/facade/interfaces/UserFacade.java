package com.bookmakerApp.facade.interfaces;

import com.bookmakerApp.facade.dtos.UserModelDto;

public interface UserFacade {
    UserModelDto getUserById(Long idUser);
}