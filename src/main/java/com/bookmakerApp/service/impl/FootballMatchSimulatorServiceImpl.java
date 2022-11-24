package com.bookmakerApp.service.impl;

import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.SportModel;
import com.bookmakerApp.model.football.FootballMatchModel;
import com.bookmakerApp.repository.EventRepository;
import com.bookmakerApp.service.interfaces.SimulatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class FootballMatchSimulatorServiceImpl implements SimulatorService {

    private final EventRepository eventRepository;

    @Override
    public void simulate(EventModel event) {
        SportModel footballMatch = event.getSport();
        if (footballMatch instanceof FootballMatchModel && !event.isResultIsChecked()) {
            simulateFootballMatchGoals(event);
            setSimulateResult(footballMatch.getIdSport());
        } else {
            throw new IllegalArgumentException("SportModel is not FootballMatchModel");
        }
    }

    private void setSimulateResult(Long idSport) {
        List<EventModel> events = eventRepository.getEventModelsBySport_IdSport(idSport);
        events.stream()
                .filter(event -> event.getSport() instanceof FootballMatchModel
                        && !event.isResultIsChecked());
        for (EventModel event : events) {
            FootballMatchModel footballMatch = (FootballMatchModel) event.getSport();
            String choosenResult = String.valueOf(event.getChosenResult());
            if (choosenResult.equals("DRAFT") && checkDraft(footballMatch)) {
                event.setSuccess(true);
                event.setFinish(true);
                event.setResultIsChecked(true);
                return;
            } else if (choosenResult.equals("FIRST_TEAM_WIN") && checkTeamsGoals(footballMatch)) {
                event.setSuccess(true);
                event.setFinish(true);
                event.setResultIsChecked(true);
            } else if (choosenResult.equals("SECOND_TEAM_WIN") && !checkTeamsGoals(footballMatch)) {
                event.setSuccess(true);
                event.setFinish(true);
                event.setResultIsChecked(true);
            } else {
                event.setSuccess(false);
                event.setFinish(true);
                event.setResultIsChecked(true);
            }
        }
    }

    private boolean checkTeamsGoals(FootballMatchModel match) {
        int homeTeamGoals = match.getHomeTeamGoals();
        int visitingTeamGoals = match.getVisitingTeamGoals();
        if (homeTeamGoals > visitingTeamGoals) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkDraft(FootballMatchModel match) {
        if (match.getHomeTeamGoals() == match.getVisitingTeamGoals()) {
            return true;
        } else {
            return false;
        }
    }

    private void simulateFootballMatchGoals(EventModel event) {
        SportModel footballMatch = event.getSport();
        if (footballMatch instanceof FootballMatchModel) {
            int probability;
            String choosenResult = String.valueOf(event.getChosenResult());
            if (choosenResult.equals("FIRST_TEAM_WIN")) {
                probability = (int) (((FootballMatchModel) footballMatch).getHomeTeamWinOdds() * 100);
            } else if (choosenResult.equals("SECOND_TEAM_WIN")) {
                probability = (int) (((FootballMatchModel) footballMatch).getVisitingTeamWinOdds() * 100);
            } else {
                probability = (int) (((FootballMatchModel) footballMatch).getDraftOdds() * 100);
            }
            simulateGoalsForTeams((FootballMatchModel) footballMatch, probability);
        }
    }


    private void simulateGoalsForTeams(FootballMatchModel match, int probability) {
        Random random = new Random();
        int minusGoals;
        int numberOfGoals = random.nextInt(6);
        boolean successProbability = random.nextInt(1, 102) >= probability;
        if (successProbability) {
            match.setHomeTeamGoals(numberOfGoals);
            if (numberOfGoals > 1) {
                minusGoals = random.nextInt(numberOfGoals + 1);
                match.setVisitingTeamGoals(numberOfGoals - minusGoals);
            } else {
                match.setVisitingTeamGoals(0);
            }
        } else {
            match.setVisitingTeamGoals(numberOfGoals);
            if (numberOfGoals > 1) {
                minusGoals = random.nextInt(numberOfGoals + 1);
                match.setHomeTeamGoals(numberOfGoals - minusGoals);
            } else {
                match.setHomeTeamGoals(0);
            }
        }
    }
}
