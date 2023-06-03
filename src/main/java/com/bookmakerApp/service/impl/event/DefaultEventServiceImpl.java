package com.bookmakerApp.service.impl.event;

import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.SportModel;
import com.bookmakerApp.model.enums.SportName;
import com.bookmakerApp.model.football.FootballMatchModel;
import com.bookmakerApp.model.mma.MMAFightModel;
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

    private static final String FIRST_TEAM_WIN = "FIRST_TEAM_WIN";
    private static final String SECOND_TEAM_WIN = "SECOND_TEAM_WIN";
    private static final String FIRST_FIGHTER_WIN = "FIRST_FIGHTER_WIN";
    private final static int PAGE_SIZE = 10;

    @Override
    public Page<EventModel> getEventsByFinishAndSportName(boolean finish, SportName sportName, int pageNumber) {
        return eventRepository.getEventModelsByFinishAndSportSportName(finish, sportName, PageRequest.of(pageNumber, PAGE_SIZE));
    }

    @Override
    public List<EventModel> getEventByDateBeforeAndResultIsChecked(Date date, boolean isChecked) {
        return eventRepository.getEventModelsByDateBeforeAndResultIsChecked(date, isChecked);
    }

    @Override
    public EventModel addFootballEvent(EventModel event) {
        setOddsForFootballEvent(event);
        return eventRepository.save(event);
    }

    @Override
    public List<EventModel> getEventsByIds(List<Long> idEvents) {
        return eventRepository.getEventModelsByIdEventIn(idEvents);
    }

    private void setOddsForFootballEvent(EventModel event) {
        SportModel sport = sportRepository
                .getSportModelByIdSport(event.getSport().getIdSport());
        if (sport instanceof FootballMatchModel) {
            String chosenResult = String.valueOf(event.getChosenResult());
            Double odds = getOddsForFootballForChosenResult((FootballMatchModel) sport, chosenResult);
            event.setOdds(odds);
        }
    }

    private Double getOddsForFootballForChosenResult(FootballMatchModel footballMatch, String chosenResult) {
        if (FIRST_TEAM_WIN.equals(chosenResult)) {
            return footballMatch.getHomeTeamWinOdds();
        } else if (SECOND_TEAM_WIN.equals(chosenResult)) {
            return footballMatch.getVisitingTeamWinOdds();
        } else {
            return footballMatch.getDraftOdds();
        }
    }

    @Override
    public EventModel addMMAEvent(EventModel event) {
        setOddsForMMAEvent(event);
        return eventRepository.save(event);
    }

    private void setOddsForMMAEvent(EventModel event) {
        SportModel sport = sportRepository
                .getSportModelByIdSport(event.getSport().getIdSport());
        if (sport instanceof MMAFightModel) {
            String chosenResult = String.valueOf(event.getChosenResult());
            Double odds = getOddsForMMAForChosenResult((MMAFightModel) sport, chosenResult);
            event.setOdds(odds);
        }
    }

    private Double getOddsForMMAForChosenResult(MMAFightModel mmaFight, String chosenResult) {
        return FIRST_FIGHTER_WIN.equals(chosenResult)
                ? mmaFight.getFirstFighterWinOdds() : mmaFight.getSecondFighterWinOdds();
    }
}
