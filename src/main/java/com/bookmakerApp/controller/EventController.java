package com.bookmakerApp.controller;

import com.bookmakerApp.facade.dtos.event.FootballEventModelDto;
import com.bookmakerApp.facade.dtos.event.MMAEventModelDto;
import com.bookmakerApp.facade.impl.DefaultEventFacadeImpl;
import com.bookmakerApp.model.EventModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class EventController {

    @Qualifier("DefaultEventFacadeImpl")
    private final DefaultEventFacadeImpl eventFacade;

    @GetMapping("/events/unfinishedFootballEvents")
    public List<FootballEventModelDto> getUnfinishedFootballEvents(@RequestParam(defaultValue = "0") int pageNumber) {
        return eventFacade.getUnfinishedFootballEvents(pageNumber);
    }

    @GetMapping("/events/finishedFootballEvents")
    public List<FootballEventModelDto> getFinishedFootballEvents(@RequestParam(defaultValue = "0") int pageNumber) {
        return eventFacade.getFinishedFootballEvents(pageNumber);
    }

    @GetMapping("/events/unfinishedMMAEvents")
    public List<MMAEventModelDto> getUnfinishedMMAEvents(@RequestParam(defaultValue = "0") int pageNumber) {
        return eventFacade.getUnfinishedMMAEvents(pageNumber);
    }

    @GetMapping("/events/finishedMMAEvents")
    public List<MMAEventModelDto> getFinishedMMAEvents(@RequestParam(defaultValue = "0") int pageNumber) {
        return eventFacade.getFinishedMMAEvents(pageNumber);
    }

    @GetMapping("/events/footballEventsByIds")
    public List<FootballEventModelDto> getFootballEventsByIds(
            @RequestParam(required = false, defaultValue = "") List<Long> idEvents) {
        return eventFacade.getFootballEventsByIds(idEvents);
    }

    @PostMapping("/footballEvent")
    public EventModel addFootballEvent(@RequestBody EventModel event) {
        return eventFacade.addEvent(event);
    }
}
