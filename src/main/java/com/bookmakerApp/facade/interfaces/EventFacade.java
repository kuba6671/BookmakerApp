package com.bookmakerApp.facade.interfaces;

import com.bookmakerApp.facade.dtos.event.FootballEventModelDto;
import com.bookmakerApp.facade.dtos.event.MMAEventModelDto;

import java.util.List;

public interface EventFacade {
    List<FootballEventModelDto> getUnfinishedFootballEvents(int pageNumber);

    List<FootballEventModelDto> getFinishedFootballEvents(int pageNumber);

    List<MMAEventModelDto> getUnfinishedMMAEvents(int pageNumber);

    List<MMAEventModelDto> getFinishedMMAEvents(int pageNumber);

    List<FootballEventModelDto> getFootballEventsByIds(List<Long> idEvents);

    List<MMAEventModelDto> getMMAEventsByIds(List<Long> idEvents);
}
