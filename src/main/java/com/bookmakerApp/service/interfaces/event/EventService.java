package com.bookmakerApp.service.interfaces.event;

import com.bookmakerApp.model.EventModel;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface EventService {
    Page<EventModel> getEventsByFinish(boolean finish, int pageNumber);

    List<EventModel> getEventByDateBeforeAndResultIsChecked(Date date, boolean isChecked);

    EventModel addEventModel(EventModel event);

    List<EventModel> getEventsByIds(List<Long> idEvents);
}
