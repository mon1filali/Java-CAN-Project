package Logic.Interface;

import Logic.Sport.Match;
import Logic.Sport.TimeHandler.Creneau;

public interface Planifiable {
    public boolean estDisponible(Creneau matchCrenau);
    public void planifierMatch(Match match, Creneau matchCreneau);
    public void annulerMatch(int codeMatch);
}
