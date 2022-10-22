package com.bookmakerApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
    private Double odds;
    private Boolean success;
    private Date date;
    @OneToOne
    private SportModel sport;
}
