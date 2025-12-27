package classesCan.personne.sousClasses;
import classesCan.personne.Personne;
public class Arbitre extends Personne {

    public Arbitre(String nom, String nationalité) {
       super(nom, nationalité);
    }


    @Override
    public String getPriorite() {
        return "Arbitre";
    }

    
    
}
