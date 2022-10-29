package com.bookmakerApp.facade.interfaces;

import com.bookmakerApp.facade.dtos.BetTicketModelDto;
import com.bookmakerApp.model.BetTicketModel;

import java.util.List;

public interface BetTicketFacade {

    List<BetTicketModel> getBetTicketsByUser(Long id);

    List<BetTicketModel> getWonBetTicketsByUser(Long id);

    List<BetTicketModel> getLostBetTicketByUsers(Long id);

    List<BetTicketModel> getUnfinishedBetTicketsByUser(Long id);

    BetTicketModel addBetTicket(BetTicketModel betTicket);
}
