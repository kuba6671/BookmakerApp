package com.bookmakerApp.service.impl;

import com.bookmakerApp.model.BetTicketModel;
import com.bookmakerApp.repository.BetTicketRepository;
import com.bookmakerApp.service.interfaces.BettingTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultBettingTicketServiceImpl implements BettingTicketService {

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
}
