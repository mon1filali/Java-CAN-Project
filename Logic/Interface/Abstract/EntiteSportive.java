package Logic.Interface.Abstract;

import java.util.ArrayList;

import Logic.Sport.Match;

public abstract class EntiteSportive {
    protected static int compteur = 0;
    protected int id;
    public String nom;
    public ArrayList<Match> affectationList = new ArrayList<Match>(); // the list of matches the team participated in. list of matches hosted in the stadium 

    public EntiteSportive(String nom) {
        this.id = compteur;
        compteur++;
        this.nom = nom;
    }

    public abstract ArrayList<Match> getAllMatches();

    public void removeMatch(Match m) {
        affectationList.remove(m);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Match> getAffectationList() {
        return affectationList;
    }

    public void setAffectationList(ArrayList<Match> affectationList) {
        this.affectationList = affectationList;
    }    
}
