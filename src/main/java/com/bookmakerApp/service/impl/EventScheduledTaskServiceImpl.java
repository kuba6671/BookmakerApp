package com.bookmakerApp.service.impl;

import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.football.FootballMatchModel;
import com.bookmakerApp.repository.EventRepository;
import com.bookmakerApp.repository.SportRepository;
import com.bookmakerApp.service.interfaces.EventScheduledTaskService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Qualifier("EventScheduledTaskServiceImpl")
public class EventScheduledTaskServiceImpl extends
        DefaultEventServiceImpl implements EventScheduledTaskService {

    private final FootballMatchSimulatorServiceImpl footballMatchSimulatorService;

    public EventScheduledTaskServiceImpl(EventRepository eventRepository,
                                         SportRepository sportRepository,
                                         FootballMatchSimulatorServiceImpl footballMatchSimulatorService) {
        super(eventRepository, sportRepository);
        this.footballMatchSimulatorService = footballMatchSimulatorService;
    }


    @Override
    @Scheduled(cron = "0 0/7 * * * ?")
    @Transactional
    public void simulateEvents() {
        Date date = new Date();
        List<EventModel> events = getEventByDateBeforeAndResultIsChecked(date, Boolean.FALSE);
        List<EventModel> footballMatchEvents = events.stream()
                .filter(event -> event.getSport() instanceof FootballMatchModel)
                .toList();

        footballMatchEvents.forEach(footballMatch -> {
            if (!footballMatch.isResultIsChecked()) {
                footballMatchSimulatorService.simulate(footballMatch);
            }
        });
    }
}
