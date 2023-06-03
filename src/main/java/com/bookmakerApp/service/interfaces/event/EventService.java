package com.bookmakerApp.service.interfaces.event;

import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.enums.SportName;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface EventService {
    Page<EventModel> getEventsByFinishAndSportName(boolean finish, SportName sportName, int pageNumber);

    List<EventModel> getEventByDateBeforeAndResultIsChecked(Date date, boolean isChecked);

    EventModel addFootballEvent(EventModel event);

    EventModel addMMAEvent(EventModel event);

    List<EventModel> getEventsByIds(List<Long> idEvents);
}
