package com.bookmakerApp.service.impl;

import com.bookmakerApp.model.BettingTicketModel;
import com.bookmakerApp.repository.BettingTicketRepository;
import com.bookmakerApp.service.interfaces.BettingTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultBettingTicketServiceImpl implements BettingTicketService {

    private final BettingTicketRepository bettingTicketRepository;

    @Override
    public List<BettingTicketModel> getBettingTicketByUser(Long id){
        return bettingTicketRepository.getBettingTicketModelByUser(id);
    }

    @Override
    public List<BettingTicketModel> getWonBettingTicketByUser(Long id){
       return bettingTicketRepository.getBettingTicketModelByUserAndSuccess(id, Boolean.TRUE);
    }

    @Override
    public List<BettingTicketModel> getLostBettingTicketByUser(Long id){
        return bettingTicketRepository.getBettingTicketModelByUserAndSuccess(id, Boolean.FALSE);
    }

    @Override
    public List<BettingTicketModel> getUnfinishedBettingTicketByUser(Long id){
        return bettingTicketRepository.getBettingTicketModelByUserAndFinish(id, false);
    }
}
