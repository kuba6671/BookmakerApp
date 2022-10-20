package com.bookmakerApp.service.interfaces;

import com.bookmakerApp.model.BettingTicketModel;

import java.util.List;

public interface BettingTicketService {
    List<BettingTicketModel> getBettingTicketByUser(Long id);

    List<BettingTicketModel> getWonBettingTicketByUser(Long id);

    List<BettingTicketModel> getLostBettingTicketByUser(Long id);

    List<BettingTicketModel> getUnfinishedBettingTicketByUser(Long id);
}
