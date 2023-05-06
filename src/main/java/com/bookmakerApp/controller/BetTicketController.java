package com.bookmakerApp.controller;

import com.bookmakerApp.facade.dtos.BetTicketModelDto;
import com.bookmakerApp.facade.impl.DefaultBetTicketFacadeImpl;
import com.bookmakerApp.model.BetTicketModel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class BetTicketController {

    @Qualifier("DefaultBetTicketFacadeImpl")
    private final DefaultBetTicketFacadeImpl defaultBetTicketFacade;

    @PostMapping("betTickets")
    public BetTicketModel addBetTicket(@RequestBody BetTicketModel newBetTicket) {
        if (ObjectUtils.isEmpty(newBetTicket)) {
            throw new IllegalArgumentException("BetTicket is empty or null");
        } else {
            return defaultBetTicketFacade.addBetTicket(newBetTicket);
        }
    }

    @GetMapping("/betTicketsUser/{id}")
    public List<BetTicketModelDto> getBetTicketsByUser(@PathVariable Long id,
                                                       @RequestParam(defaultValue = "0") int pageNumber) {
        return defaultBetTicketFacade.getBetTicketsByUser(id, pageNumber);
    }

    @GetMapping("/betTicketsWon/{id}")
    public List<BetTicketModelDto> getWonBetTicketsByUser(@PathVariable Long id,
                                                          @RequestParam(defaultValue = "0") int pageNumber) {
        return defaultBetTicketFacade.getWonBetTicketsByUser(id, pageNumber);
    }

    @GetMapping("/betTicketsLost/{id}")
    public List<BetTicketModelDto> getLostBetTicketByUsers(@PathVariable Long id,
                                                           @RequestParam(defaultValue = "0") int pageNumber) {
        return defaultBetTicketFacade.getLostBetTicketByUser(id, pageNumber);
    }

    @GetMapping("/betTicketsUnfinished/{id}")
    public List<BetTicketModelDto> getUnfinishedBetTicketsByUser(@PathVariable Long id,
                                                                 @RequestParam(defaultValue = "0") int pageNumber) {
        return defaultBetTicketFacade.getUnfinishedBetTicketsByUser(id, pageNumber);
    }

}
