package com.bookmakerApp.facade.mappers;

import com.bookmakerApp.facade.dtos.DefaultEventModelDto;
import com.bookmakerApp.model.EventModel;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultEventModelDtoMapper {
    private DefaultEventModelDtoMapper(){}

    public static List<DefaultEventModelDto> mapToDefaultEventModelDtos(List<EventModel> events){
        return events.stream()
                .map(event -> mapToDefaultEventModelDto(event))
                .collect(Collectors.toList());
    }

    private static DefaultEventModelDto mapToDefaultEventModelDto(EventModel event){
        return DefaultEventModelDto.builder()
                .odds(event.getOdds())
                .success(event.getSuccess())
                .finish(event.isFinish())
                .date(event.getDate())
                .type(event.getClass().toString())
                .build();
    }
}
