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
import com.bookmakerApp.service.util.ChosenResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;

import static com.bookmakerApp.model.enums.ChosenResult.*;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final SportRepository sportRepository;

    private static final int PAGE_SIZE = 18;

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
        SportModel sportModel = sportRepository.getSportModelByIdSport(addEventDto.getIdSport());
        if (sportModel instanceof FootballMatchModel footballMatch) {
            Date eventDate = addEventDto.getDate();
            List<EventModel> events = createEventsList(footballMatch, eventDate,
                    getFootballResults(),
                    ChosenResultUtil::getOddsForFootballForChosenResult);
            return eventRepository.saveAll(events);
        } else {
            throw new IllegalArgumentException("SportModel is not FootballMatchModel");
        }
    }


    @Transactional
    public List<EventModel> addMMAEvent(AddEventDto addEventDto) {
        SportModel sportModel = sportRepository.getSportModelByIdSport(addEventDto.getIdSport());
        if (sportModel instanceof MMAFightModel mmaFight) {
            Date eventDate = addEventDto.getDate();
            List<EventModel> events = createEventsList(mmaFight, eventDate,
                    getMmaResults(),
                    ChosenResultUtil::getOddsForMMAForChosenResult);
            return eventRepository.saveAll(events);
        } else {
            throw new IllegalArgumentException("SportModel is not MMAFightModel");
        }
    }

    private <T extends SportModel> List<EventModel> createEventsList(T sport,
                                                                     Date eventDate,
                                                                     List<ChosenResult> chosenResult,
                                                                     BiFunction<T, ChosenResult, Double> getOdds) {
        return chosenResult.stream()
                .map(result -> createEvent(sport, eventDate, result, getOdds.apply(sport, result)))
                .toList();


    }

    private EventModel createEvent(SportModel footballMatch,
                                   Date eventDate,
                                   ChosenResult chosenResult,
                                   Double odds) {
        EventModel event = new EventModel();
        event.setChosenResult(chosenResult);
        event.setSport(footballMatch);
        event.setDate(eventDate);
        event.setOdds(odds);
        return event;
    }
}
