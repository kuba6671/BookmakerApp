package com.bookmakerApp.service.impl;

import com.bookmakerApp.model.BetTicketModel;
import com.bookmakerApp.repository.BetTicketRepository;
import com.bookmakerApp.service.interfaces.BetTicketScheduledTaskService;
import com.bookmakerApp.service.interfaces.BetTicketService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BetTicketScheduledTaskServiceImpl extends DefaultBetTicketServiceImpl implements BetTicketScheduledTaskService {

    public BetTicketScheduledTaskServiceImpl(BetTicketRepository betTicketRepository) {
        super(betTicketRepository);
    }

    @Override
    @Scheduled(cron = "0 0/9 * * * ?")
    public void checkBetTicketFinish() {
        List<BetTicketModel> betTickets = getUnfinishedBetTickets();
        for (BetTicketModel betTicket : betTickets) {
            isFinishBetTicket(betTicket);
        }
    }

    @Override
    @Scheduled(cron = "0 0/10 * * * ?")
    public void checkBetTicketResults() {
        List<BetTicketModel> betTickets = getFinishedAndUncheckedBetTicket();
        for (BetTicketModel betTicket : betTickets) {
            isWonBetTicket(betTicket);
        }
    }
}
