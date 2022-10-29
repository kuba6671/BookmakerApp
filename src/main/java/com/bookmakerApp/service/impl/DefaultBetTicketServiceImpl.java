package com.bookmakerApp.service.impl;

import com.bookmakerApp.model.BetTicketModel;
import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.repository.BetTicketRepository;
import com.bookmakerApp.service.interfaces.BetTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultBetTicketServiceImpl implements BetTicketService {

    private final BetTicketRepository betTicketRepository;

    @Override
    public List<BetTicketModel> getBetTicketsByUser(Long id){
        return betTicketRepository.getBetTicketModelsByUser(id);
    }

    @Override
    public List<BetTicketModel> getWonBetTicketsByUser(Long id){
       return betTicketRepository.getBetTicketModelsByUserAndSuccess(id, Boolean.TRUE);
    }

    @Override
    public List<BetTicketModel> getLostBetTicketByUsers(Long id){
        return betTicketRepository.getBetTicketModelsByUserAndSuccess(id, Boolean.FALSE);
    }

    @Override
    public List<BetTicketModel> getUnfinishedBetTicketsByUser(Long id){
        return betTicketRepository.getBetTicketModelsByUserAndFinish(id, false);
    }

    @Override
    public BetTicketModel addBetTicket(BetTicketModel betTicket){
        calculateBetTicket(betTicket);
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
}
