package com.bookmakerApp.repository;

import com.bookmakerApp.model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<EventModel, Long> {
    List<EventModel> getEventModelsByFinish(boolean finish);
}
