package com.bookmakerApp.facade.dtos.betticket;

import com.bookmakerApp.facade.dtos.event.DefaultEventModelDto;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Builder
public class BetTicketModelDto {
    private boolean finish;
    private Boolean success;
    private BigDecimal deposit;
    private BigDecimal toWin;
    private Double totalOdds;
    private String name;
    private String surname;
    private Date date;
    private List<DefaultEventModelDto> events;
    private int numberOfPages;
}
