package classesCan.entiteSport.sousClasses;
import java.util.ArrayList;

import classesCan.Match;
import classesCan.entiteSport.EntiteSportive;

public class Stade extends EntiteSportive{
    
    protected String ville;
    protected int capacite;
    protected String categorie;


     public Stade(String nom, String ville, int capacite, String categorie) {
        super(nom);
        this.ville = ville;
        this.capacite = capacite;
        this.categorie = categorie;
    }

    // !!!!!!!!!!! to return !!!!!!!!!!!!!!!!!!!!!
    public boolean isEmpty() {
        return true;
    }

    // Return all the matches played in the stad
    public ArrayList<Match> getAllMatchs() {
        return this.ListAffictation;
    }


    
}
