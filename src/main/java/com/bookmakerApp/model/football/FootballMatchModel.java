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
    @Enumerated(EnumType.ORDINAL)
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
