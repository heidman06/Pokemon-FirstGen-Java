package iaexpert;

import capacite.Capacite;
import dresseur.Attaque;
import dresseur.Dresseur;
import interfaces.IAttaque;
import interfaces.IPokemon;
import interfaces.IType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import pokedex.Pokedex;
import pokemon.Pokemon;

import static java.lang.constant.ConstantDescs.NULL;


public class DresseurExpert extends Dresseur{

    private int X;
    private int val;
    private String nom;
    private StrategyIAExpert strategy ;
    Dresseur A ;
    Dresseur B;


    ArrayList<String> nomDresseur = new ArrayList<>();

    /**
     *Constructeur de DresseurExpert avec en parametre un nom est une strategie
     *
     */
    public DresseurExpert(String nom , StrategyIAExpert strategy) {
        super(nom);
        this.strategy = strategy;
        setNom(generateRandomName());
    }



    /**
     * Renvoie une attaque capacite avec en parametre le pokemon attaquant et defenseur
     */
    public IAttaque attaqueCap (Pokemon attaquant, Pokemon deff){
        Attaque attaque = new Attaque();
        attaque.setCap(retourneCapacite(attaquant));

        return attaque;
    }

    /**
     * Renvoie une attaque echange avec en parametre le pokemon attaquant
     */
    public IAttaque attaqueEchange (Pokemon attaquant){
        Attaque attaque = new Attaque();
       attaquant = (Pokemon) choisitCombattant();
       attaque.setPokemon(attaquant);
       return attaque;
    }


    /**
     * 
     */
    @Override
    public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur) {

        return null;
    }
	/**
	 * Renvoie un nom aleatoire pour l'IA
	 * 
	 */
    public String generateRandomName() {

        nomDresseur.add("Pablo");
        nomDresseur.add("Guillaume");
        nomDresseur.add("Zarena");
        nomDresseur.add("Andrea");
        nomDresseur.add("Pedro");

        int randomIndex = (int) (Math.random() * nomDresseur.size());

        return nomDresseur.get(randomIndex);
    }

    /**
     * Renvoie une capacite si elle existe, sinon renvoie struggle
     */
    private Capacite retourneCapacite(Pokemon pokemon){
        int nbDeCapacites = 0;
        Capacite cap = null;
        Pokedex pokedex = new Pokedex();
        pokedex.initialiseFromCsvMoves("listeCapacites.csv");

        for (int i = 0; i < pokemon.getCapacitesApprises().length; i++){
            if (pokemon.getCapacitesApprises()[i] != null){
                nbDeCapacites++;
            }
        }

        if (nbDeCapacites <= 0){
            cap = new Capacite(pokedex.getCapacite("struggle"));
        }else{
            while (cap == null){
                int randomIndex = (int) (Math.random() *  ((Pokemon) pokemon).nbCapacitesApprises());

                if (pokemon.getCapacitesApprises()[randomIndex].getPP() > 0){
                    cap = pokemon.getCapacite()[randomIndex];
                }
            }
        }

        return cap;
    }

}