package com.bookmakerApp.service.impl.event;

import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.football.FootballMatchModel;
import com.bookmakerApp.model.mma.MMAFightModel;
import com.bookmakerApp.repository.EventRepository;
import com.bookmakerApp.repository.SportRepository;
import com.bookmakerApp.service.impl.sport.football.FootballMatchSimulatorServiceImpl;
import com.bookmakerApp.service.impl.sport.mma.MMAFightSimulatorServiceImpl;
import com.bookmakerApp.service.interfaces.event.EventScheduledTaskService;
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
    private final MMAFightSimulatorServiceImpl mmaFightSimulatorService;

    public EventScheduledTaskServiceImpl(EventRepository eventRepository,
                                         SportRepository sportRepository,
                                         FootballMatchSimulatorServiceImpl footballMatchSimulatorService,
                                         MMAFightSimulatorServiceImpl mmaFightSimulatorService) {
        super(eventRepository, sportRepository);
        this.footballMatchSimulatorService = footballMatchSimulatorService;
        this.mmaFightSimulatorService = mmaFightSimulatorService;
    }


    @Override
    @Scheduled(cron = "0 0/7 * * * ?")
    @Transactional
    public void simulateEvents() {
        List<EventModel> events = getEventByDateBeforeAndResultIsChecked(new Date(), Boolean.FALSE);

        events.stream()
                .filter(event -> !event.isResultIsChecked())
                .forEach(event -> {
                    if (event.getSport() instanceof FootballMatchModel) {
                        footballMatchSimulatorService.simulate(event);
                    } else if (event.getSport() instanceof MMAFightModel) {
                        mmaFightSimulatorService.simulate(event);
                    }
                });
    }
}
