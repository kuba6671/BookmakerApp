package com.bookmakerApp.service.interfaces;

import com.bookmakerApp.model.UserModel;

public interface UserService {
    UserModel getUserById(Long idUser);
    UserModel getUserByEmail(String email);
    boolean checkPasswordIsValid(UserModel user, String password);
    UserModel changePassword(UserModel user,String oldPassword, String newPassword);
}
