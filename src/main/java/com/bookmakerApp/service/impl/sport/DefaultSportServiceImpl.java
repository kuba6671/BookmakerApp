package com.bookmakerApp.service.impl.sport;

import com.bookmakerApp.model.SportModel;
import com.bookmakerApp.repository.SportRepository;
import com.bookmakerApp.service.interfaces.sport.SportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultSportServiceImpl implements SportService {

    private final SportRepository sportRepository;

    private static final String FOOTBALL_MATCH = "Football";

    @Override
    public List<SportModel> getFootballMatches() {
        return sportRepository.getSportModelsBySportName(FOOTBALL_MATCH);
    }
}