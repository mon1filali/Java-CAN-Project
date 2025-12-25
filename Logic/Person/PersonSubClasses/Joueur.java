package Logic.Person.PersonSubClasses;

import Logic.SoccerPosition;
import Logic.Person.Personne;

public class Joueur extends Personne {
    protected int joueuerNum;
    protected SoccerPosition emplacement;
    
    public Joueur(String nom, String nationalite, SoccerPosition emplacement) {
        super(nom, nationalite);
        this.emplacement = emplacement;
    }

    public String getPriorite() {
        return "Joueur";
    };

    public SoccerPosition playerPosition() {
        return this.emplacement;
    }
}

