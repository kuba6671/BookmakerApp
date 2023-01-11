package com.bookmakerApp.service.impl;

import com.bookmakerApp.model.UserModel;
import com.bookmakerApp.repository.UserRepository;
import com.bookmakerApp.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultUserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserModel getUserById(Long idUser) {
            return userRepository.findUserModelByIdUser(idUser);
    }
}
