package Logic.Sport;

import Logic.Person.PersonSubClasses.Arbitre;
import Logic.Sport.TimeHandler.Creneau;
import Logic.MatchState;
import java.time.LocalDate;
import java.time.LocalTime;
import Logic.MatchImportance;

// !! don't forget isplaying and isempty

public class Match {
    public static int compteur = 0;
    public int codeMatch;
    public Equipe equipeA;
    public Equipe equipeB;
    public Stade stade;
    public Arbitre arbitre;
    public Creneau creneau;
    public MatchState statut;
    public MatchImportance importance;
    /* protected Equipe winner => score; */

    public Match(Equipe equipeA, Equipe equipeB, Stade stade, Arbitre arbitre, Creneau creneau,
            MatchImportance importance) {
        this.equipeA = equipeA;
        this.equipeB = equipeB;
        this.stade = stade;
        this.arbitre = arbitre;
        this.creneau = creneau;
        this.statut = MatchState.PENDING;
        this.importance = importance;
    }

    public void changeStatut() {
        if (this.statut == MatchState.PENDING && LocalDate.now().equals(this.creneau.date)) {
            if (LocalTime.now().isAfter(this.creneau.debut.plusMinutes(this.creneau.dureeMinutes))) {
                this.statut = MatchState.FINISH;
            } else if(LocalTime.now().isAfter(this.creneau.debut)) {
                this.statut = MatchState.PLAYING;
            } else{
                this.statut = MatchState.PENDING;
            }
        }

        if(this.statut == MatchState.PLAYING && LocalDate.now().equals(this.creneau.date)) {
            if (LocalTime.now().isAfter(this.creneau.debut.plusMinutes(this.creneau.dureeMinutes))) {
                this.statut = MatchState.FINISH;
            }
        }

        if(LocalDate.now().isAfter(this.creneau.date)) {
            this.statut = MatchState.FINISH;
        }
    }

    public void changeImportance(MatchImportance importance) {
        if (this.importance != importance) {
            this.importance = importance;
        } else {
            System.out.println("The match already in this state");
        }
    }

    public void setCreneau(Creneau creneau) {
        this.creneau = creneau;
    }
}
