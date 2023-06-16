package com.bookmakerApp.facade.interfaces;

import com.bookmakerApp.facade.dtos.event.*;
import com.bookmakerApp.model.EventModel;

import java.util.List;

public interface EventFacade {
    List<GroupedFootballEventsDto> getUnfinishedFootballEvents(int pageNumber);

    List<GroupedFootballEventsDto> getFinishedFootballEvents(int pageNumber);

    List<GroupedMMAEventDto> getUnfinishedMMAEvents(int pageNumber);

    List<GroupedMMAEventDto> getFinishedMMAEvents(int pageNumber);

    List<MMAEventModelDto> getUnfinishedMMAEvents(int pageNumber);

    List<MMAEventModelDto> getFinishedMMAEvents(int pageNumber);

    List<FootballEventModelDto> getFootballEventsByIds(List<Long> idEvents);

    List<MMAEventModelDto> getMMAEventsByIds(List<Long> idEvents);

    List<EventModel> addFootballEvent(AddEventDto event);

    List<EventModel> addMMAEvent(AddEventDto addEventDto);
}
