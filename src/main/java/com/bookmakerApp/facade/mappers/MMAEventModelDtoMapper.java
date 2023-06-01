package com.bookmakerApp.facade.mappers;

import com.bookmakerApp.facade.dtos.event.MMAEventModelDto;
import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.mma.MMAFightModel;
import com.bookmakerApp.model.mma.MMAFighterModel;

import java.util.List;
import java.util.stream.Collectors;

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
                .numberOfPages(numberOfPages)
                .build();
    }
}
