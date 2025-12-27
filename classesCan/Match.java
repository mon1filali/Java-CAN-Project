package classesCan;

import classesCan.MatchImportance;
import classesCan.MatchState;
import classesCan.Creneau;
import classesCan.entiteSport.EntiteSportive;
import classesCan.personne.sousClasses.Arbitre;


public class Match {

    protected static int compteur = 0;
    protected int id;
    protected  EntiteSportive equipe1;
    protected EntiteSportive equipe2;
    protected EntiteSportive stade;
    protected Arbitre arbitre;
    protected Creneau creneau;
    protected MatchState statut;
    protected MatchImportance importance;

    public Match() {
        compteur++;
        this.id = compteur;
    }

    public Match(EntiteSportive equipe1, EntiteSportive equipe2, EntiteSportive stade, Arbitre arbitre,
            Creneau creneau, MatchState statut, MatchImportance importance) {
        compteur++;
        this.id = compteur;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.stade = stade;
        this.arbitre = arbitre;
        this.creneau = creneau;
        this.statut = statut;
        this.importance = importance;
    }




    
}
