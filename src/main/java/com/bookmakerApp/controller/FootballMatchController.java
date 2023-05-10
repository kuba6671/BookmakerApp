package com.bookmakerApp.controller;


import com.bookmakerApp.model.football.FootballMatchModel;
import com.bookmakerApp.model.football.FootballTeamModel;
import com.bookmakerApp.service.impl.football.FootballMatchServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class FootballMatchController {

    private final FootballMatchServiceImpl footballMatchService;

    @PostMapping("/footballMatch")
    public FootballMatchModel addFootballMatch(@RequestBody FootballMatchModel footballMatch) {
        return footballMatchService.addFootballMatch(footballMatch);
    }

    @PostMapping("/footballTeam")
    public FootballTeamModel addFootballTeam(@RequestBody FootballTeamModel footballTeam) {
        return footballMatchService.addFootballTeam(footballTeam);
    }
}
