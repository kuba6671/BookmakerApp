package com.bookmakerApp.controller;

import com.bookmakerApp.facade.dtos.betticket.BetTicketModelDto;
import com.bookmakerApp.facade.impl.BetTicketFacade;
import com.bookmakerApp.model.BetTicketModel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class BetTicketController {

    private final BetTicketFacade betTicketFacade;

    @PostMapping("betTickets")
    public BetTicketModel addBetTicket(@RequestBody BetTicketModel newBetTicket) {
        if (ObjectUtils.isEmpty(newBetTicket)) {
            throw new IllegalArgumentException("BetTicket is empty or null");
        } else {
            return betTicketFacade.addBetTicket(newBetTicket);
        }
    }

    @GetMapping("/betTicketsUser/{id}")
    public List<BetTicketModelDto> getBetTicketsByUser(@PathVariable Long id,
                                                       @RequestParam(defaultValue = "0") int pageNumber) {
        return betTicketFacade.getBetTicketsByUser(id, pageNumber);
    }

    @GetMapping("/betTicketsWon/{id}")
    public List<BetTicketModelDto> getWonBetTicketsByUser(@PathVariable Long id,
                                                          @RequestParam(defaultValue = "0") int pageNumber) {
        return betTicketFacade.getWonBetTicketsByUser(id, pageNumber);
    }

    @GetMapping("/betTicketsLost/{id}")
    public List<BetTicketModelDto> getLostBetTicketByUsers(@PathVariable Long id,
                                                           @RequestParam(defaultValue = "0") int pageNumber) {
        return betTicketFacade.getLostBetTicketByUser(id, pageNumber);
    }

    @GetMapping("/betTicketsUnfinished/{id}")
    public List<BetTicketModelDto> getUnfinishedBetTicketsByUser(@PathVariable Long id,
                                                                 @RequestParam(defaultValue = "0") int pageNumber) {
        return betTicketFacade.getUnfinishedBetTicketsByUser(id, pageNumber);
    }

}
