package com.bookmakerApp.service.impl.sport.mma;

import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.SportModel;
import com.bookmakerApp.model.enums.MMAFightResult;
import com.bookmakerApp.model.mma.MMAFightModel;
import com.bookmakerApp.repository.EventRepository;
import com.bookmakerApp.service.interfaces.sport.SimulatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MMAFightSimulatorServiceImpl implements SimulatorService {

    private final EventRepository eventRepository;

    private static final String FIRST_FIGHTER_WIN = "FIRST_FIGHTER_WIN";
    private static final String SECOND_FIGHTER_WIN = "SECOND_FIGHTER_WIN";

    @Override
    public void simulate(EventModel event) {
        if (isMMAFightWithNoCheckedResult(event)) {
            simulateMMAFightResult(event);
            setSimulateResult(event.getSport().getIdSport());
        } else {
            throw new IllegalArgumentException("SportModel is not MMAFightModel");
        }
    }

    private void setSimulateResult(Long idSport) {
        List<EventModel> events = eventRepository.getEventModelsBySport_IdSportAndResultIsChecked(idSport, false);
        events = events.stream()
                .filter(this::isMMAFightWithNoCheckedResult)
                .toList();

        events.forEach(event -> {
            MMAFightModel mmaFight = (MMAFightModel) event.getSport();
            String chosenResult = String.valueOf(event.getChosenResult());
            if (!MMAFightResult.UNFINISHED.equals(mmaFight.getFightResult())) {
                event.setSuccess(chosenResult.equals(mmaFight.getFightResult().toString()));
                event.setFinish(true);
                event.setResultIsChecked(true);
            }
        });
    }

    private void simulateMMAFightResult(EventModel event) {
        SportModel mmaFight = event.getSport();
        if (mmaFight instanceof MMAFightModel) {
            String chosenResult = String.valueOf(event.getChosenResult());
            int probability = calculateProbabilityForChosenResult((MMAFightModel) mmaFight, chosenResult);
            simulateResult((MMAFightModel) mmaFight, probability, chosenResult);
        }
    }

    private void simulateResult(MMAFightModel mmaFight, int probability, String chosenResult) {
        Random random = new Random();
        boolean successProbability = random.nextInt(1, 101) >= probability;
        if (FIRST_FIGHTER_WIN.equals(chosenResult)) {
            if (successProbability) {
                mmaFight.setFightResult(MMAFightResult.FIRST_FIGHTER_WIN);
            } else {
                mmaFight.setFightResult(MMAFightResult.SECOND_FIGHTER_WIN);
            }
        } else if (SECOND_FIGHTER_WIN.equals(chosenResult)) {
            if (successProbability) {
                mmaFight.setFightResult(MMAFightResult.SECOND_FIGHTER_WIN);
            } else {
                mmaFight.setFightResult(MMAFightResult.FIRST_FIGHTER_WIN);
            }
        }
    }

    private int calculateProbabilityForChosenResult(MMAFightModel mmaFight, String chosenResult) {
        if (FIRST_FIGHTER_WIN.equals(chosenResult)) {
            return (int) (mmaFight.getFirstFighterWinOdds() * 10);
        } else {
            return (int) (mmaFight.getSecondFighterWinOdds() * 10);
        }
    }

    private boolean isMMAFightWithNoCheckedResult(EventModel event) {
        SportModel mmaFight = event.getSport();
        return mmaFight instanceof MMAFightModel && !event.isResultIsChecked();
    }

}
