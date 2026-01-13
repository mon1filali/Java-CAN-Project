package Logic.Sport.DateHandler;

import Logic.Exceptions.ConflitCalendrierException;
import Logic.Exceptions.MatchInvalideException;
import Logic.Interface.Controlable;
import Logic.Management.CompetitionManager;
import Logic.Sport.Match;

public class CalendrierCAN implements Controlable {
    public CompetitionManager manager;
    private int conflitCount = 0;

    public CalendrierCAN(CompetitionManager manager) {
        this.manager = manager;
    }

    // The class instantiation calling this, is the one handeling it
    public void verifierConflit(Match match) throws ConflitCalendrierException {
        if (!match.stade.estDisponible(match.creneau)) {
            conflitCount++;
            throw new ConflitCalendrierException(
                    "This match you want to create, its creneau is not compatible with the Competition Calender");
        }

        if(!manager.checkRefereeIsAvailable(match.arbitre.getNom())) {
            conflitCount++;
            throw new ConflitCalendrierException("The refree is not available in this date");
        }

        /* for (int i = 0; i < manager.allMatches.size(); i++) {
            if (manager.allMatches.get(i).creneau.date.equals(match.creneau.date)
                    && manager.allMatches.get(i).arbitre.getId() == match.arbitre.getId()) {
                throw new ConflitCalendrierException("The refree is not available in this date");
            }
        } */
    };

    public void verifierEligibilite(Match match) throws MatchInvalideException{
        if(!match.equipeA.estDisponible(match.creneau) || !match.equipeB.estDisponible(match.creneau)){
            throw new MatchInvalideException(null);
        }
    };

    public int getConflitCount() {
        return conflitCount;
    }
}