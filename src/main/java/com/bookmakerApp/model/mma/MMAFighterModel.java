package com.bookmakerApp.model.mma;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class MMAFighterModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idMMAFighter;
    private String name;
    private String surname;
    private String Country;
    private MMAFighterRecord record;
}
