package com.bookmakerApp.model;

import com.bookmakerApp.model.enums.ChosenResult;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idEvent;
    @DecimalMin("1.01")
    @DecimalMax("10.00")
    private Double odds;
    private Boolean success;
    private boolean finish;
    private Date date;
    @OneToOne
    @JoinColumn(name = "idSport")
    private SportModel sport;
    @Enumerated(EnumType.STRING)
    private ChosenResult chosenResult;
    private boolean resultIsChecked;
}
