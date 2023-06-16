package com.bookmakerApp.service.interfaces.event;

import com.bookmakerApp.facade.dtos.event.AddEventDto;
import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.enums.SportName;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface EventService {
    Page<EventModel> getEventsByFinishAndSportName(boolean finish, SportName sportName, int pageNumber);

    List<EventModel> getEventByDateBeforeAndResultIsChecked(Date date, boolean isChecked);

    List<EventModel> addFootballEvent(AddEventDto addEventDto);

    List<EventModel> addMMAEvent(AddEventDto addEventDto);

    List<EventModel> getEventsByIds(List<Long> idEvents);
}
