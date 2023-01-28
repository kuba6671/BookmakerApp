package com.bookmakerApp.service.impl;

import com.bookmakerApp.model.football.FootballMatchModel;
import com.bookmakerApp.model.football.FootballTeamModel;
import com.bookmakerApp.repository.FootballMatchRepository;
import com.bookmakerApp.repository.FootballTeamRepository;
import com.bookmakerApp.service.interfaces.FootballMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FootballMatchServiceImpl implements FootballMatchService {

    private final FootballMatchRepository footballMatchRepository;

    private final FootballTeamRepository footballTeamRepository;

    @Override
    public FootballMatchModel addFootballMatch(FootballMatchModel footballMatch) {
        footballMatch.setSportName("Piłka nożna");
        return footballMatchRepository.save(footballMatch);
    }

    @Override
    public FootballTeamModel addFootballTeam(FootballTeamModel footballTeam) {
        return footballTeamRepository.save(footballTeam);
    }

}
