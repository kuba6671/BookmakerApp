package com.bookmakerApp.facade.mappers;

import com.bookmakerApp.facade.dtos.betticket.BetTicketModelDto;
import com.bookmakerApp.facade.dtos.event.DefaultEventModelDto;
import com.bookmakerApp.model.BetTicketModel;
import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.UserModel;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class BetTicketModelDtoMapper {

    private BetTicketModelDtoMapper() {
    }

    public static List<BetTicketModelDto> mapToBetTicketModelDtos(Page<BetTicketModel> betTickets) {
        int numberOfPages = betTickets.getTotalPages();
        return betTickets.stream()
                .map(betTicket -> mapToBetTicketModelDto(betTicket, betTicket.getUser(),
                        betTicket.getEvents(), numberOfPages))
                .collect(Collectors.toList());
    }

    private static BetTicketModelDto mapToBetTicketModelDto
            (BetTicketModel betTicket, UserModel user, List<EventModel> events, int numberOfPages) {
        List<DefaultEventModelDto> defaultEventModelDtos = DefaultEventModelDtoMapper.mapToDefaultEventModelDtos(events);

        return BetTicketModelDto.builder()
                .finish(betTicket.isFinish())
                .success(betTicket.getSuccess())
                .deposit(betTicket.getDeposit())
                .toWin(betTicket.getToWin())
                .totalOdds(betTicket.getTotalOdds())
                .name(user.getName())
                .surname(user.getSurname())
                .date(betTicket.getDate())
                .events(defaultEventModelDtos)
                .numberOfPages(numberOfPages)
                .build();
    }
}
