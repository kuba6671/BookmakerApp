package com.bookmakerApp.service.impl;

import com.bookmakerApp.model.BetTicketModel;
import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.UserModel;
import com.bookmakerApp.repository.BetTicketRepository;
import com.bookmakerApp.service.interfaces.BetTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Qualifier("DefaultBetTicketServiceImpl")
public class DefaultBetTicketServiceImpl implements BetTicketService {

    private final BetTicketRepository betTicketRepository;

    @Override
    public List<BetTicketModel> getBetTicketsByUser(Long id){
        return betTicketRepository.getBetTicketModelsByUserIdUser(id);
    }

    @Override
    public List<BetTicketModel> getWonBetTicketsByUser(Long id){
       return betTicketRepository.getBetTicketModelsByUserIdUserAndSuccess(id, Boolean.TRUE);
    }

    @Override
    public List<BetTicketModel> getLostBetTicketByUsers(Long id){
        return betTicketRepository.getBetTicketModelsByUserIdUserAndSuccess(id, Boolean.FALSE);
    }

    @Override
    public List<BetTicketModel> getUnfinishedBetTicketsByUser(Long id){
        return betTicketRepository.getBetTicketModelsByUserIdUserAndFinish(id, false);
    }

    @Override
    public List<BetTicketModel> getUnfinishedBetTickets() {
        return betTicketRepository.getBetTicketModelsByFinish(false);
    }

    @Override
    public List<BetTicketModel> getFinishedAndUncheckedBetTicket() {
        return betTicketRepository.getBetTicketModelsByFinishAndAndResultIsChecked(true, false);
    }

    @Override
    public BetTicketModel addBetTicket(BetTicketModel betTicket){
        BigDecimal deposit = betTicket.getDeposit();
        UserModel user = betTicket.getUser();
        calculateBetTicket(betTicket);
        updateAccountBalance(user,deposit);
        return betTicketRepository.save(betTicket);
    }

    private BetTicketModel calculateBetTicket(BetTicketModel betTicket){
        Double totalOdds = 1.0;
        List<EventModel> events = betTicket.getEvents();
        for(EventModel event : events){
            totalOdds *= event.getOdds();
        }
        BigDecimal toWin = BigDecimal.valueOf(betTicket.getDeposit().doubleValue() * totalOdds);
        betTicket.setTotalOdds(totalOdds);
        betTicket.setToWin(toWin);
        return betTicket;
    }

    private UserModel updateAccountBalance(UserModel user, BigDecimal deposit){
        BigDecimal userBankBalance = user.getAccount().getBankBalance();
        if(deposit.doubleValue() > userBankBalance.doubleValue()){
            throw new IllegalArgumentException("You don't have enough money on your account");
        }
        user.getAccount().setBankBalance(BigDecimal.valueOf(userBankBalance.doubleValue() - deposit.doubleValue()));
        return user;
    }

    protected Boolean isWonBetTicket(BetTicketModel betTicket){
        List<EventModel> events = betTicket.getEvents();
        for(EventModel event : events){
            if(!event.getSuccess()) {
                betTicket.setSuccess(Boolean.FALSE);
                betTicket.setResultIsChecked(true);
                return Boolean.FALSE;
            }
        }
        betTicket.setResultIsChecked(true);
        betTicket.setSuccess(Boolean.TRUE);
        return Boolean.TRUE;
    }

    protected boolean isFinishBetTicket(BetTicketModel betTicket){
        List<EventModel> events = betTicket.getEvents();
        for(EventModel event : events){
            if(!event.isFinish()) {
                betTicket.setFinish(false);
                return false;
            }
        }
        betTicket.setFinish(true);
        return true;
    }
}
