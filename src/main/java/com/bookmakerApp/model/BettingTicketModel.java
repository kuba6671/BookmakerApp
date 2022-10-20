package com.bookmakerApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class BettingTicketModel {
    @Id
    private Long id;
    private boolean finish;
    private Boolean success;
    @ManyToOne
    @JoinColumn(name="id")
    private UserModel user;
    @OneToMany(mappedBy = "event")
    private List<EventModel> events;
}
