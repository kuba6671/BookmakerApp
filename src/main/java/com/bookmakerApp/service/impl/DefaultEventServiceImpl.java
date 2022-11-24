package com.bookmakerApp.service.impl;

import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.SportModel;
import com.bookmakerApp.model.football.FootballMatchModel;
import com.bookmakerApp.model.football.FootballTeamModel;
import com.bookmakerApp.repository.EventRepository;
import com.bookmakerApp.repository.SportRepository;
import com.bookmakerApp.service.interfaces.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Qualifier("DefaultEventServiceImpl")
public class DefaultEventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final SportRepository sportRepository;

    @Override
    public List<EventModel> getUnfinishedEvents() {
        return eventRepository.getEventModelsByFinish(false);
    }

    @Override
    public List<EventModel> getFinishedEvents() {
        return eventRepository.getEventModelsByFinish(true);
    }

    @Override
    public List<EventModel> getEventByDateBefore(Date date) {
        return eventRepository.getEventModelsByDateBefore(date);
    }

    @Override
    public EventModel addEventModel(EventModel event) {
        setOdds(event);
        return eventRepository.save(event);
    }

    private void setOdds(EventModel event) {
        SportModel sport = event.getSport();
        if (sport.getSportName().equals("Football")) {
            FootballMatchModel footballMatch = (FootballMatchModel) sportRepository.getSportModelByIdSport(sport.getIdSport());
            String choosenResult = String.valueOf(event.getChosenResult());
            if (choosenResult.equals("FIRST_TEAM_WIN")) {
                Double odds = footballMatch.getHomeTeamWinOdds();
                event.setOdds(odds);
            } else if (choosenResult.equals("SECOND_TEAM_WIN")) {
                Double odds = footballMatch.getVisitingTeamWinOdds();
                event.setOdds(odds);
            } else {
                Double odds = footballMatch.getDraftOdds();
                event.setOdds(odds);
            }
        }
    }
}
