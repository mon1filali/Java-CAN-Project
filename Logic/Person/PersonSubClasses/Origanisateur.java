package Logic.Person.PersonSubClasses;

import Logic.Person.Personne;


public class Origanisateur extends Personne {
    
    public Origanisateur(String nom, String nationalite) {
        super(nom, nationalite);
    }

    public String getPriorite() {
        return "Origanisateur";
    };
}