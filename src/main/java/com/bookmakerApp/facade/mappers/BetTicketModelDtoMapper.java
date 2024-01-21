package com.bookmakerApp.facade.mappers;

import com.bookmakerApp.facade.dtos.betticket.BetTicketModelDto;
import com.bookmakerApp.facade.dtos.event.DefaultEventModelDto;
import com.bookmakerApp.model.BetTicketModel;
import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.UserModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BetTicketModelDtoMapper {

    public static List<BetTicketModelDto> mapToBetTicketModelDtos(Page<BetTicketModel> betTickets) {
        int numberOfPages = betTickets.getTotalPages();
        return betTickets.stream()
                .map(betTicket -> mapToBetTicketModelDto(betTicket, betTicket.getUser(),
                        betTicket.getEvents(), numberOfPages))
                .toList();
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
