package com.bookmakerApp.model.mma;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class MMAFighterRecord {
    int numberOfWins;
    int numberOfLosses;

    @Override
    public String toString() {
        return numberOfWins + ":" + numberOfLosses;
    }
}
