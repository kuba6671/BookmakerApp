package com.bookmakerApp.repository;

import com.bookmakerApp.model.SportModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportRepository extends JpaRepository<SportModel, Long> {
}
