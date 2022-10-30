package com.bookmakerApp.facade.mappers;

import com.bookmakerApp.facade.dtos.BetTicketModelDto;
import com.bookmakerApp.facade.dtos.DefaultEventModelDto;
import com.bookmakerApp.facade.dtos.FootballEventModelDto;
import com.bookmakerApp.model.BetTicketModel;
import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class BetTicketModelDtoMapper {

    private BetTicketModelDtoMapper(){}

    private static DefaultEventModelDtoMapper defaultEventModelDtoMapper;

    public static List<BetTicketModelDto> mapToBetTicketModelDtos(List<BetTicketModel> betTickets){
        return betTickets.stream()
                .map(betTicket -> mapToBetTicketModelDto(betTicket, betTicket.getUser(), betTicket.getEvents()))
                .collect(Collectors.toList());
    }

    private static BetTicketModelDto mapToBetTicketModelDto
            (BetTicketModel betTicket, UserModel user, List<EventModel> events){
            List<DefaultEventModelDto> defaultEventModelDtos = defaultEventModelDtoMapper.mapToDefaultEventModelDtos(events);

            return BetTicketModelDto.builder()
                    .finish(betTicket.isFinish())
                    .success(betTicket.getSuccess())
                    .deposit(betTicket.getDeposit())
                    .toWin(betTicket.getToWin())
                    .totalOdds(betTicket.getTotalOdds())
                    .name(user.getName())
                    .surname(user.getSurname())
                    .events(defaultEventModelDtos)
                    .build();
    }
}
