package com.bookmakerApp.service.impl;

import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.football.FootballMatchModel;
import com.bookmakerApp.repository.EventRepository;
import com.bookmakerApp.repository.SportRepository;
import com.bookmakerApp.service.interfaces.EventScheduledTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("EventScheduledTaskServiceImpl")
public class EventScheduledTaskServiceImpl extends DefaultEventServiceImpl implements EventScheduledTaskService {

    @Autowired
    private FootballMatchSimulatorServiceImpl footballMatchSimulatorService;

    public EventScheduledTaskServiceImpl(EventRepository eventRepository, SportRepository sportRepository) {
        super(eventRepository, sportRepository);
    }


    @Override
    @Scheduled(cron = "0 0/7 * * * ?")
    @Transactional
    public void simulateEvents() {
        Date date = new Date();
        List<EventModel> events = getEventByDateBefore(date);
        List<EventModel> footballMatchEvents = events.stream()
                .filter(event -> event.getSport() instanceof FootballMatchModel
                        && !event.isResultIsChecked())
                .collect(Collectors.toList());
        for (EventModel footballMatch : footballMatchEvents) {
            if(!footballMatch.isResultIsChecked())
                footballMatchSimulatorService.simulate(footballMatch);
        }
    }
}
