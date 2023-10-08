package com.bookmakerApp.service.impl.sport.mma;

import com.bookmakerApp.model.enums.SportName;
import com.bookmakerApp.model.mma.MMAFightModel;
import com.bookmakerApp.model.mma.MMAFighterModel;
import com.bookmakerApp.repository.MMAFightRepository;
import com.bookmakerApp.repository.MMAFighterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MMAFightService {

    private final MMAFightRepository mmaFightRepository;
    private final MMAFighterRepository mmaFighterRepository;

    public MMAFightModel addMMAFight(MMAFightModel mmaFight) {
        mmaFight.setSportName(SportName.MMA);
        return mmaFightRepository.save(mmaFight);
    }

    public MMAFighterModel addMMAFighter(MMAFighterModel mmaFighter) {
        return mmaFighterRepository.save(mmaFighter);
    }
}
