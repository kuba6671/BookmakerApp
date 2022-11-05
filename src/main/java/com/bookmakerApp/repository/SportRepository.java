package com.bookmakerApp.repository;

import com.bookmakerApp.model.SportModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SportRepository extends JpaRepository<SportModel, Long> {
    public List<SportModel> getSportModelsBySportName(String sportName);
}
