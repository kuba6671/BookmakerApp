package com.bookmakerApp.service.impl.sport.football;

import com.bookmakerApp.model.enums.SportName;
import com.bookmakerApp.model.football.FootballMatchModel;
import com.bookmakerApp.model.football.FootballTeamModel;
import com.bookmakerApp.repository.FootballMatchRepository;
import com.bookmakerApp.repository.FootballTeamRepository;
import com.bookmakerApp.service.interfaces.sport.football.FootballMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FootballMatchServiceImpl implements FootballMatchService {

    private final FootballMatchRepository footballMatchRepository;
    private final FootballTeamRepository footballTeamRepository;


    @Override
    public FootballMatchModel addFootballMatch(FootballMatchModel footballMatch) {
        footballMatch.setSportName(SportName.Football);
        return footballMatchRepository.save(footballMatch);
    }

    @Override
    public FootballTeamModel addFootballTeam(FootballTeamModel footballTeam) {
        return footballTeamRepository.save(footballTeam);
    }
}