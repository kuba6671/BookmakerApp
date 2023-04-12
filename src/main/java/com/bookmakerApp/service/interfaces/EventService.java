package com.bookmakerApp.service.interfaces;

import com.bookmakerApp.model.EventModel;

import java.util.Date;
import java.util.List;

public interface EventService {
    List<EventModel> getUnfinishedEvents();

    List<EventModel> getFinishedEvents();

    List<EventModel> getEventByDateBeforeAndResultIsChecked(Date date, boolean isChecked);

    EventModel addEventModel(EventModel event);

    List<EventModel> getEventsByIds(List<Long> idEvents);
}
