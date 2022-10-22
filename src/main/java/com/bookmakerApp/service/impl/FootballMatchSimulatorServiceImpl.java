package com.bookmakerApp.service.impl;

import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.SportModel;
import com.bookmakerApp.model.football.FootballMatchModel;
import com.bookmakerApp.service.interfaces.SimulatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class FootballMatchSimulatorServiceImpl implements SimulatorService {

    private Random random = new Random();

    @Override
    public void simulateFootballMatchGoals(EventModel event) {
        SportModel sport = event.getSport();
        int probability = (int) (event.getOdds()*100);

        if(sport instanceof FootballMatchModel){
            simulateGoalsForTeams((FootballMatchModel) sport, probability);
        }
    }


    private void simulateGoalsForTeams(FootballMatchModel match, int probability){
        int minusGoals;
        int numberOfGoals = random.nextInt(6);
        boolean successProbability = random.nextInt(1, 102) >= probability;
        if(successProbability){
            match.setHomeTeamGoals(numberOfGoals);
            if(numberOfGoals > 1){
                minusGoals = random.nextInt(numberOfGoals+1);
                match.setVisitingTeamGoals(numberOfGoals - minusGoals);
            }
            else{
                match.setVisitingTeamGoals(0);
            }
        }else{
            match.setVisitingTeamGoals(numberOfGoals);
            if(numberOfGoals > 1){
                minusGoals = random.nextInt(numberOfGoals+1);
                match.setHomeTeamGoals(numberOfGoals - minusGoals);
            }
            else{
                match.setHomeTeamGoals(0);
            }
        }
    }
}
