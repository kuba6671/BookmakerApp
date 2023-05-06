package com.bookmakerApp.facade.impl;

import com.bookmakerApp.facade.dtos.FootballEventModelDto;
import com.bookmakerApp.facade.interfaces.EventFacade;
import com.bookmakerApp.facade.mappers.FootballEventModelDtoMapper;
import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.football.FootballMatchModel;
import com.bookmakerApp.service.impl.DefaultEventServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
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
    public List<FootballEventModelDto> getUnfinishedFootballEvents(int pageNumber) {
        Page<EventModel> events = defaultEventService.getUnfinishedEvents(pageNumber);
        List<EventModel> footballEvents = events.stream()
                .filter(event -> event.getSport() instanceof FootballMatchModel)
                .toList();
        return FootballEventModelDtoMapper.mapToFootballEventModelDtos(footballEvents, events.getTotalPages());
    }

    @Override
    public List<FootballEventModelDto> getFinishedFootballEvents(int pageNumber) {
        Page<EventModel> events = defaultEventService.getFinishedEvents(pageNumber);
        List<EventModel> footballEvents = events.stream()
                .filter(event -> event.getSport() instanceof FootballMatchModel)
                .toList();
        return FootballEventModelDtoMapper.mapToFootballEventModelDtos(footballEvents, events.getTotalPages());
    }

    @Override
    public List<FootballEventModelDto> getFootballEventsByIds(List<Long> idEvents) {
        List<EventModel> events = defaultEventService.getEventsByIds(idEvents);
        events = events.stream()
                .filter(event -> event.getSport() instanceof FootballMatchModel)
                .collect(Collectors.toList());
        return FootballEventModelDtoMapper.mapToFootballEventModelDtos(events, 0);
    }

    public EventModel addEvent(EventModel event) {
        return defaultEventService.addEventModel(event);
    }
}
