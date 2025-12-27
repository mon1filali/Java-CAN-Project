package Logic.Sport.DateHandler;

import Logic.Exceptions.ConflitCalendrierException;
import Logic.Exceptions.MatchInvalideException;
import Logic.Interface.Controlable;
import Logic.Management.CompetitionManger;
import Logic.Sport.Match;

public class CalendrierCAN implements Controlable {
    public CalendrierCAN() {
    }

    // The class instantiation calling this, si the one handeling it
    public void verifierConflit(Match match) throws ConflitCalendrierException {
        if (!match.stade.estDisponible(match.creneau)) {
            throw new ConflitCalendrierException(
                    "This match you want to create, its creneau is not compatible with the Competition Calender");
        }

        for (int i = 0; i < CompetitionManger.allMatches.size(); i++) {
            if (CompetitionManger.allMatches.get(i).creneau.date.equals(match.creneau.date)
                    && CompetitionManger.allMatches.get(i).arbitre.getId() == match.arbitre.getId()) {
                throw new ConflitCalendrierException("The refree is not available is this date");
            }
        }
    };

    public void verifierEligibilite(Match match) throws MatchInvalideException{
        if(!match.equipeA.estDisponible(match) || !match.equipeB.estDisponible(match)){
            throw new MatchInvalideException(null);
        }
    };
}