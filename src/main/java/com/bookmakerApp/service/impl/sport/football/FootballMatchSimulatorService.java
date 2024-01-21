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
public class FootballMatchSimulatorService implements SimulatorService {

    private final EventRepository eventRepository;
    private final Random random;

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
        events.stream()
                .filter(this::isFootballMatchWithNoCheckedResult)
                .forEach(this::setResult);
    }

    private void setResult(EventModel event) {
        FootballMatchModel footballMatch = (FootballMatchModel) event.getSport();
        String chosenResult = String.valueOf(event.getChosenResult());
        if (DRAFT.equals(chosenResult)) {
            event.setSuccess(checkDraft(footballMatch));
        } else if (FIRST_TEAM_WIN.equals(chosenResult)) {
            event.setSuccess(checkHomeTeamWin(footballMatch));
        } else {
            event.setSuccess(SECOND_TEAM_WIN.equals(chosenResult) && !checkHomeTeamWin(footballMatch));
        }
        event.setFinish(true);
        event.setResultIsChecked(true);
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
        SportModel sportModel = event.getSport();
        if (sportModel instanceof FootballMatchModel footballMatch) {
            String chosenResult = String.valueOf(event.getChosenResult());
            int probability = calculateProbabilityForChosenResult(footballMatch, chosenResult);
            simulateGoalsForTeams(footballMatch, probability);
        }
    }

    private int calculateProbabilityForChosenResult(FootballMatchModel footballMatch, String chosenResult) {
        switch (chosenResult) {
            case FIRST_TEAM_WIN -> {
                return (int) (footballMatch.getHomeTeamWinOdds() * 10);
            }
            case SECOND_TEAM_WIN -> {
                return (int) (footballMatch.getVisitingTeamWinOdds() * 10);
            }
            default -> {
                return (int) (footballMatch.getDraftOdds() * 10);
            }
        }
    }


    private void simulateGoalsForTeams(FootballMatchModel match, int probability) {
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
        SportModel sportModel = event.getSport();
        return sportModel instanceof FootballMatchModel && !event.isResultIsChecked();
    }
}
