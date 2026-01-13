package Logic.Person.PersonSubClasses;

import Logic.Person.Personne;

public class Arbitre extends Personne {
    
    public Arbitre(String nom, String nationalite) {
        super(nom, nationalite);
    }

    public String getPriorite() {
        return "Arbitre";
    };

    public String toString() {
        return "Referee " + this.nom;
    }
}
