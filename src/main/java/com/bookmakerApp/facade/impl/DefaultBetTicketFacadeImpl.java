package com.bookmakerApp.facade.impl;

import com.bookmakerApp.facade.interfaces.BetTicketFacade;
import com.bookmakerApp.model.BetTicketModel;
import com.bookmakerApp.service.impl.DefaultBetTicketServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DefaultBetTicketFacadeImpl implements BetTicketFacade {

    private final DefaultBetTicketServiceImpl defaultBetTicketService;

    @Override
    public List<BetTicketModel> getBetTicketsByUser(Long id) {
        throw new NotImplementedException("Not implemented yet");
    }

    @Override
    public List<BetTicketModel> getWonBetTicketsByUser(Long id) {
        throw new NotImplementedException("Not implemented yet");
    }

    @Override
    public List<BetTicketModel> getLostBetTicketByUsers(Long id) {
        throw new NotImplementedException("Not implemented yet");
    }

    @Override
    public List<BetTicketModel> getUnfinishedBetTicketsByUser(Long id) {
        throw new NotImplementedException("Not implemented yet");
    }

    @Override
    public BetTicketModel addBetTicket(BetTicketModel betTicket) {
        return defaultBetTicketService.addBetTicket(betTicket);
    }
}
