package dresseur;

import capacite.Capacite;
import interfaces.IAttaque;
import interfaces.IEchange;
import interfaces.IPokemon;
import pokemon.Pokemon;

public class Attaque implements IAttaque, IEchange {

    private Pokemon pokemon = null;
    private Capacite cap = null;
    private DresseurHumain dresseurHumain;


    /**
     * Constructeur vide de Attaque
     */
    public Attaque() {}

    /**
     * Renvoie les degats que le pokemon lanceur va infliger au Pokemon receveur
     * 
     */
    @Override
    public int calculeDommage(IPokemon lanceur, IPokemon receveur) {
        return 0;
    }

    /**
     * diminue les pp de la capacite utilisee de 1
     */
    @Override
    public void utilise() {
        cap.utilise();
    }

    /**
     * Set le pokemon en parametre et le convertit en type Pokemon
     * 
     */
    @Override
    public void setPokemon(IPokemon pok) {
        pokemon = (Pokemon) pok;
    }

    /**
     * Renvoie le Pokemon a echange
     * 
     */
    @Override
    public IPokemon echangeCombattant() {
        return pokemon;
    }

    /**
     * Renvoie la capacite lie a l'attaque
     * 
     */
    public Capacite getCap() {
        return cap;
    }

    /**
     * Set la capacite en parametre en tant que capacite lie a l'attaque
     * 
     */
    public void setCap(Capacite cap) {
        this.cap = cap;
    }

    /**
     * toString de Attaque
     */
    @Override
    public String toString() {
        return "Attaque{" +
                "pokemon=" + pokemon +
                ", cap=" + cap +
                '}';
    }

    /**
     * Renvoie true si l'attaque est une capacite
     * 
     */
    public boolean estUneCapacite(){
        boolean resultat = cap != null;
        return resultat;
    }



}
