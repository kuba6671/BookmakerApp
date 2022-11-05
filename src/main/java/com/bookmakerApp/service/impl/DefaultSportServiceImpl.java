package com.bookmakerApp.service.impl;

import com.bookmakerApp.model.SportModel;
import com.bookmakerApp.repository.SportRepository;
import com.bookmakerApp.service.interfaces.SportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultSportServiceImpl implements SportService {

    private final SportRepository sportRepository;

    @Override
    public List<SportModel> getFootballMatches() {
        return sportRepository.getSportModelsBySportName("Mecz piłkarski");
    }
}
