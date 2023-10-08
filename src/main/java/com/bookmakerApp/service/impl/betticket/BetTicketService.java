package com.bookmakerApp.service.impl.betticket;

import com.bookmakerApp.model.AccountModel;
import com.bookmakerApp.model.BetTicketModel;
import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.UserModel;
import com.bookmakerApp.repository.AccountRepository;
import com.bookmakerApp.repository.BetTicketRepository;
import com.bookmakerApp.repository.EventRepository;
import com.bookmakerApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BetTicketService {

    private final BetTicketRepository betTicketRepository;
    private final EventRepository eventRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    private final static int PAGE_SIZE = 5;

    public Page<BetTicketModel> getBetTicketsByUser(Long id, int pageNumber) {
        return betTicketRepository.getBetTicketModelsByUserIdUser(id, PageRequest.of(pageNumber, PAGE_SIZE));
    }

    public Page<BetTicketModel> getBetTicketsByUserAndResult(Long id, int pageNumber, boolean result) {
        return betTicketRepository.getBetTicketModelsByUserIdUserAndSuccess(id, result, PageRequest.of(pageNumber, PAGE_SIZE));
    }

    public Page<BetTicketModel> getUnfinishedBetTicketsByUser(Long id, int pageNumber) {
        return betTicketRepository.getBetTicketModelsByUserIdUserAndFinish(id, false, PageRequest.of(pageNumber, PAGE_SIZE));
    }

    public List<BetTicketModel> getUnfinishedBetTickets() {
        return betTicketRepository.getBetTicketModelsByFinish(false);
    }

    public List<BetTicketModel> getFinishedAndUncheckedBetTicket() {
        return betTicketRepository.getBetTicketModelsByFinishAndAndResultIsChecked(true, false);
    }

    @Transactional
    public BetTicketModel addBetTicket(BetTicketModel betTicket) {
        betTicket.setDate(new Date());
        calculateBetTicket(betTicket);
        updateAccountBalance(betTicket.getUser(), betTicket.getDeposit());

        return betTicketRepository.save(betTicket);
    }

    private void calculateBetTicket(BetTicketModel betTicket) {
        Double totalOdds = 1.0;
        List<EventModel> events = betTicket.getEvents();
        for (EventModel event : events) {
            event = eventRepository.getEventModelsByIdEvent(event.getIdEvent());
            totalOdds *= event.getOdds();
        }
        BigDecimal toWin = BigDecimal.valueOf(betTicket.getDeposit().doubleValue() * totalOdds);
        betTicket.setTotalOdds(totalOdds);
        betTicket.setToWin(toWin);
    }

    private void updateAccountBalance(UserModel user, BigDecimal deposit) {
        UserModel updatedUser = userRepository.findUserModelByIdUser(user.getIdUser());
        AccountModel account = accountRepository.getAccountModelByUser_IdUser(updatedUser.getIdUser());
        BigDecimal userBankBalance = account.getBankBalance();
        if (deposit.doubleValue() > userBankBalance.doubleValue()) {
            throw new IllegalArgumentException("You don't have enough money on your account");
        }
        account.setBankBalance(BigDecimal.valueOf(userBankBalance.doubleValue() - deposit.doubleValue()));
        updatedUser.setAccount(account);
    }
}
