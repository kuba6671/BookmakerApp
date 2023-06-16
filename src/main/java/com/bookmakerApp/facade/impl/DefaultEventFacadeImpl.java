package com.bookmakerApp.facade.impl;


import com.bookmakerApp.facade.dtos.event.*;
import com.bookmakerApp.facade.interfaces.EventFacade;
import com.bookmakerApp.facade.mappers.FootballEventModelDtoMapper;
import com.bookmakerApp.facade.mappers.MMAEventModelDtoMapper;
import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.enums.SportName;
import com.bookmakerApp.model.football.FootballMatchModel;
import com.bookmakerApp.model.mma.MMAFightModel;
import com.bookmakerApp.service.impl.event.DefaultEventServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;


@RequiredArgsConstructor
@Component
@Qualifier("DefaultEventFacadeImpl")
public class DefaultEventFacadeImpl implements EventFacade {

    @Qualifier("DefaultEventServiceImpl")
    private final DefaultEventServiceImpl defaultEventService;

    @Override
    public List<GroupedFootballEventsDto> getUnfinishedFootballEvents(int pageNumber) {
        return getMappedFootballEvents(pageNumber, Boolean.FALSE);
    }

    @Override
    public List<GroupedFootballEventsDto> getFinishedFootballEvents(int pageNumber) {
        return getMappedFootballEvents(pageNumber, Boolean.TRUE);

    }

    private List<GroupedFootballEventsDto> getMappedFootballEvents(int pageNumber, boolean isFinish) {
        ImmutablePair<List<EventModel>, Integer> footballEvents =
                getEventsByFinishAndSportName(isFinish, SportName.Football, pageNumber);
        Map<Pair<Long, Date>, List<EventModel>> groupedEvents = footballEvents.getLeft().stream()
                .collect(groupingBy(event -> new ImmutablePair<>(event.getSport().getIdSport(), event.getDate())));
        List<GroupedFootballEventsDto> mappedFootballEvents = new LinkedList<>();
        groupedEvents.forEach((key, value) ->
                mappedFootballEvents.add(FootballEventModelDtoMapper.mapToGroupedFootballEventDtos(value, footballEvents.getRight())));
        return mappedFootballEvents;
    }

    @Override
    public List<GroupedMMAEventDto> getUnfinishedMMAEvents(int pageNumber) {
        return getMappedMMAEvents(pageNumber, Boolean.FALSE);
    }

    @Override
    public List<GroupedMMAEventDto> getFinishedMMAEvents(int pageNumber) {
        return getMappedMMAEvents(pageNumber, Boolean.TRUE);

    }

    private List<GroupedMMAEventDto> getMappedMMAEvents(int pageNumber, boolean isFinish) {
        ImmutablePair<List<EventModel>, Integer> mmaEvents =
                getEventsByFinishAndSportName(isFinish, SportName.MMA, pageNumber);
        Map<Pair<Long, Date>, List<EventModel>> groupedEvents = mmaEvents.getLeft().stream()
                .collect(groupingBy(event -> new ImmutablePair<>(event.getSport().getIdSport(), event.getDate())));
        List<GroupedMMAEventDto> mappedMMAEvents = new LinkedList<>();
        groupedEvents.forEach((key, value) ->
                mappedMMAEvents.add(MMAEventModelDtoMapper.mapToGroupedMMAEventDtos(value, mmaEvents.getRight())));
        return mappedMMAEvents;
    }

    private ImmutablePair<List<EventModel>, Integer> getEventsByFinishAndSportName(boolean finish, SportName sportName, int pageNumber) {
        Page<EventModel> events = defaultEventService.getEventsByFinishAndSportName(finish, sportName, pageNumber);
        return new ImmutablePair<>(events.getContent(), events.getTotalPages());
    }

    @Override
    public List<FootballEventModelDto> getFootballEventsByIds(List<Long> idEvents) {
        List<EventModel> events = defaultEventService.getEventsByIds(idEvents);
        events = events.stream()
                .filter(event -> event.getSport() instanceof FootballMatchModel)
                .collect(Collectors.toList());
        return FootballEventModelDtoMapper.mapToFootballEventModelDtos(events, 0);
    }

    @Override
    public List<MMAEventModelDto> getMMAEventsByIds(List<Long> idEvents) {
        List<EventModel> events = defaultEventService.getEventsByIds(idEvents);
        events = events.stream()
                .filter(event -> event.getSport() instanceof MMAFightModel)
                .collect(Collectors.toList());
        return MMAEventModelDtoMapper.mapToMMAEventModelDtos(events, 0);
    }

    @Override
    public List<EventModel> addFootballEvent(AddEventDto addEventDto) {
        return defaultEventService.addFootballEvent(addEventDto);
    }

    @Override
    public List<EventModel> addMMAEvent(AddEventDto addEventDto) {
        return defaultEventService.addMMAEvent(addEventDto);
    }
}
