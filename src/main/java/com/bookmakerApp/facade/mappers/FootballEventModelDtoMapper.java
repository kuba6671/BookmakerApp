package com.bookmakerApp.facade.mappers;

import com.bookmakerApp.facade.dtos.FootballEventModelDto;
import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.football.FootballMatchModel;

import java.util.List;
import java.util.stream.Collectors;

public class FootballEventModelDtoMapper {
    private FootballEventModelDtoMapper() {
    }

    public static List<FootballEventModelDto> mapToFootballEventModelDtos(List<EventModel> events, int numberOfPages) {
        return events.stream()
                .map(event -> mapToFootballEventModelDto(event, (FootballMatchModel) event.getSport(), numberOfPages))
                .collect(Collectors.toList());
    }

    private static FootballEventModelDto mapToFootballEventModelDto
            (EventModel event, FootballMatchModel match, int numberOfPages) {
        return FootballEventModelDto.builder()
                .idEvent(event.getIdEvent())
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
                .numberOfPages(numberOfPages)
                .build();
    }
}
