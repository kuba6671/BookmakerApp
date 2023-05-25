package com.bookmakerApp.controller;

import com.bookmakerApp.model.mma.MMAFightModel;
import com.bookmakerApp.model.mma.MMAFighterModel;
import com.bookmakerApp.service.impl.sport.mma.MMAFightServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Deprecated
public class MMAFightController {

    private final MMAFightServiceImpl mmaFightService;

    @PostMapping("/mmaFight")
    public MMAFightModel addFootballMatch(@RequestBody MMAFightModel mmaFight) {
        return mmaFightService.addMMAFight(mmaFight);
    }

    @PostMapping("/mmaFighter")
    public MMAFighterModel addFootballTeam(@RequestBody MMAFighterModel mmaFighter) {
        return mmaFightService.addMMAFighter(mmaFighter);
    }

}
