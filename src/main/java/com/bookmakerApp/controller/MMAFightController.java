package com.bookmakerApp.controller;

import com.bookmakerApp.model.mma.MMAFightModel;
import com.bookmakerApp.model.mma.MMAFighterModel;
import com.bookmakerApp.service.impl.sport.mma.MMAFightService;
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

    private final MMAFightService mmaFightService;

    @PostMapping("/mmaFight")
    public MMAFightModel addMMAFight(@RequestBody MMAFightModel mmaFight) {
        return mmaFightService.addMMAFight(mmaFight);
    }

    @PostMapping("/mmaFighter")
    public MMAFighterModel addMMAFighter(@RequestBody MMAFighterModel mmaFighter) {
        return mmaFightService.addMMAFighter(mmaFighter);
    }

}
