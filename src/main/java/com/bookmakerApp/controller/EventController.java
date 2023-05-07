package com.bookmakerApp.controller;

import com.bookmakerApp.facade.dtos.FootballEventModelDto;
import com.bookmakerApp.facade.impl.FootballEventFacadeImpl;
import com.bookmakerApp.model.EventModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class EventController {

    @Qualifier("FootballEventFacadeImpl")
    private final FootballEventFacadeImpl footballEventFacade;

    @GetMapping("/events/unfinishedFootballEvents")
    public List<FootballEventModelDto> getUnfinishedFootballEvents(@RequestParam(defaultValue = "0") int pageNumber) {
        return footballEventFacade.getUnfinishedFootballEvents(pageNumber);
    }

    @GetMapping("/events/finishedFootballEvents")
    public List<FootballEventModelDto> getFinishedFootballEvents(@RequestParam(defaultValue = "0") int pageNumber) {
        return footballEventFacade.getFinishedFootballEvents(pageNumber);
    }

    @GetMapping("/events/footballEventsByIds")
    public List<FootballEventModelDto> getFootballEventsByIds(
            @RequestParam(required = false, defaultValue = "") List<Long> idEvents) {
        return footballEventFacade.getFootballEventsByIds(idEvents);
    }

    @PostMapping("/footballEvent")
    public EventModel addFootballEvent(@RequestBody EventModel event) {
        return footballEventFacade.addEvent(event);
    }
}
