package com.bookmakerApp.controller;

import com.bookmakerApp.facade.impl.DefaultBetTicketFacadeImpl;
import com.bookmakerApp.model.BetTicketModel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class BetTicketController {

    private final DefaultBetTicketFacadeImpl defaultBetTicketFacade;

    @PostMapping("betTickets")
    public BetTicketModel addBetTicket(@RequestBody BetTicketModel newBetTicket){
        if(ObjectUtils.isEmpty(newBetTicket)){
            throw new IllegalArgumentException("BetTicket is empty or null");
        }else{
            return defaultBetTicketFacade.addBetTicket(newBetTicket);
        }
    }

}
