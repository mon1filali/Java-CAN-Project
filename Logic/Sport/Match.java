package Logic.Sport;

import Logic.Person.PersonSubClasses.Arbitre;
import Logic.Sport.TimeHandler.Creneau;
import Logic.MatchState;
import Logic.MatchImportance;

// !! don't forget isplaying and isempty


public class Match {
    protected static int compteur = 0;
    protected int codeMatch;
    protected Equipe equipeA;
    protected Equipe equipeB;
    protected Stade stade;
    protected Arbitre arbitre;
    protected Creneau creneau;
    protected MatchState statut;
    protected MatchImportance importance;

    public Match(){
    }
}
