package Logic.Interface.Abstract;

import java.util.ArrayList;

import Logic.Sport.Match;

public abstract class EntiteSportive {
    protected static int compteur = 0;
    protected int id;
    protected String nom;
    protected ArrayList<Match> affectationList; // the list of matches the team participated in. list of matches hosted in the stadium 

    public EntiteSportive(String nom) {
        this.id = compteur;
        compteur++;
        this.nom = nom;
    }

    public abstract ArrayList<Match> getAllMatches();
}
