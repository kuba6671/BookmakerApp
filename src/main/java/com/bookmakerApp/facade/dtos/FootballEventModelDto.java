package com.bookmakerApp.facade.dtos;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class FootballEventModelDto extends DefaultEventModelDto {
    private String footballMatchType;
    private String homeTeamName;
    private String homeTeamCountry;
    private int homeTeamGoals;
    private String visitingTeamName;
    private String visitingTeamCountry;
    private int visitingTeamGoals;
    private int numberOfPages;
}
