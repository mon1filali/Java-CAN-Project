package Logic.Interface;

import Logic.Exceptions.ConflitCalendrierException;
import Logic.Exceptions.MatchInvalideException;
import Logic.Sport.Match;

public interface Controlable {
    public void verifierConflit(Match match) throws ConflitCalendrierException;
    public void verifierEligibilite(Match match) throws MatchInvalideException;
}
