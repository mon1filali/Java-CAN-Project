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

    public boolean isChevauchement(Creneau creneau) {
        if(this.date.equals(creneau.date)) {
            if(this.debut.equals(creneau.debut)) {
                return true;
            }

            if(this.debut.isBefore(creneau.debut) && (this.debut.plusMinutes(this.dureeMinutes)).isAfter(creneau.debut)) {
                return true;
            }

            if(this.debut.isAfter(creneau.debut) && creneau.debut.isBefore((this.debut.plusMinutes(this.dureeMinutes)))) {
                return true;
            }
        }

        return false;
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

    public String toString() {
        return "Day: " + this.date + " start Time: " + this.debut;
    }
}
