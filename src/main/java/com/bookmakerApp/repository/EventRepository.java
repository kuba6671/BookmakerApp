package com.bookmakerApp.repository;

import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.enums.SportName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<EventModel, Long> {
    Page<EventModel> getEventModelsByFinishAndSportSportName(boolean finish, SportName sportName, Pageable pageable);

    List<EventModel> getEventModelsByDateBeforeAndResultIsChecked(Date date, boolean isChecked);

    List<EventModel> getEventModelsBySport_IdSportAndResultIsChecked(Long idSport, boolean isChecked);

    List<EventModel> getEventModelsByIdEventIn(List<Long> idEvents);

    EventModel getEventModelsByIdEvent(Long idEvent);
}
