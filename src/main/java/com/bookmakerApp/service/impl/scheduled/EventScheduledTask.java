package com.bookmakerApp.service.impl.scheduled;

import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.football.FootballMatchModel;
import com.bookmakerApp.model.mma.MMAFightModel;
import com.bookmakerApp.service.impl.event.EventService;
import com.bookmakerApp.service.impl.sport.football.FootballMatchSimulatorService;
import com.bookmakerApp.service.impl.sport.mma.MMAFightSimulatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class EventScheduledTask {

    private final FootballMatchSimulatorService footballMatchSimulatorService;
    private final MMAFightSimulatorService mmaFightSimulatorService;
    private final EventService eventService;

    private final Map<Class<?>, Consumer<EventModel>> simulateByType = Map.of(
            FootballMatchModel.class, footballMatchSimulatorService::simulate,
            MMAFightModel.class, mmaFightSimulatorService::simulate
    );

    @Scheduled(cron = "0 0/7 * * * ?")
    @Transactional
    public void simulateEvents() {
        List<EventModel> events = eventService.getEventByDateBeforeAndResultIsChecked(new Date(), Boolean.FALSE);

        events.stream()
                .filter(event -> !event.isResultIsChecked())
                .forEach(event -> simulateByType.get(event.getSport().getClass()).accept(event));
    }
}
