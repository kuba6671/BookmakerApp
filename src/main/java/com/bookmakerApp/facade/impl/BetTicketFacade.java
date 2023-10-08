package com.bookmakerApp.facade.impl;

import com.bookmakerApp.facade.dtos.betticket.BetTicketModelDto;
import com.bookmakerApp.facade.mappers.BetTicketModelDtoMapper;
import com.bookmakerApp.model.BetTicketModel;
import com.bookmakerApp.service.impl.betticket.BetTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BetTicketFacade {

    private final BetTicketService betTicketService;

    public List<BetTicketModelDto> getBetTicketsByUser(Long id, int pageNumber) {
        return BetTicketModelDtoMapper.
                mapToBetTicketModelDtos(betTicketService.getBetTicketsByUser(id, pageNumber));
    }

    public List<BetTicketModelDto> getWonBetTicketsByUser(Long id, int pageNumber) {
        return BetTicketModelDtoMapper.
                mapToBetTicketModelDtos(betTicketService.getBetTicketsByUserAndResult(id, pageNumber, Boolean.TRUE));
    }

    public List<BetTicketModelDto> getLostBetTicketByUser(Long id, int pageNumber) {
        return BetTicketModelDtoMapper.
                mapToBetTicketModelDtos(betTicketService.getBetTicketsByUserAndResult(id, pageNumber, Boolean.FALSE));
    }

    public List<BetTicketModelDto> getUnfinishedBetTicketsByUser(Long id, int pageNumber) {
        return BetTicketModelDtoMapper.
                mapToBetTicketModelDtos(betTicketService.getUnfinishedBetTicketsByUser(id, pageNumber));
    }

    public BetTicketModel addBetTicket(BetTicketModel betTicket) {
        return betTicketService.addBetTicket(betTicket);
    }
}
