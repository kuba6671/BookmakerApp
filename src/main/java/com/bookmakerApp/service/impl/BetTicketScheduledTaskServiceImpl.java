package com.bookmakerApp.service.impl;

import com.bookmakerApp.model.BetTicketModel;
import com.bookmakerApp.repository.AccountRepository;
import com.bookmakerApp.repository.BetTicketRepository;
import com.bookmakerApp.repository.EventRepository;
import com.bookmakerApp.repository.UserRepository;
import com.bookmakerApp.service.interfaces.BetTicketScheduledTaskService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("BetTicketScheduledTaskServiceImpl")
public class BetTicketScheduledTaskServiceImpl extends
        DefaultBetTicketServiceImpl implements BetTicketScheduledTaskService {

    public BetTicketScheduledTaskServiceImpl(BetTicketRepository betTicketRepository,
                                             EventRepository eventRepository,
                                             AccountRepository accountRepository,
                                             UserRepository userRepository) {
        super(betTicketRepository, eventRepository, accountRepository, userRepository);
    }

    @Override
    @Scheduled(cron = "0 0/9 * * * ?")
    @Transactional
    public void checkBetTicketFinish() {
        final List<BetTicketModel> betTickets = getUnfinishedBetTickets();
        betTickets.forEach(this::setBetTicketFinish);
    }

    @Override
    @Scheduled(cron = "0 0/10 * * * ?")
    @Transactional
    public void checkBetTicketResults() {
        final List<BetTicketModel> betTickets = getFinishedAndUncheckedBetTicket();
        betTickets.forEach(this::setBetTicketResult);
    }
}
