package com.bookmakerApp.facade.impl;

import com.bookmakerApp.facade.dtos.event.FootballEventModelDto;
import com.bookmakerApp.facade.dtos.event.MMAEventModelDto;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Qualifier("DefaultEventFacadeImpl")
public class DefaultEventFacadeImpl implements EventFacade {

    @Qualifier("DefaultEventServiceImpl")
    private final DefaultEventServiceImpl defaultEventService;

    @Override
    public List<FootballEventModelDto> getUnfinishedFootballEvents(int pageNumber) {
        ImmutablePair<List<EventModel>, Integer> footballEvents =
                getEventsByFinishAndSportName(false, SportName.Football, pageNumber);
        return FootballEventModelDtoMapper.mapToFootballEventModelDtos(footballEvents.getLeft(), footballEvents.getRight());
    }

    @Override
    public List<FootballEventModelDto> getFinishedFootballEvents(int pageNumber) {
        ImmutablePair<List<EventModel>, Integer> footballEvents =
                getEventsByFinishAndSportName(true, SportName.Football, pageNumber);
        return FootballEventModelDtoMapper.mapToFootballEventModelDtos(footballEvents.getLeft(), footballEvents.getRight());
    }

    @Override
    public List<MMAEventModelDto> getUnfinishedMMAEvents(int pageNumber) {
        ImmutablePair<List<EventModel>, Integer> mmaEvents =
                getEventsByFinishAndSportName(false, SportName.MMA, pageNumber);
        return MMAEventModelDtoMapper.mapToMMAEventModelDtos(mmaEvents.getLeft(), mmaEvents.getRight());
    }

    @Override
    public List<MMAEventModelDto> getFinishedMMAEvents(int pageNumber) {
        ImmutablePair<List<EventModel>, Integer> mmaEvents =
                getEventsByFinishAndSportName(true, SportName.MMA, pageNumber);
        return MMAEventModelDtoMapper.mapToMMAEventModelDtos(mmaEvents.getLeft(), mmaEvents.getRight());
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

    public EventModel addFootballEvent(EventModel event) {
        return defaultEventService.addFootballEvent(event);
    }

    public EventModel addMMAEvent(EventModel event) {
        return defaultEventService.addMMAEvent(event);
    }
}
