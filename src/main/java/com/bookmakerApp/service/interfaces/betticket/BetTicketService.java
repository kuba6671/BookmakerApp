package com.bookmakerApp.service.interfaces.betticket;

import com.bookmakerApp.model.BetTicketModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BetTicketService {
    Page<BetTicketModel> getBetTicketsByUser(Long id, int pageNumber);

    Page<BetTicketModel> getBetTicketsByUserAndResult(Long id, int pageNumber, boolean result);

    Page<BetTicketModel> getUnfinishedBetTicketsByUser(Long id, int pageNumber);

    List<BetTicketModel> getUnfinishedBetTickets();

    List<BetTicketModel> getFinishedAndUncheckedBetTicket();

    BetTicketModel addBetTicket(BetTicketModel betTicket);
}
