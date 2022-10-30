package com.bookmakerApp.facade.interfaces;

import com.bookmakerApp.facade.dtos.BetTicketModelDto;
import com.bookmakerApp.model.BetTicketModel;

import java.util.List;

public interface BetTicketFacade {

    List<BetTicketModelDto> getBetTicketsByUser(Long id);

    List<BetTicketModelDto> getWonBetTicketsByUser(Long id);

    List<BetTicketModelDto> getLostBetTicketByUser(Long id);

    List<BetTicketModelDto> getUnfinishedBetTicketsByUser(Long id);

    BetTicketModel addBetTicket(BetTicketModel betTicket);
}
