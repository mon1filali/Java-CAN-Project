package Logic.Sport;

import java.util.ArrayList;
import Logic.MatchState;
import Logic.Interface.Planifiable;
import Logic.Interface.Abstract.EntiteSportive;
import Logic.Sport.TimeHandler.Creneau;

public class Stade extends EntiteSportive implements Planifiable {
    protected String ville;
    protected int capacite;
    protected String categorie;

    public Stade(String nom, String ville, int capacite, String categorie) {
        super(nom);
        this.ville = ville;
        this.capacite = capacite;
        this.categorie = categorie;
    }

    // Checks if a crenau is available in order to put a match at that time, It still needs refactoring
    // can I host a match in this time
    public boolean estDisponible(Creneau matchCrenau) {
        boolean isDisponible = true;

        for(int i = 0; i < affectationList.size(); i++) {
            if(affectationList.get(i).creneau.date.equals(matchCrenau.date) && matchCrenau.debut.isAfter(affectationList.get(i).creneau.debut) && matchCrenau.debut.isBefore(affectationList.get(i).creneau.debut.plusMinutes(affectationList.get(i).creneau.dureeMinutes))) {
                if(matchCrenau.debut.equals(affectationList.get(i).creneau.debut) && matchCrenau.debut.equals(affectationList.get(i).creneau.debut.plusMinutes(affectationList.get(i).creneau.dureeMinutes))) {
                    isDisponible = false;
                }
            }
        }
        return isDisponible;
    };
    
    public void planifierMatch(Match match, Creneau matchCreneau) {
        match.setCreneau(matchCreneau);
    };

    public void annulerMatch(int codeMatch) {
        for(int i = 0; i < affectationList.size(); i++) {
            if(affectationList.get(i).codeMatch == codeMatch) {
                affectationList.get(i).statut = MatchState.ANNULATED;
            }
        }
    };

    // !!!!!!!!!!! to return !!!!!!!!!!!!!!!!!!!!!
    public boolean isEmpty() {
        return true;
    }

    // Return all the matches played in the stad
    public ArrayList<Match> getAllMatches() {
        return this.affectationList;
    }
}
