package dresseurexpert;

import dresseur.Dresseur;
import dresseur.DresseurIA;
import dresseur.StrategyAleatoire;
import interfaces.IAttaque;
import interfaces.IPokemon;
import pokemon.Pokemon;

import java.util.ArrayList;

public class DresseurExpert extends Dresseur  {
    ArrayList<String> nomDresseur = new ArrayList<>();
    private Pokemon pokeIA;
    private final StrategyExperte strat ;

    /**
     *Constructeur de DresseurExpert avec en parametre un nom est une strategie
     *
     */
    public DresseurExpert(String nom, StrategyExperte strat) {
        super(nom);
        this.strat = new StrategyExperte(this);
        this.setNom("IAExperte");
    }


	/**
	 * Renvoie un nom aleatoire pour l'IA
	 * 
	 */
    public String generateRandomName() {

        nomDresseur.add("Pablo");
        nomDresseur.add("Guillaume");

        int randomIndex = (int) (Math.random() * nomDresseur.size());

        return nomDresseur.get(randomIndex);
    }


    /**
     * Renvoie une attaque avec en parametre le pokemon attaquant et le pokemon defenseur
     */
    @Override
    public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur) {
        return strat.choisitAttaque(attaquant,defenseur);
    }
}
