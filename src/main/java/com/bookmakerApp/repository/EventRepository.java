package com.bookmakerApp.repository;

import com.bookmakerApp.model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventModel, Long> {
}
