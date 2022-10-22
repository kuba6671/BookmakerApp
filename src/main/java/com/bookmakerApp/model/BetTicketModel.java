package com.bookmakerApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
public class BetTicketModel {
    @Id
    private Long id;
    private boolean finish;
    private Boolean success;
    private BigDecimal deposit;
    private BigDecimal toWin;
    private Double totalOdds;
    @ManyToOne
    @JoinColumn(name="id")
    private UserModel user;
    @OneToMany(mappedBy = "event")
    private List<EventModel> events;
}
