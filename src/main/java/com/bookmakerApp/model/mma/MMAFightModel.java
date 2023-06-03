package com.bookmakerApp.model.mma;

import com.bookmakerApp.model.SportModel;
import com.bookmakerApp.model.enums.MMAFightResult;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Getter
@Setter
@Entity
public class MMAFightModel extends SportModel {
    @DecimalMin("1.01")
    @DecimalMax("10.00")
    private Double firstFighterWinOdds;
    @DecimalMin("1.01")
    @DecimalMax("10.00")
    private Double secondFighterWinOdds;
    @OneToOne
    private MMAFighterModel firstFighter;
    @OneToOne
    private MMAFighterModel secondFighter;
    private MMAFightResult fightResult;
}
