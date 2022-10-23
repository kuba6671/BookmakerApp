package com.bookmakerApp.facade.dtos;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class FootballEventModelDto {
    private Double odds;
    private Boolean success;
    private boolean finish;
    private Date date;
    private String footballMatchType;
    private String homeTeamName;
    private String homeTeamCountry;
    private int homeTeamGoals;
    private String visitingTeamName;
    private String visitingTeamCountry;
    private int visitingTeamGoals;
}
