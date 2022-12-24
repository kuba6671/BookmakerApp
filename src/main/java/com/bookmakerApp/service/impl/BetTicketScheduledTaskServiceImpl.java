package com.bookmakerApp.service.impl;

import com.bookmakerApp.model.BetTicketModel;
import com.bookmakerApp.repository.BetTicketRepository;
import com.bookmakerApp.service.interfaces.BetTicketScheduledTaskService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("BetTicketScheduledTaskServiceImpl")
public class BetTicketScheduledTaskServiceImpl extends DefaultBetTicketServiceImpl implements BetTicketScheduledTaskService {

    public BetTicketScheduledTaskServiceImpl(BetTicketRepository betTicketRepository) {
        super(betTicketRepository);
    }

    @Override
    @Scheduled(cron = "0 0/9 * * * ?")
    @Transactional
    public void checkBetTicketFinish() {
        List<BetTicketModel> betTickets = getUnfinishedBetTickets();
        for (BetTicketModel betTicket : betTickets) {
            isFinishBetTicket(betTicket);
        }
    }

    @Override
    @Scheduled(cron = "0 0/10 * * * ?")
    @Transactional
    public void checkBetTicketResults() {
        List<BetTicketModel> betTickets = getFinishedAndUncheckedBetTicket();
        for (BetTicketModel betTicket : betTickets) {
            isWonBetTicket(betTicket);
        }
    }
}
