package com.bookmakerApp.facade.interfaces;

import com.bookmakerApp.facade.dtos.FootballEventModelDto;

import java.util.List;

public interface EventFacade {
    List<FootballEventModelDto> getUnfinishedFootballEvents();

    List<FootballEventModelDto> getFinishedFootballEvents();

    List<FootballEventModelDto> getFootballEventsByIds(List<Long> idEvents);
}
