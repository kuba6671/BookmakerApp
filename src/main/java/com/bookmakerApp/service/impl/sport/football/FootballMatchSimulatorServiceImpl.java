package com.bookmakerApp.service.impl.sport.football;

import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.SportModel;
import com.bookmakerApp.model.football.FootballMatchModel;
import com.bookmakerApp.repository.EventRepository;
import com.bookmakerApp.service.interfaces.sport.SimulatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class FootballMatchSimulatorServiceImpl implements SimulatorService {

    private final EventRepository eventRepository;

    private static final String FIRST_TEAM_WIN = "FIRST_TEAM_WIN";
    private static final String SECOND_TEAM_WIN = "SECOND_TEAM_WIN";
    private static final String DRAFT = "DRAFT";

    @Override
    public void simulate(EventModel event) {
        if (isFootballMatchWithNoCheckedResult(event)) {
            simulateFootballMatchGoals(event);
            setSimulateResult(event.getSport().getIdSport());
        } else {
            throw new IllegalArgumentException("SportModel is not FootballMatchModel");
        }
    }

    private void setSimulateResult(Long idSport) {
        List<EventModel> events = eventRepository.getEventModelsBySport_IdSportAndResultIsChecked(idSport, false);
        events = events.stream()
                .filter(this::isFootballMatchWithNoCheckedResult)
                .toList();

        events.forEach(event -> {
            FootballMatchModel footballMatch = (FootballMatchModel) event.getSport();
            String chosenResult = String.valueOf(event.getChosenResult());
            if (DRAFT.equals(chosenResult) && checkDraft(footballMatch)) {
                event.setSuccess(true);
            } else if (FIRST_TEAM_WIN.equals(chosenResult) && checkHomeTeamWin(footballMatch)) {
                event.setSuccess(true);
            } else {
                event.setSuccess(SECOND_TEAM_WIN.equals(chosenResult) && !checkHomeTeamWin(footballMatch));
            }
            event.setFinish(true);
            event.setResultIsChecked(true);
        });
    }

    private boolean checkHomeTeamWin(FootballMatchModel match) {
        int homeTeamGoals = match.getHomeTeamGoals();
        int visitingTeamGoals = match.getVisitingTeamGoals();
        return homeTeamGoals > visitingTeamGoals;
    }

    private boolean checkDraft(FootballMatchModel match) {
        return match.getHomeTeamGoals() == match.getVisitingTeamGoals();
    }

    private void simulateFootballMatchGoals(EventModel event) {
        SportModel footballMatch = event.getSport();
        if (footballMatch instanceof FootballMatchModel) {
            String chosenResult = String.valueOf(event.getChosenResult());
            int probability = calculateProbabilityForChosenResult((FootballMatchModel) footballMatch, chosenResult);
            simulateGoalsForTeams((FootballMatchModel) footballMatch, probability);
        }
    }

    private int calculateProbabilityForChosenResult(FootballMatchModel footballMatch, String chosenResult) {
        if (FIRST_TEAM_WIN.equals(chosenResult)) {
            return (int) (footballMatch.getHomeTeamWinOdds() * 10);
        } else if (SECOND_TEAM_WIN.equals(chosenResult)) {
            return (int) (footballMatch.getVisitingTeamWinOdds() * 10);
        } else {
            return (int) (footballMatch.getDraftOdds() * 10);
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

    private boolean isFootballMatchWithNoCheckedResult(EventModel event) {
        SportModel footballMatch = event.getSport();
        return footballMatch instanceof FootballMatchModel && !event.isResultIsChecked();
    }
}
