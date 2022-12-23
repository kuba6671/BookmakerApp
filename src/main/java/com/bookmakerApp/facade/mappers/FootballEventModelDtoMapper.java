package com.bookmakerApp.facade.mappers;

import com.bookmakerApp.facade.dtos.FootballEventModelDto;
import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.football.FootballMatchModel;
import com.bookmakerApp.model.football.FootballTeamModel;

import java.util.List;
import java.util.stream.Collectors;

public class FootballEventModelDtoMapper {
    private FootballEventModelDtoMapper(){}

    public static List<FootballEventModelDto> mapToFootballEventModelDtos(List<EventModel> events){
        return events.stream()
                .map(event -> mapToFootballEventModelDto(event, (FootballMatchModel) event.getSport()))
                .collect(Collectors.toList());
    }

    private static FootballEventModelDto mapToFootballEventModelDto
            (EventModel event, FootballMatchModel match){
        return FootballEventModelDto.builder()
                .odds(event.getOdds())
                .success(event.getSuccess())
                .finish(event.isFinish())
                .date(event.getDate())
                .footballMatchType(String.valueOf(match.getFootballMatchType()))
                .homeTeamName(match.getHomeTeam().getName())
                .homeTeamCountry(match.getHomeTeam().getCountry())
                .homeTeamGoals(match.getHomeTeamGoals())
                .visitingTeamName(match.getVisitingTeam().getName())
                .visitingTeamCountry(match.getVisitingTeam().getCountry())
                .visitingTeamGoals(match.getVisitingTeamGoals())
                .chosenResult(event.getChosenResult().toString())
                .build();
    }
}
