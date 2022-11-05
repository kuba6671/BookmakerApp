package com.bookmakerApp.model.football;

import com.bookmakerApp.model.SportModel;
import com.bookmakerApp.model.football.enums.ChosenResult;
import com.bookmakerApp.model.football.enums.FootballMatchType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Getter
@Setter
@Entity
public class FootballMatchModel extends SportModel {
    @DecimalMin("1.01")
    @DecimalMax("10.00")
    private Double homeTeamWinOdds;
    @DecimalMin("1.01")
    @DecimalMax("10.00")
    private Double visitingTeamWinOdds;
    @DecimalMin("1.01")
    @DecimalMax("10.00")
    private Double draftOdds;
    @Enumerated(EnumType.STRING)
    private ChosenResult chosenResult;
    @Enumerated(EnumType.STRING)
    private FootballMatchType footballMatchType;
    @OneToOne
    private FootballTeamModel homeTeam;
    private int homeTeamGoals;
    @OneToOne
    private FootballTeamModel visitingTeam;
    private int visitingTeamGoals;
}
