package classesCan.personne;

import java.util.ArrayList;

public abstract class Personne {
    protected static int compteur = 1;
    protected int id;
    protected String nom;
    protected String nationalité ;
    ArrayList<Personne> personnes ;


    public Personne() {
        this.id = compteur++;
        this.nom = "";
        this.nationalité = "";
    }
    public Personne(String nom, String nationalité) {
        this.id = compteur++;
        this.nom = nom;
        this.nationalité = nationalité;
        this.personnes = new ArrayList<>();
    }

 public abstract String getPriorite();  //user role 



 public void ajouterPersonne(Personne p){
    personnes.add(p);
 }
 public void supprimerPersonne(Personne p){
    personnes.remove(p);
 }




 public void modifierPersonne( Personne p, String nom, String nationalité){

    personnes.set( personnes.indexOf(p), p);
if (nom != null){
    p.nom = nom;
 }
if (nationalité != null){
    p.nationalité = nationalité;

 }

   }
}