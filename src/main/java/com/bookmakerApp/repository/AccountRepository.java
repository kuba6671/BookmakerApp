package com.bookmakerApp.repository;

import com.bookmakerApp.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountModel, Long> {
}
