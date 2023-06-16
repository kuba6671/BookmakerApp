package com.bookmakerApp.facade.dtos.event;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder(toBuilder = true)
public class GroupedFootballEventsDto {
    private long firstTeamWinEventId;
    private long secondTeamWinEventId;
    private long draftEventId;
    private String firstTeamName;
    private String secondTeamName;
    @Builder.Default
    private String sportName = "Piłka nożna";
    private Date date;
    private double firstTeamWinOdds;
    private double secondTeamWinOdds;
    private double draftOdds;
    private int homeTeamGoals;
    private int visitingTeamGoals;
    private int numberOfPages;
}
