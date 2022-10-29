package com.bookmakerApp.facade.impl;

import com.bookmakerApp.facade.dtos.FootballEventModelDto;
import com.bookmakerApp.facade.interfaces.EventFacade;
import com.bookmakerApp.facade.mappers.FootballEventModelDtoMapper;
import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.football.FootballMatchModel;
import com.bookmakerApp.service.impl.DefaultEventServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class FootballEventFacadeImpl implements EventFacade {

    private final DefaultEventServiceImpl defaultEventService;

    private FootballEventModelDtoMapper footballEventModelDtoMapper;

    @Override
    public List<FootballEventModelDto> getUnfinishedFootballEvents() {
        List<EventModel> events = defaultEventService.getUnfinishedEvents();
        events.stream()
                .filter(event -> event.getSport() instanceof FootballMatchModel)
                .collect(Collectors.toList());
        return footballEventModelDtoMapper.mapToFootballEventModelDtos(events);
    }

    @Override
    public List<FootballEventModelDto> getFinishedFootballEvents() {
        List<EventModel> events = defaultEventService.getFinishedEvents();
        events.stream()
                .filter(event -> event.getSport() instanceof FootballMatchModel)
                .collect(Collectors.toList());
        return footballEventModelDtoMapper.mapToFootballEventModelDtos(events);
    }
}
