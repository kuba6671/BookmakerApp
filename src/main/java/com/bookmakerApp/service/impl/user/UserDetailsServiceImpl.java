package com.bookmakerApp.service.impl.user;

import com.bookmakerApp.model.UserModel;
import com.bookmakerApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User springUser;
        UserModel user = userRepository.findUserModelByMail(username);

        if (ObjectUtils.isNotEmpty(user)) {
            springUser = new User(user.getMail(), user.getPassword(), user.getAuthorities());
        } else {
            throw new UsernameNotFoundException("User not exist");
        }
        return springUser;
    }

    public Long getUserIdByUsername(String username) {
        return userRepository.findUserModelByMail(username).getIdUser();
    }
}
