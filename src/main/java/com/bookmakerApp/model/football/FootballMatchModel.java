package com.bookmakerApp.model.football;

import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.SportModel;
import com.bookmakerApp.model.football.enums.FootballMatchType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class FootballMatchModel extends SportModel {
    @Enumerated(EnumType.STRING)
    private FootballMatchType footballMatchType;
    @OneToOne
    private FootballTeamModel homeTeam;
    private int homeTeamGoals;
    @OneToOne
    private FootballTeamModel visitingTeam;
    private int visitingTeamGoals;
}
