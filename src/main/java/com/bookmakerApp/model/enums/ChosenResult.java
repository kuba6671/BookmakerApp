package com.bookmakerApp.model.enums;

import java.util.List;

public enum ChosenResult {
    FIRST_TEAM_WIN,
    SECOND_TEAM_WIN,
    DRAFT,
    FIRST_FIGHTER_WIN,
    SECOND_FIGHTER_WIN;

    public static List<ChosenResult> getFootballResults() {
        return List.of(FIRST_TEAM_WIN, SECOND_TEAM_WIN, DRAFT);
    }

    public static List<ChosenResult> getMmaResults() {
        return List.of(FIRST_FIGHTER_WIN, SECOND_FIGHTER_WIN);
    }
}
