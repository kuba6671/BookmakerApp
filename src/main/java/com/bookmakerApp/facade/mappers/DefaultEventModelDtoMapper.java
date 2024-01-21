package com.bookmakerApp.facade.mappers;

import com.bookmakerApp.facade.dtos.event.DefaultEventModelDto;
import com.bookmakerApp.model.EventModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DefaultEventModelDtoMapper {

    public static List<DefaultEventModelDto> mapToDefaultEventModelDtos(List<EventModel> events) {
        return events.stream()
                .map(DefaultEventModelDtoMapper::mapToDefaultEventModelDto)
                .toList();
    }

    private static DefaultEventModelDto mapToDefaultEventModelDto(EventModel event) {
        return DefaultEventModelDto.builder()
                .idEvent(event.getIdEvent())
                .odds(event.getOdds())
                .success(event.getSuccess())
                .finish(event.isFinish())
                .date(event.getDate())
                .type(event.getClass().toString())
                .chosenResult(event.getChosenResult().toString())
                .build();
    }
}
