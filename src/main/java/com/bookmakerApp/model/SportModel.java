package com.bookmakerApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class SportModel {
    @Id
    private Long idSport;
    private String sportName;
    @ManyToOne
    @JoinColumn(name = "idEvent")
    private EventModel event;
}
