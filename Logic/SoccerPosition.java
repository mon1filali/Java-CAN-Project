package Logic;

public enum SoccerPosition {
    GOALKEEPER,
    CENTER_BACK,
    LEFT_BACK,
    RIGHT_BACK,
    DEFENSIVE_MIDFIELDER,
    CENTRAL_MIDFIELDER,
    ATTACKING_MIDFIELDER,
    WINGER,
    STRIKER;

    public boolean isDefender() {
        return this == CENTER_BACK || this == LEFT_BACK || this == RIGHT_BACK;
    }

    public boolean isMidfielder() {
        return this == DEFENSIVE_MIDFIELDER
            || this == CENTRAL_MIDFIELDER
            || this == ATTACKING_MIDFIELDER;
    }

    public boolean isAttacker() {
        return this == WINGER || this == STRIKER;
    }
}
