package com.bookmakerApp.facade.interfaces;

import com.bookmakerApp.facade.dtos.event.FootballEventModelDto;
import com.bookmakerApp.facade.dtos.event.GroupedFootballEventsDto;
import com.bookmakerApp.facade.dtos.event.GroupedMMAEventDto;
import com.bookmakerApp.facade.dtos.event.MMAEventModelDto;

import java.util.List;

public interface EventFacade {
    List<GroupedFootballEventsDto> getUnfinishedFootballEvents(int pageNumber);

    List<GroupedFootballEventsDto> getFinishedFootballEvents(int pageNumber);

    List<GroupedMMAEventDto> getUnfinishedMMAEvents(int pageNumber);

    List<GroupedMMAEventDto> getFinishedMMAEvents(int pageNumber);

    List<FootballEventModelDto> getFootballEventsByIds(List<Long> idEvents);

    List<MMAEventModelDto> getMMAEventsByIds(List<Long> idEvents);
}
