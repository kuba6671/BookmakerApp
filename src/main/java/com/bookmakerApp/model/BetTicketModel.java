package com.bookmakerApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class BetTicketModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idBetTicket;
    private boolean finish;
    private Boolean success;
    private boolean resultIsChecked;
    private BigDecimal deposit;
    private BigDecimal toWin;
    private Double totalOdds;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private UserModel user;
    @ManyToMany
    private List<EventModel> events;
}
