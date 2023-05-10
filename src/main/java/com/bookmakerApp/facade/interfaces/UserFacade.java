package com.bookmakerApp.facade.interfaces;

import com.bookmakerApp.facade.dtos.user.UserModelDto;
import com.bookmakerApp.model.UserModel;

public interface UserFacade {
    UserModelDto getUserById(Long idUser);

    UserModelDto changePassword(String oldPassword, String newPassword);

    UserModel changeUserData(UserModel user);

    UserModel addUser(UserModel newUser);

    String getUserIdByUsername(String username);
}
