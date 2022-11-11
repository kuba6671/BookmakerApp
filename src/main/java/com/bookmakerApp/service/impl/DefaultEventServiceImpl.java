package com.bookmakerApp.service.impl;

import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.SportModel;
import com.bookmakerApp.model.football.FootballMatchModel;
import com.bookmakerApp.repository.EventRepository;
import com.bookmakerApp.service.interfaces.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public List<EventModel> getEventByDateBefore(Date date) {
        return eventRepository.getEventModelsByDateBefore(date);
    }

    @Override
    public EventModel addEventModel(EventModel event) {
        setOdds(event);
        return eventRepository.save(event);
    }

    private void setOdds(EventModel event){
        SportModel sport = event.getSport();
        if(sport instanceof FootballMatchModel){
            String choosenResult = String.valueOf(((FootballMatchModel) sport).getChosenResult());
            if(choosenResult.equals(choosenResult.equals("FIRST_TEAM_WIN"))){
                Double odds = ((FootballMatchModel) sport).getHomeTeamWinOdds();
                event.setOdds(odds);
            }
            else if(choosenResult.equals("SECOND_TEAM_WIN")){
                Double odds = ((FootballMatchModel) sport).getVisitingTeamWinOdds();
                event.setOdds(odds);
            }
            else{
                Double odds = ((FootballMatchModel) sport).getDraftOdds();
                event.setOdds(odds);
            }
        }
    }
}
