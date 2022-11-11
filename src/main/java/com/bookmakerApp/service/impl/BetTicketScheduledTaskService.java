package com.bookmakerApp.service.impl;

import com.bookmakerApp.model.BetTicketModel;
import com.bookmakerApp.repository.BetTicketRepository;
import com.bookmakerApp.service.interfaces.BetTicketService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BetTicketScheduledTaskService extends DefaultBetTicketServiceImpl implements BetTicketService {

    public BetTicketScheduledTaskService(BetTicketRepository betTicketRepository) {
        super(betTicketRepository);
    }

    @Scheduled(cron = "0 0/9 * * * ?")
    public void checkBetTicketFinish() {
        List<BetTicketModel> betTickets = getUnfinishedBetTickets();
        for (BetTicketModel betTicket : betTickets) {
            isFinishBetTicket(betTicket);
        }
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    public void checkBetTicketResults() {
        List<BetTicketModel> betTickets = getFinishedAndUncheckedBetTicket();
        for (BetTicketModel betTicket : betTickets) {
            isWonBetTicket(betTicket);
        }
    }
}
