package classesCan.entiteSport;
import java.util.ArrayList;

import classesCan.Match;

public  abstract class   EntiteSportive {
    protected static int compteur = 0;
    protected int id ;
    protected String nom;
    protected ArrayList<Match> ListAffictation;// the list of matches the team participated in. list of matches hosted in the stadium 

    public EntiteSportive() {
        compteur++;
        this.id = compteur;
        this.ListAffictation = new ArrayList<Match>();
    }


    public EntiteSportive(String nom) {
        compteur++;
        this.id = compteur;
        this.nom = nom;
        this.ListAffictation = new ArrayList<Match>();
        
    }

    public abstract ArrayList<Match> getAllMatchs();

    
}
