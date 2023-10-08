package com.bookmakerApp.facade.mappers;

import com.bookmakerApp.facade.dtos.event.FootballEventModelDto;
import com.bookmakerApp.facade.dtos.event.GroupedFootballEventsDto;
import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.enums.SportName;
import com.bookmakerApp.model.football.FootballMatchModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FootballEventModelDtoMapper {
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


    public static GroupedFootballEventsDto mapToGroupedFootballEventDtos(List<EventModel> events, int numberOfPages) {
        if (events.size() != 3) {
            log.warn("The list of events is incorrectly grouped, list size = [{}], skip", events.size());
            return null;
        }
        GroupedFootballEventsDto groupedFootballEvent = GroupedFootballEventsDto.builder().build();
        for (EventModel footballEvent : events) {
            groupedFootballEvent = buildGroupedFootballEvent(footballEvent, groupedFootballEvent, numberOfPages);
        }
        return groupedFootballEvent;
    }

    private static GroupedFootballEventsDto buildGroupedFootballEvent(EventModel footballEvent,
                                                                      GroupedFootballEventsDto groupedFootballEvent,
                                                                      int numberOfPages) {
        switch (footballEvent.getChosenResult()) {
            case FIRST_TEAM_WIN -> groupedFootballEvent = groupedFootballEvent.toBuilder()
                    .firstTeamWinEventId(footballEvent.getIdEvent())
                    .firstTeamName(((FootballMatchModel) footballEvent.getSport()).getHomeTeam().getName())
                    .secondTeamName(((FootballMatchModel) footballEvent.getSport()).getVisitingTeam().getName())
                    .date(footballEvent.getDate())
                    .firstTeamWinOdds(footballEvent.getOdds())
                    .sportName(SportName.Football.toString())
                    .homeTeamGoals(((FootballMatchModel) footballEvent.getSport()).getHomeTeamGoals())
                    .visitingTeamGoals(((FootballMatchModel) footballEvent.getSport()).getVisitingTeamGoals())
                    .numberOfPages(numberOfPages)
                    .build();
            case SECOND_TEAM_WIN -> groupedFootballEvent = groupedFootballEvent.toBuilder()
                    .secondTeamWinEventId(footballEvent.getIdEvent())
                    .secondTeamWinOdds(footballEvent.getOdds())
                    .build();
            case DRAFT -> groupedFootballEvent = groupedFootballEvent.toBuilder()
                    .draftEventId(footballEvent.getIdEvent())
                    .draftOdds(footballEvent.getOdds())
                    .build();
            default -> log.warn("The chosen result in event is wrong");
        }
        return groupedFootballEvent;
    }
}
