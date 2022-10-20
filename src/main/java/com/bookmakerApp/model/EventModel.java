package com.bookmakerApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.util.Date;

@Getter
@Setter
@Entity
public class EventModel {
    @Id
    private Long id;
    @DecimalMin("1.01")
    @DecimalMax("10.00")
    private Float probability;
    private Boolean success;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "id")
    private EventModel event;
    @OneToOne
    private SportModel sport;
}
