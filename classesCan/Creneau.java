package classesCan;

import java.time.LocalDate;
import java.time.LocalTime;

public class Creneau {
      protected LocalDate date; // Match day
    protected LocalTime debut; // Start time
    protected int dureeMinutes;

    public boolean isChevauchement() {
        return true;
    }
}
