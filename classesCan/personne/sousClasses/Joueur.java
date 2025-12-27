package classesCan.personne.sousClasses;
import classesCan.personne.Personne;
import classesCan.SoccerPosition;
public class Joueur extends Personne {


    protected SoccerPosition position;
    protected int numJoueur;

    public Joueur(String nom, String nationalité, SoccerPosition position, int numJoueur) {
        super(nom, nationalité);
        this.position = position;
        this.numJoueur = numJoueur;
       
    }



    @Override
    public String getPriorite() {
        return "Joueur";
    }

      public SoccerPosition playerPosition() {
        return this.position;
    }

    
}
