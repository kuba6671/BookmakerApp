package com.bookmakerApp.service.interfaces.sport.mma;

import com.bookmakerApp.model.mma.MMAFightModel;
import com.bookmakerApp.model.mma.MMAFighterModel;

public interface MMAFightService {

    MMAFightModel addMMAFight(MMAFightModel mmaFight);

    MMAFighterModel addMMAFighter(MMAFighterModel mmaFighter);

}
