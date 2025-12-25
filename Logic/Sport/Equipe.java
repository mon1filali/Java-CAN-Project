package Logic.Sport;

import java.util.ArrayList;

import Logic.Interface.Abstract.EntiteSportive;
import Logic.Person.PersonSubClasses.Joueur;

public class Equipe extends EntiteSportive {
    /* getAllMatches()
    When did this team play */
    protected ArrayList<Joueur> players = new ArrayList<Joueur>(); // From what cited in the specifications document

    public Equipe(String nom) {
        super(nom);
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
    public ArrayList<Match> getAllMatches() {
        return this.affectationList;
    }
}