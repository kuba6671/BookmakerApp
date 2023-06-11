package com.bookmakerApp.facade.mappers;

import com.bookmakerApp.facade.dtos.event.GroupedMMAEventDto;
import com.bookmakerApp.facade.dtos.event.MMAEventModelDto;
import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.enums.ChosenResult;
import com.bookmakerApp.model.mma.MMAFightModel;
import com.bookmakerApp.model.mma.MMAFighterModel;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
public class MMAEventModelDtoMapper {

    private MMAEventModelDtoMapper() {
    }

    public static List<MMAEventModelDto> mapToMMAEventModelDtos(List<EventModel> mmaEvents, int numberOfPages) {
        return mmaEvents.stream()
                .map(mmaEvent -> mapToMMAEventModelDto(mmaEvent, (MMAFightModel) mmaEvent.getSport(), numberOfPages))
                .collect(Collectors.toList());
    }

    private static MMAEventModelDto mapToMMAEventModelDto(
            EventModel event, MMAFightModel mmaFight, int numberOfPages) {
        MMAFighterModel firstFighter = mmaFight.getFirstFighter();
        MMAFighterModel secondFighter = mmaFight.getSecondFighter();
        return MMAEventModelDto.builder()
                .idEvent(event.getIdEvent())
                .odds(event.getOdds())
                .success(event.getSuccess())
                .finish(event.isFinish())
                .date(event.getDate())
                .firstFighterName(firstFighter.getName() + " " + firstFighter.getSurname())
                .firstFighterCountry(firstFighter.getCountry())
                .firstFighterRecord(firstFighter.getRecord().toString())
                .secondFighterName(secondFighter.getName() + " " + secondFighter.getSurname())
                .secondFighterCountry(secondFighter.getCountry())
                .secondFighterRecord(secondFighter.getRecord().toString())
                .mmaFightResult(mmaFight.getFightResult().toString())
                .chosenResult(event.getChosenResult().toString())
                .numberOfPages(numberOfPages)
                .build();
    }

    public static GroupedMMAEventDto mapToGroupedMMAEventDtos(List<EventModel> events, int numberOfPages) {
        if (events.size() != 2) {
            log.warn("The list of events is incorrectly grouped, list size = [{}], skip", events.size());
            return null;
        }
        GroupedMMAEventDto groupedMMAEvent = GroupedMMAEventDto.builder().build();
        for (EventModel MMAEvent : events) {
            groupedMMAEvent = buildGroupedMMAEvent(MMAEvent, groupedMMAEvent, numberOfPages);
        }
        return groupedMMAEvent;
    }

    private static GroupedMMAEventDto buildGroupedMMAEvent(EventModel MMAEvent, GroupedMMAEventDto groupedMMAEvent, int numberOfPages) {
        if (ChosenResult.FIRST_FIGHTER_WIN.equals(MMAEvent.getChosenResult())) {
            groupedMMAEvent = groupedMMAEvent.toBuilder()
                    .firstFighterWinId(MMAEvent.getIdEvent())
                    .firstFighterName(((MMAFightModel) MMAEvent.getSport()).
                            getFirstFighter().getName()
                            + " " + ((MMAFightModel) MMAEvent.getSport()).getFirstFighter().getSurname())
                    .secondFighterName(((MMAFightModel) MMAEvent.getSport()).
                            getSecondFighter().getName()
                            + " " + ((MMAFightModel) MMAEvent.getSport()).getSecondFighter().getSurname())
                    .date(MMAEvent.getDate())
                    .firstFighterWinOdds(MMAEvent.getOdds())
                    .mmaFightResult(((MMAFightModel) MMAEvent.getSport()).getFightResult().toString())
                    .numberOfPages(numberOfPages)
                    .build();
        } else if (ChosenResult.SECOND_FIGHTER_WIN.equals(MMAEvent.getChosenResult())) {
            groupedMMAEvent = groupedMMAEvent.toBuilder()
                    .secondFighterWinId(MMAEvent.getIdEvent())
                    .secondFighterWinOdds(MMAEvent.getOdds())
                    .build();
        } else {
            log.warn("The chosen result in event is wrong");
        }
        return groupedMMAEvent;
    }
}
