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

    @Override
    public List<BetTicketModelDto> getBetTicketsByUser(Long id) {
        return BetTicketModelDtoMapper.
                mapToBetTicketModelDtos(defaultBetTicketService.getBetTicketsByUser(id));
    }

    @Override
    public List<BetTicketModelDto> getWonBetTicketsByUser(Long id) {
        return BetTicketModelDtoMapper.
                mapToBetTicketModelDtos(defaultBetTicketService.getWonBetTicketsByUser(id));
    }

    @Override
    public List<BetTicketModelDto> getLostBetTicketByUser(Long id) {
        return BetTicketModelDtoMapper.
                mapToBetTicketModelDtos(defaultBetTicketService.getLostBetTicketByUsers(id));
    }

    @Override
    public List<BetTicketModelDto> getUnfinishedBetTicketsByUser(Long id) {
        return BetTicketModelDtoMapper.
                mapToBetTicketModelDtos(defaultBetTicketService.getUnfinishedBetTicketsByUser(id));
    }

    @Override
    public BetTicketModel addBetTicket(BetTicketModel betTicket) {
        return defaultBetTicketService.addBetTicket(betTicket);
    }
}
