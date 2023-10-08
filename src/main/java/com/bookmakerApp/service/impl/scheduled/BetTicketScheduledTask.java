package com.bookmakerApp.service.impl.scheduled;

import com.bookmakerApp.model.BetTicketModel;
import com.bookmakerApp.model.EventModel;

import com.bookmakerApp.service.impl.betticket.BetTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BetTicketScheduledTask {

    private final BetTicketService betTicketService;

    @Scheduled(cron = "0 0/9 * * * ?")
    @Transactional
    public void checkBetTicketFinish() {
        final List<BetTicketModel> betTickets = betTicketService.getUnfinishedBetTickets();
        betTickets.forEach(this::setBetTicketFinish);
    }

    private void setBetTicketFinish(BetTicketModel betTicket) {
        List<EventModel> events = betTicket.getEvents();
        for (EventModel event : events) {
            if (!event.isFinish()) {
                betTicket.setFinish(false);
                return;
            }
        }
        betTicket.setFinish(true);
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    @Transactional
    public void checkBetTicketResults() {
        final List<BetTicketModel> betTickets = betTicketService.getFinishedAndUncheckedBetTicket();
        betTickets.forEach(this::setBetTicketResult);
    }

    private void setBetTicketResult(BetTicketModel betTicket) {
        final List<EventModel> events = betTicket.getEvents();
        for (EventModel event : events) {
            if (!event.getSuccess()) {
                betTicket.setSuccess(Boolean.FALSE);
                betTicket.setResultIsChecked(true);
                return;
            }
        }
        betTicket.setResultIsChecked(true);
        betTicket.setSuccess(Boolean.TRUE);
    }
}
