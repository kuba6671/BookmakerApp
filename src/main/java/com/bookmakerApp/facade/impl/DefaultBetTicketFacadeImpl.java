package com.bookmakerApp.facade.impl;

import com.bookmakerApp.facade.dtos.BetTicketModelDto;
import com.bookmakerApp.facade.interfaces.BetTicketFacade;
import com.bookmakerApp.facade.mappers.BetTicketModelDtoMapper;
import com.bookmakerApp.model.BetTicketModel;
import com.bookmakerApp.service.impl.DefaultBetTicketServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@Qualifier("DefaultBetTicketFacadeImpl")
public class DefaultBetTicketFacadeImpl implements BetTicketFacade {

    @Qualifier("DefaultBetTicketServiceImpl")
    private final DefaultBetTicketServiceImpl defaultBetTicketService;

    public List<BetTicketModelDto> getBetTicketsByUser(Long id, int pageNumber) {
        return BetTicketModelDtoMapper.
                mapToBetTicketModelDtos(defaultBetTicketService.getBetTicketsByUser(id, pageNumber));
    }

    @Override
    public List<BetTicketModelDto> getWonBetTicketsByUser(Long id, int pageNumber) {
        return BetTicketModelDtoMapper.
                mapToBetTicketModelDtos(defaultBetTicketService.getBetTicketsByUserAndResult(id, pageNumber, Boolean.TRUE));
    }

    @Override
    public List<BetTicketModelDto> getLostBetTicketByUser(Long id, int pageNumber) {
        return BetTicketModelDtoMapper.
                mapToBetTicketModelDtos(defaultBetTicketService.getBetTicketsByUserAndResult(id, pageNumber, Boolean.FALSE));
    }

    @Override
    public List<BetTicketModelDto> getUnfinishedBetTicketsByUser(Long id, int pageNumber) {
        return BetTicketModelDtoMapper.
                mapToBetTicketModelDtos(defaultBetTicketService.getUnfinishedBetTicketsByUser(id, pageNumber));
    }

    @Override
    public BetTicketModel addBetTicket(BetTicketModel betTicket) {
        return defaultBetTicketService.addBetTicket(betTicket);
    }
}
