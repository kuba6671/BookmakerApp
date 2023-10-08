package com.bookmakerApp.service.impl.user;

import com.bookmakerApp.config.security.CustomPasswordEncoder;
import com.bookmakerApp.model.UserModel;
import com.bookmakerApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CustomPasswordEncoder customPasswordEncoder;

    public UserModel getUserById(Long idUser) {
        return userRepository.findUserModelByIdUser(idUser);
    }

    public UserModel getUserByEmail(final String email) {
        return userRepository.findUserModelByMail(email);
    }

    public boolean checkPasswordIsValid(UserModel user, String password) {
        return customPasswordEncoder.getPasswordEncoder().matches(password, user.getPassword());
    }

    @Transactional
    public UserModel changePassword(final UserModel user, final String oldPassword, final String newPassword) {
        if (checkPasswordIsValid(user, oldPassword)) {
            user.setPassword(customPasswordEncoder.getPasswordEncoder().encode(newPassword));
            return user;
        } else {
            throw new IllegalArgumentException("Password is wrong");
        }
    }

    @Transactional
    public UserModel changeUserPersonalData(final UserModel user) {
        final UserModel changedUser = getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        changedUser.setName(StringUtils.defaultIfEmpty(user.getName(), changedUser.getName()));
        changedUser.setSurname(StringUtils.defaultIfEmpty(user.getSurname(), changedUser.getSurname()));
        if (user.getAge() > 18) {
            changedUser.setAge(user.getAge());
        }
        return userRepository.save(changedUser);
    }
}
