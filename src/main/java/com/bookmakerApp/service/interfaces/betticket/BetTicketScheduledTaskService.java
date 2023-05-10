package com.bookmakerApp.service.interfaces.betticket;

public interface BetTicketScheduledTaskService extends BetTicketService {
    void checkBetTicketFinish();

    void checkBetTicketResults();
}
