package com.bookmakerApp.facade.impl;

import com.bookmakerApp.facade.dtos.event.FootballEventModelDto;
import com.bookmakerApp.facade.interfaces.EventFacade;
import com.bookmakerApp.facade.mappers.FootballEventModelDtoMapper;
import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.football.FootballMatchModel;
import com.bookmakerApp.service.impl.event.DefaultEventServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
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
        ImmutablePair<List<EventModel>, Integer> footballEvents = getFootballEventsByFinish(false, pageNumber);
        return FootballEventModelDtoMapper.mapToFootballEventModelDtos(footballEvents.getLeft(), footballEvents.getRight());
    }

    @Override
    public List<FootballEventModelDto> getFinishedFootballEvents(int pageNumber) {
        ImmutablePair<List<EventModel>, Integer> footballEvents = getFootballEventsByFinish(true, pageNumber);
        return FootballEventModelDtoMapper.mapToFootballEventModelDtos(footballEvents.getLeft(), footballEvents.getRight());
    }

    private ImmutablePair<List<EventModel>, Integer> getFootballEventsByFinish(boolean finish, int pageNumber) {
        Page<EventModel> events = defaultEventService.getEventsByFinish(finish, pageNumber);
        List<EventModel> footballEvents = events.stream()
                .filter(event -> event.getSport() instanceof FootballMatchModel)
                .toList();
        return new ImmutablePair<>(footballEvents, events.getTotalPages());
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
