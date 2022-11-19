package com.bookmakerApp.repository;

import com.bookmakerApp.model.football.FootballTeamModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FootballTeamRepository extends JpaRepository <FootballTeamModel, Long> {
}
