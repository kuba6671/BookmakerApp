package com.bookmakerApp.controller;

import com.bookmakerApp.facade.dtos.event.*;
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
    public List<GroupedFootballEventsDto> getUnfinishedFootballEvents(@RequestParam(defaultValue = "0") int pageNumber) {
        return eventFacade.getUnfinishedFootballEvents(pageNumber);
    }

    @GetMapping("/events/finishedFootballEvents")
    public List<GroupedFootballEventsDto> getFinishedFootballEvents(@RequestParam(defaultValue = "0") int pageNumber) {
        return eventFacade.getFinishedFootballEvents(pageNumber);
    }

    @GetMapping("/events/unfinishedMMAEvents")
    public List<GroupedMMAEventDto> getUnfinishedMMAEvents(@RequestParam(defaultValue = "0") int pageNumber) {
        return eventFacade.getUnfinishedMMAEvents(pageNumber);
    }

    @GetMapping("/events/finishedMMAEvents")
    public List<GroupedMMAEventDto> getFinishedMMAEvents(@RequestParam(defaultValue = "0") int pageNumber) {
        return eventFacade.getFinishedMMAEvents(pageNumber);
    }

    @GetMapping("/events/footballEventsByIds")
    public List<FootballEventModelDto> getFootballEventsByIds(
            @RequestParam(required = false, defaultValue = "") List<Long> idEvents) {
        return eventFacade.getFootballEventsByIds(idEvents);
    }

    @GetMapping("/events/mmaEventsByIds")
    public List<MMAEventModelDto> getMMAEventsByIds(
            @RequestParam(required = false, defaultValue = "") List<Long> idEvents) {
        return eventFacade.getMMAEventsByIds(idEvents);
    }

    @PostMapping("/footballEvent")
    public List<EventModel> addFootballEvent(@RequestBody AddEventDto addEventDto) {
        return eventFacade.addFootballEvent(addEventDto);
    }

    @PostMapping("/mmaEvent")
    public List<EventModel> addMMAEvent(@RequestBody AddEventDto addEventDto) {
        return eventFacade.addMMAEvent(addEventDto);
    }
}
