package com.bookmakerApp.service.interfaces;

import com.bookmakerApp.model.EventModel;

import java.util.Date;
import java.util.List;

public interface EventService {
    List<EventModel> getUnfinishedEvents();
    List<EventModel> getFinishedEvents();
    List<EventModel> getEventByDateBefore(Date date);
    EventModel addEventModel(EventModel event);
}
