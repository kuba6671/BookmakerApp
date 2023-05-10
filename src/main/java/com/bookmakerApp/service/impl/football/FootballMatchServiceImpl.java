package com.bookmakerApp.service.impl.football;

import com.bookmakerApp.model.football.FootballMatchModel;
import com.bookmakerApp.model.football.FootballTeamModel;
import com.bookmakerApp.repository.FootballMatchRepository;
import com.bookmakerApp.repository.FootballTeamRepository;
import com.bookmakerApp.service.interfaces.football.FootballMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FootballMatchServiceImpl implements FootballMatchService {

    private final FootballMatchRepository footballMatchRepository;
    private final FootballTeamRepository footballTeamRepository;

    private static final String FOOTBALL_MATCH = "Football";

    public FootballMatchModel addFootballMatch(FootballMatchModel footballMatch) {
        footballMatch.setSportName(FOOTBALL_MATCH);
        return footballMatchRepository.save(footballMatch);
    }

    @Override
    public FootballTeamModel addFootballTeam(FootballTeamModel footballTeam) {
        return footballTeamRepository.save(footballTeam);
    }
}
