package com.bookmakerApp.model.football;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class FootballTeamModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idFootballTeam;
    private String name;
    private String Country;

}
