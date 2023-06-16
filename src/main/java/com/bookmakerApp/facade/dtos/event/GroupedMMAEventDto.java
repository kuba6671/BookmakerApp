package com.bookmakerApp.facade.dtos.event;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder(toBuilder = true)
public class GroupedMMAEventDto {
    private long firstFighterWinId;
    private long secondFighterWinId;
    private String firstFighterName;
    private String secondFighterName;
    private Date date;
    @Builder.Default
    private String sportName = "MMA";
    private double firstFighterWinOdds;
    private double secondFighterWinOdds;
    private String mmaFightResult;
    private int numberOfPages;
}
