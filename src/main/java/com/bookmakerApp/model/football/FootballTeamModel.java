package com.bookmakerApp.model.football;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class FootballTeamModel {
    @Id
    private Long idFootballTeam;
    private String name;
    private String Country;

}
