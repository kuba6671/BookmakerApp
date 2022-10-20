package com.bookmakerApp.model.football;

import com.bookmakerApp.model.SportModel;
import com.bookmakerApp.model.football.enums.FootballMatchType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class FootballMatchModel extends SportModel {
    private FootballMatchType footballMatchType;
    @OneToOne
    @JoinColumn(name = "id")
    private FootballTeamModel homeTeam;
    private int homeTeamGoals;
    @OneToOne
    @JoinColumn(name = "id")
    private FootballTeamModel visitingTeam;
    private int visitingTeamGoals;
}
