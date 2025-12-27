package classesCan.personne.sousClasses;

import classesCan.personne.Personne;

public class Organisateur extends Personne {

    public Organisateur (String nom, String nationalité){
        super(nom, nationalité);
    }
    
    @Override
    public String getPriorite(){
        return "Organisateur";
    }
}
