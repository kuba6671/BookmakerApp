package com.bookmakerApp.service.impl;

import com.bookmakerApp.config.security.CustomPasswordEncoder;
import com.bookmakerApp.model.UserModel;
import com.bookmakerApp.repository.UserRepository;
import com.bookmakerApp.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultUserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final CustomPasswordEncoder customPasswordEncoder;

    @Override
    public UserModel getUserById(Long idUser) {
        return userRepository.findUserModelByIdUser(idUser);
    }

    @Override
    public UserModel getUserByEmail(final String email) {
        return userRepository.findUserModelByMail(email);
    }

    @Override
    public boolean checkPasswordIsValid(UserModel user, String password) {
        return customPasswordEncoder.getPasswordEncoder().matches(password, user.getPassword());
    }

    @Override
    @Transactional
    public UserModel changePassword(final UserModel user, final String oldPassword, final String newPassword) {
        if (checkPasswordIsValid(user, oldPassword)) {
            user.setPassword(customPasswordEncoder.getPasswordEncoder().encode(newPassword));
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Password is wrong");
        }
    }


}
