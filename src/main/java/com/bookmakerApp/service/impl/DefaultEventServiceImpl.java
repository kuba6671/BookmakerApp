package com.bookmakerApp.service.impl;

import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.repository.EventRepository;
import com.bookmakerApp.service.interfaces.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultEventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public List<EventModel> getUnfinishedEvents() {
        return eventRepository.getEventModelsByFinish(false);
    }

    @Override
    public List<EventModel> getFinishedEvents() {
        return eventRepository.getEventModelsByFinish(true);
    }
}
