package com.bookmakerApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class SportModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idSport;
    private String sportName;
}
