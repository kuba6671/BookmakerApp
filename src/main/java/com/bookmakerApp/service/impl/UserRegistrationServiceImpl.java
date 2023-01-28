package com.bookmakerApp.service.impl;

import com.bookmakerApp.config.security.CustomPasswordEncoder;
import com.bookmakerApp.model.AccountModel;
import com.bookmakerApp.model.UserModel;
import com.bookmakerApp.repository.AccountRepository;
import com.bookmakerApp.repository.UserRepository;
import com.bookmakerApp.service.interfaces.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    private final CustomPasswordEncoder customPasswordEncoder;

    @Override
    @Transactional
    public UserModel registerUserAccount(UserModel user) {
        UserModel existingUser = userRepository.findUserModelByMail(user.getMail());
        if (ObjectUtils.isNotEmpty(existingUser)) {
            throw new IllegalArgumentException("User already exist");
        }
        AccountModel account = new AccountModel();
        account.setBankBalance(BigDecimal.ZERO);
        account.setUser(user);
        accountRepository.save(account);
        user.setAccount(account);
        String password = user.getPassword();
        user.setPassword(customPasswordEncoder.getPasswordEncoder().encode(password));
        return userRepository.save(user);
    }
}
