package com.bookmakerApp.repository;

import com.bookmakerApp.model.EventModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<EventModel, Long> {
    Page<EventModel> getEventModelsByFinish(boolean finish, Pageable pageable);

    List<EventModel> getEventModelsByDateBeforeAndResultIsChecked(Date date, boolean isChecked);

    List<EventModel> getEventModelsBySport_IdSport(Long idSport);

    List<EventModel> getEventModelsByIdEventIn(List<Long> idEvents);

    EventModel getEventModelsByIdEvent(Long idEvent);
}
