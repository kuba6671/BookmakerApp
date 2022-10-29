package com.bookmakerApp.service.interfaces;

import com.bookmakerApp.model.BetTicketModel;

import java.util.List;

public interface BetTicketService {
    List<BetTicketModel> getBetTicketsByUser(Long id);

    List<BetTicketModel> getWonBetTicketsByUser(Long id);

    List<BetTicketModel> getLostBetTicketByUsers(Long id);

    List<BetTicketModel> getUnfinishedBetTicketsByUser(Long id);

    BetTicketModel addBetTicket(BetTicketModel betTicket);
}
