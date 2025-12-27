package classesCan.entiteSport.sousClasses;

import java.util.ArrayList;

import classesCan.Match;
import classesCan.personne.sousClasses.Joueur;
import classesCan.entiteSport.EntiteSportive;

public class Equipe extends EntiteSportive {

    protected ArrayList<Joueur> players = new ArrayList<Joueur>(); // From what cited in the specifications document
    protected String pays;

    public Equipe(String nom) {
        super(nom);
    }


    public Equipe(String nom, String pays) {
        super(nom);
        this.pays = pays;
    }
     
    

    public void addPlayer(Joueur p) {
        if(players.size() <= 24) {
            players.add(p);
        }else{
            System.out.println("Vous avez passer le limite des joueurs");
        }
    }

    // !!!!!!!!!!! to return !!!!!!!!!!!!!!!!!!!!!
    public boolean isPlaying() {
        return true;
    }

    // Return all the matches played by the team
    public ArrayList<Match> getAllMatchs() {
        return this.ListAffictation;
    }
}

    

