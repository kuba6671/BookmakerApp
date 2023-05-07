package com.bookmakerApp.service.interfaces;

import com.bookmakerApp.model.BetTicketModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BetTicketService {
    Page<BetTicketModel> getBetTicketsByUser(Long id, int pageNumber);

    Page<BetTicketModel> getWonBetTicketsByUser(Long id, int pageNumber);

    Page<BetTicketModel> getLostBetTicketByUsers(Long id, int pageNumber);

    Page<BetTicketModel> getUnfinishedBetTicketsByUser(Long id, int pageNumber);

    List<BetTicketModel> getUnfinishedBetTickets();

    List<BetTicketModel> getFinishedAndUncheckedBetTicket();

    BetTicketModel addBetTicket(BetTicketModel betTicket);
}
