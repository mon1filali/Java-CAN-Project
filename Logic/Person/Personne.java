package Logic.Person;

import java.util.ArrayList;

public abstract class Personne {
    protected static int compteur = 1;
    protected int id;
    protected String nom;
    protected String nationalite;
    protected ArrayList<Personne> personnes;

    public Personne(String nom, String nationalite){
        this.nom = nom;
        this.nationalite = nationalite;
        id = compteur;
        compteur++;
    }

    public abstract String getPriorite(); // user Role

    public void ajouterPersonne(Personne p) {
        personnes.add(p);
    };

    public void supprimerPersonne(Personne p) {
        personnes.remove(p);
    };
    /* public void editerPersonne(int id, String newNom) {

    };

    public void editerPersonne(int id, String newNationalite) {
    }; */

    // !!!!!!!! to return !!!!!!!!!!!!!!!!!!!!!
    public void editerPersonne(Personne p, String newNom, String newNationalite) {
        int personneIndex = personnes.indexOf(p); // Return the index of the person in the arraylist
        if(newNom != ""){
            p.nom = newNom;
        }

        if(newNationalite != ""){
            p.nationalite = newNationalite;
        }
        personnes.set(personneIndex, p);
    };
}