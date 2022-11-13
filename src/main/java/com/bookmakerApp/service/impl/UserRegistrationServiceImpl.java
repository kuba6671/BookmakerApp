package com.bookmakerApp.service.impl;

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

    @Override
    @Transactional
    public UserModel registerUserAccount(UserModel user) {
        //TODO : Need to add password encryption
        UserModel existingUser = userRepository.findUserModelByMail(user.getMail());
        if (ObjectUtils.isEmpty(existingUser)) {
            //UserAlreadyExistAuthenticationException
            throw new IllegalArgumentException("User already exist");
        }
        AccountModel account = new AccountModel();
        account.setBankBalance(BigDecimal.ZERO);
        account.setUser(user);
        accountRepository.save(account);
        user.setAccount(account);
        return userRepository.save(user);
    }
}