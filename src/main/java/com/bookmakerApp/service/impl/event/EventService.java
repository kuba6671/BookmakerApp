package com.bookmakerApp.service.impl.event;

import com.bookmakerApp.facade.dtos.event.AddEventDto;
import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.SportModel;
import com.bookmakerApp.model.enums.ChosenResult;
import com.bookmakerApp.model.enums.SportName;
import com.bookmakerApp.model.football.FootballMatchModel;
import com.bookmakerApp.model.mma.MMAFightModel;
import com.bookmakerApp.repository.EventRepository;
import com.bookmakerApp.repository.SportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

import static com.bookmakerApp.model.enums.ChosenResult.*;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final SportRepository sportRepository;

    private final static int PAGE_SIZE = 18;

    public Page<EventModel> getEventsByFinishAndSportName(boolean finish, SportName sportName, int pageNumber) {
        return eventRepository.getEventModelsByFinishAndSportSportName(finish, sportName, PageRequest.of(pageNumber, PAGE_SIZE));
    }

    public List<EventModel> getEventByDateBeforeAndResultIsChecked(Date date, boolean isChecked) {
        return eventRepository.getEventModelsByDateBeforeAndResultIsChecked(date, isChecked);
    }

    public List<EventModel> getEventsByIds(List<Long> idEvents) {
        return eventRepository.getEventModelsByIdEventIn(idEvents);
    }

    @Transactional
    public List<EventModel> addFootballEvent(AddEventDto addEventDto) {
        SportModel footballMatch = sportRepository.getSportModelByIdSport(addEventDto.getIdSport());
        if (footballMatch instanceof FootballMatchModel) {
            Date eventDate = addEventDto.getDate();
            EventModel firstTeamWinEvent = createEvent(
                    footballMatch, eventDate, FIRST_TEAM_WIN,
                    () -> getOddsForFootballForChosenResult((FootballMatchModel) footballMatch, FIRST_TEAM_WIN));
            EventModel secondTeamWinEvent = createEvent(
                    footballMatch, eventDate, SECOND_TEAM_WIN,
                    () -> getOddsForFootballForChosenResult((FootballMatchModel) footballMatch, SECOND_TEAM_WIN));
            EventModel draftEvent = createEvent(
                    footballMatch, eventDate, DRAFT,
                    () -> getOddsForFootballForChosenResult((FootballMatchModel) footballMatch, DRAFT));
            return eventRepository.saveAll(List.of(firstTeamWinEvent, secondTeamWinEvent, draftEvent));
        } else {
            throw new IllegalArgumentException("SportModel is not FootballMatchModel");
        }
    }

    @Transactional
    public List<EventModel> addMMAEvent(AddEventDto addEventDto) {
        SportModel mmaMatch = sportRepository.getSportModelByIdSport(addEventDto.getIdSport());
        if (mmaMatch instanceof MMAFightModel) {
            Date eventDate = addEventDto.getDate();
            EventModel firstFighterWinEvent = createEvent(
                    mmaMatch, eventDate, FIRST_FIGHTER_WIN,
                    () -> getOddsForMMAForChosenResult((MMAFightModel) mmaMatch, FIRST_FIGHTER_WIN));
            EventModel secondFighterWinEvent = createEvent(
                    mmaMatch, eventDate, SECOND_FIGHTER_WIN,
                    () -> getOddsForMMAForChosenResult((MMAFightModel) mmaMatch, SECOND_FIGHTER_WIN));
            return eventRepository.saveAll(List.of(firstFighterWinEvent, secondFighterWinEvent));
        } else {
            throw new IllegalArgumentException("SportModel is not FootballMatchModel");
        }
    }

    private EventModel createEvent(SportModel footballMatch,
                                   Date eventDate,
                                   ChosenResult chosenResult,
                                   Supplier<Double> getOddsMethod) {
        EventModel event = new EventModel();
        event.setChosenResult(chosenResult);
        event.setSport(footballMatch);
        event.setDate(eventDate);
        event.setOdds(getOddsMethod.get());
        return event;
    }

    private Double getOddsForFootballForChosenResult(FootballMatchModel footballMatch, ChosenResult chosenResult) {
        return switch (chosenResult) {
            case FIRST_TEAM_WIN -> footballMatch.getHomeTeamWinOdds();
            case SECOND_TEAM_WIN -> footballMatch.getVisitingTeamWinOdds();
            case DRAFT -> footballMatch.getDraftOdds();
            default -> throw new IllegalArgumentException("Unknown chosen result " + chosenResult);
        };
    }

    private Double getOddsForMMAForChosenResult(MMAFightModel mmaFight, ChosenResult chosenResult) {
        return FIRST_FIGHTER_WIN.equals(chosenResult)
                ? mmaFight.getFirstFighterWinOdds() : mmaFight.getSecondFighterWinOdds();
    }
}
