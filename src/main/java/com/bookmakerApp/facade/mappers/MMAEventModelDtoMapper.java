package com.bookmakerApp.facade.mappers;

import com.bookmakerApp.facade.dtos.event.GroupedMMAEventDto;
import com.bookmakerApp.facade.dtos.event.MMAEventModelDto;
import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.mma.MMAFightModel;
import com.bookmakerApp.model.mma.MMAFighterModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MMAEventModelDtoMapper {

    public static List<MMAEventModelDto> mapToMMAEventModelDtos(List<EventModel> mmaEvents, int numberOfPages) {
        return mmaEvents.stream()
                .map(mmaEvent -> mapToMMAEventModelDto(mmaEvent, (MMAFightModel) mmaEvent.getSport(), numberOfPages))
                .toList();
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

    private static GroupedMMAEventDto buildGroupedMMAEvent(EventModel mmaEvent, GroupedMMAEventDto groupedMMAEvent, int numberOfPages) {
        switch (mmaEvent.getChosenResult()) {
            case FIRST_FIGHTER_WIN -> groupedMMAEvent = groupedMMAEvent.toBuilder()
                    .firstFighterWinId(mmaEvent.getIdEvent())
                    .firstFighterName(((MMAFightModel) mmaEvent.getSport()).
                            getFirstFighter().getName()
                            + " " + ((MMAFightModel) mmaEvent.getSport()).getFirstFighter().getSurname())
                    .secondFighterName(((MMAFightModel) mmaEvent.getSport()).
                            getSecondFighter().getName()
                            + " " + ((MMAFightModel) mmaEvent.getSport()).getSecondFighter().getSurname())
                    .date(mmaEvent.getDate())
                    .firstFighterWinOdds(mmaEvent.getOdds())
                    .mmaFightResult(((MMAFightModel) mmaEvent.getSport()).getFightResult().toString())
                    .numberOfPages(numberOfPages)
                    .build();
            case SECOND_FIGHTER_WIN -> groupedMMAEvent = groupedMMAEvent.toBuilder()
                    .secondFighterWinId(mmaEvent.getIdEvent())
                    .secondFighterWinOdds(mmaEvent.getOdds())
                    .build();
            default -> log.warn("The chosen result in event is wrong");
        }
        return groupedMMAEvent;
    }
}
