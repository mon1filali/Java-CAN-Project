package Logic.Sport.TimeHandler;

import java.time.LocalDate;
import java.time.LocalTime;

import Logic.Sport.Match;

public class Creneau {
    public LocalDate date; // Match day
    public LocalTime debut; // Start time
    public int dureeMinutes;

    public Creneau(LocalDate date, LocalTime debut, int dureeMinutes) {
        this.date = date;
        this.debut = debut;
        this.dureeMinutes = dureeMinutes;
    }

    public boolean isChevauchement(Match match) {
        if(this.date.equals(match.creneau.date)) {
            if(this.debut.equals(match.creneau.debut)) {
                return false;
            }

            if(this.debut.isBefore(match.creneau.debut) && (this.debut.plusMinutes(this.dureeMinutes)).equals(match.creneau.debut)) {
                return false;
            }

            if(this.debut.isAfter(match.creneau.debut) && match.creneau.debut.equals((this.debut.plusMinutes(this.dureeMinutes)))) {
                return false;
            }

            return true;
        }

        return true;
    }

    public void changeDureeMinutes(int time) {
        this.dureeMinutes += time;
    }

    public void changeDate(LocalDate newDate) {
        this.date = newDate;
    }

    public void changeTime(LocalTime newDebut) {
        this.debut = newDebut;
    }
}
