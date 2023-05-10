package com.bookmakerApp.facade.interfaces;

import com.bookmakerApp.facade.dtos.event.FootballEventModelDto;

import java.util.List;

public interface EventFacade {
    List<FootballEventModelDto> getUnfinishedFootballEvents(int pageNumber);

    List<FootballEventModelDto> getFinishedFootballEvents(int pageNumber);

    List<FootballEventModelDto> getFootballEventsByIds(List<Long> idEvents);
}
