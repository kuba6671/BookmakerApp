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
    public List<FootballEventModelDto> getUnfinishedFootballEvents() {
        return footballEventFacade.getUnfinishedFootballEvents();
    }

    @GetMapping("/events/finishedFootballEvents")
    public List<FootballEventModelDto> getFinishedFootballEvents() {
        return footballEventFacade.getFinishedFootballEvents();
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
