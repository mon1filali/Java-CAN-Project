package Logic.Sport;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import Logic.Exceptions.ExceededNumberOfPlayersException;
import Logic.Exceptions.DonneeInvalideException;
import Logic.Interface.Abstract.EntiteSportive;
import Logic.Person.PersonSubClasses.Joueur;
import Logic.Sport.TimeHandler.Creneau;

public class Equipe extends EntiteSportive {
    /*
     * getAllMatches()
     * When did this team play
     */
    protected ArrayList<Joueur> players = new ArrayList<Joueur>(); // From what cited in the specifications document

    // We did like this because java wont let us put the cod od throwing the exeception since it force the super to be first thing
    private static String validateNom(String nom) throws DonneeInvalideException {
        if (nom == null || nom.isEmpty()) {
            throw new DonneeInvalideException("The name of the Team must be entered");
        }
        return nom;
    }

    public Equipe(String nom) throws DonneeInvalideException {
        super(validateNom(nom));
    }

    public void addPlayer(Joueur p) throws ExceededNumberOfPlayersException {
        if (players.size() <= 24) {
            players.add(p);
        } else {
            throw new ExceededNumberOfPlayersException("You already reached the limit of players in this team");
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

    // This is going to be used to check if a a team could play in the creanau that
    // is set for a match or not
    public boolean estDisponible(Creneau creneau) {
        for (Match m : affectationList) {
            if (m.creneau.isChevauchement(creneau)) {
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

    public String toString() {
        return "Team name: " + this.nom;
    }
}