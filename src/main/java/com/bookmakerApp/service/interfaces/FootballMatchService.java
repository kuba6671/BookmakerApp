package com.bookmakerApp.service.interfaces;

import com.bookmakerApp.model.football.FootballMatchModel;
import com.bookmakerApp.model.football.FootballTeamModel;

public interface FootballMatchService {
    FootballMatchModel addFootballMatch(FootballMatchModel footballMatch);

    FootballTeamModel addFootballTeam(FootballTeamModel footballTeam);
}
