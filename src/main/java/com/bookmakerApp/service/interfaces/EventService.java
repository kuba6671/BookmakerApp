package com.bookmakerApp.service.interfaces;

import com.bookmakerApp.model.EventModel;

import java.util.List;

public interface EventService {
    List<EventModel> getUnfinishedEvents();
    List<EventModel> getFinishedEvents();
    EventModel addEventModel(EventModel event);
}
