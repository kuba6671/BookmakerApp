package com.bookmakerApp.facade.dtos.event;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class MMAEventModelDto extends DefaultEventModelDto {
    private String firstFighterName;
    private String firstFighterSurname;
    private String firstFighterCountry;
    private String firstFighterRecord;
    private String secondFighterName;
    private String secondFighterSurname;
    private String secondFighterCountry;
    private String secondFighterRecord;
    private String mmaFightResult;
}