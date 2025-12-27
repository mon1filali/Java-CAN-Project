package Logic.Sport;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import Logic.Interface.Abstract.EntiteSportive;
import Logic.Person.PersonSubClasses.Joueur;
import Logic.Sport.TimeHandler.Creneau;

public class Equipe extends EntiteSportive {
    /*
     * getAllMatches()
     * When did this team play
     */
    protected ArrayList<Joueur> players = new ArrayList<Joueur>(); // From what cited in the specifications document

    public Equipe(String nom) {
        super(nom);
    }

    public void addPlayer(Joueur p) {
        if (players.size() <= 24) {
            players.add(p);
        } else {
            System.out.println("Vous avez passer le limite des joueurs");
        }
    }

    // Check if the user is currently playing
    public boolean isPlaying() {
        boolean isPlay = false;
        for (int i = 0; i < affectationList.size(); i++) {
            if (LocalDate.now().equals(affectationList.get(i).creneau.date)) {
                if (LocalTime.now().isAfter(affectationList.get(i).creneau.debut)
                        && LocalTime.now().isBefore(affectationList.get(i).creneau.debut
                                .plusMinutes(affectationList.get(i).creneau.dureeMinutes))) {
                    isPlay = true;
                }
            }
        }
        return isPlay;
    }

    // This is going to be used to check if a a team could play in the creanau that is set foor a match or not
    public boolean estDisponible(Match match) {
        for (Match m : affectationList) {
            if (m.creneau.isChevauchement(match)) {
                return false;
            }
        }
        return true;
    }

    // Return all the matches played by the team
    public ArrayList<Match> getAllMatches() {
        return this.affectationList;
    }

    public void addMatch(Match match) {
        this.affectationList.add(match);
    }
}