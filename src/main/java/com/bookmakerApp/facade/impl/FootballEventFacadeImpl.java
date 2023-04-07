package com.bookmakerApp.facade.impl;

import com.bookmakerApp.facade.dtos.FootballEventModelDto;
import com.bookmakerApp.facade.interfaces.EventFacade;
import com.bookmakerApp.facade.mappers.FootballEventModelDtoMapper;
import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.football.FootballMatchModel;
import com.bookmakerApp.service.impl.DefaultEventServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Qualifier("FootballEventFacadeImpl")
public class FootballEventFacadeImpl implements EventFacade {

    @Qualifier("DefaultEventServiceImpl")
    private final DefaultEventServiceImpl defaultEventService;

    @Override
    public List<FootballEventModelDto> getUnfinishedFootballEvents() {
        List<EventModel> events = defaultEventService.getUnfinishedEvents();
        events.stream()
                .filter(event -> event.getSport() instanceof FootballMatchModel)
                .collect(Collectors.toList());
        return FootballEventModelDtoMapper.mapToFootballEventModelDtos(events);
    }

    @Override
    public List<FootballEventModelDto> getFinishedFootballEvents() {
        List<EventModel> events = defaultEventService.getFinishedEvents();
        events.stream()
                .filter(event -> event.getSport() instanceof FootballMatchModel)
                .collect(Collectors.toList());
        return FootballEventModelDtoMapper.mapToFootballEventModelDtos(events);
    }

    @Override
    public List<FootballEventModelDto> getFootballEventsByIds(List<Long> idEvents) {
        List<EventModel> events = defaultEventService.getEventsByIds(idEvents);
        events.stream()
                .filter(event -> event.getSport() instanceof FootballMatchModel)
                .collect(Collectors.toList());
        return FootballEventModelDtoMapper.mapToFootballEventModelDtos(events);
    }

    public EventModel addEvent(EventModel event) {
        return defaultEventService.addEventModel(event);
    }
}
