package com.bookmakerApp.repository;

import com.bookmakerApp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findUserModelByMail(String mail);

    UserModel findUserModelByIdUser(Long idUser);
}
