package com.bookmakerApp.service.impl.sport.football;

import com.bookmakerApp.model.enums.SportName;
import com.bookmakerApp.model.football.FootballMatchModel;
import com.bookmakerApp.model.football.FootballTeamModel;
import com.bookmakerApp.repository.FootballMatchRepository;
import com.bookmakerApp.repository.FootballTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FootballMatchService {

    private final FootballMatchRepository footballMatchRepository;
    private final FootballTeamRepository footballTeamRepository;

    public FootballMatchModel addFootballMatch(FootballMatchModel footballMatch) {
        footballMatch.setSportName(SportName.Football);
        return footballMatchRepository.save(footballMatch);
    }

    public FootballTeamModel addFootballTeam(FootballTeamModel footballTeam) {
        return footballTeamRepository.save(footballTeam);
    }
}
