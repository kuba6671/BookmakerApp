package com.bookmakerApp.repository;

import com.bookmakerApp.model.football.FootballMatchModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FootballMatchRepository extends JpaRepository<FootballMatchModel, Long> {
}
