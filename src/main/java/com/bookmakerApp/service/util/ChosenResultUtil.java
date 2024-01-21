package com.bookmakerApp.service.util;

import com.bookmakerApp.model.enums.ChosenResult;
import com.bookmakerApp.model.football.FootballMatchModel;
import com.bookmakerApp.model.mma.MMAFightModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.bookmakerApp.model.enums.ChosenResult.FIRST_FIGHTER_WIN;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChosenResultUtil {

    public static Double getOddsForFootballForChosenResult(FootballMatchModel footballMatch, ChosenResult chosenResult) {
        return switch (chosenResult) {
            case FIRST_TEAM_WIN -> footballMatch.getHomeTeamWinOdds();
            case SECOND_TEAM_WIN -> footballMatch.getVisitingTeamWinOdds();
            case DRAFT -> footballMatch.getDraftOdds();
            default -> throw new IllegalArgumentException("Unknown chosen result " + chosenResult);
        };
    }

    public static Double getOddsForMMAForChosenResult(MMAFightModel mmaFight, ChosenResult chosenResult) {
        return FIRST_FIGHTER_WIN.equals(chosenResult)
                ? mmaFight.getFirstFighterWinOdds() : mmaFight.getSecondFighterWinOdds();
    }
}
