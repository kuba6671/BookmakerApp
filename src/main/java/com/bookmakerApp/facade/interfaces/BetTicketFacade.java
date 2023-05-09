package com.bookmakerApp.facade.interfaces;

import com.bookmakerApp.facade.dtos.betticket.BetTicketModelDto;
import com.bookmakerApp.model.BetTicketModel;

import java.util.List;

public interface BetTicketFacade {

    List<BetTicketModelDto> getBetTicketsByUser(Long id, int pageNumber);

    List<BetTicketModelDto> getWonBetTicketsByUser(Long id, int pageNumber);

    List<BetTicketModelDto> getLostBetTicketByUser(Long id, int pageNumber);

    List<BetTicketModelDto> getUnfinishedBetTicketsByUser(Long id, int pageNumber);

    BetTicketModel addBetTicket(BetTicketModel betTicket);
}
