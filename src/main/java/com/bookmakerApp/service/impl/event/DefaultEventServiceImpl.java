package com.bookmakerApp.service.impl.event;

import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.SportModel;
import com.bookmakerApp.model.football.FootballMatchModel;
import com.bookmakerApp.repository.EventRepository;
import com.bookmakerApp.repository.SportRepository;
import com.bookmakerApp.service.interfaces.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Qualifier("DefaultEventServiceImpl")
public class DefaultEventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final SportRepository sportRepository;

    private static final String FOOTBALL_MATCH = "Football";
    private static final String FIRST_TEAM_WIN = "FIRST_TEAM_WIN";
    private static final String SECOND_TEAM_WIN = "SECOND_TEAM_WIN";
    private final static int PAGE_SIZE = 10;

    @Override
    public Page<EventModel> getUnfinishedEvents(int pageNumber) {
        return eventRepository.getEventModelsByFinish(false, PageRequest.of(pageNumber, PAGE_SIZE));
    }

    @Override
    public Page<EventModel> getFinishedEvents(int pageNumber) {
        return eventRepository.getEventModelsByFinish(true, PageRequest.of(pageNumber, PAGE_SIZE));
    }

    @Override
    public List<EventModel> getEventByDateBeforeAndResultIsChecked(Date date, boolean isChecked) {
        return eventRepository.getEventModelsByDateBeforeAndResultIsChecked(date, isChecked);
    }

    @Override
    public EventModel addEventModel(EventModel event) {
        setOdds(event);
        return eventRepository.save(event);
    }

    @Override
    public List<EventModel> getEventsByIds(List<Long> idEvents) {
        return eventRepository.getEventModelsByIdEventIn(idEvents);
    }

    private void setOdds(EventModel event) {
        SportModel sport = event.getSport();
        if (FOOTBALL_MATCH.equals(sport.getSportName())) {
            FootballMatchModel footballMatch = (FootballMatchModel) sportRepository
                    .getSportModelByIdSport(sport.getIdSport());
            String chosenResult = String.valueOf(event.getChosenResult());
            Double odds = getOddsForChosenResult(footballMatch, chosenResult);
            event.setOdds(odds);
        }
    }

    private Double getOddsForChosenResult(FootballMatchModel footballMatch, String chosenResult) {
        if (FIRST_TEAM_WIN.equals(chosenResult)) {
            return footballMatch.getHomeTeamWinOdds();
        } else if (SECOND_TEAM_WIN.equals(chosenResult)) {
            return footballMatch.getVisitingTeamWinOdds();
        } else {
            return footballMatch.getDraftOdds();
        }
    }
}
