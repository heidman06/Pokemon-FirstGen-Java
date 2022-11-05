package iaminmax;

import dresseur.Dresseur;
import interfaces.IAttaque;
import interfaces.IPokemon;

public class DresseurMinMax extends Dresseur {
    private MinMax minMax;

    /**
     * Constructeur DresseurMinMax avec en parametre en nom
     */
    public DresseurMinMax(String nom){
        super(nom);
        this.minMax = new MinMax(this);
    }

    /**
     * Renvoie MinMax
     */
    public MinMax getMinMax(){
        return minMax;
    }

    /**
     * Renvoie une attaque choisit par MinMax avec en parametre un pokemon attaquant et defenseur
     */
    @Override
    public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur) {
        return minMax.choisitAttaque(attaquant, defenseur);
    }

    /**
     * Renvoie le pokemon actif
     */
    @Override
    public IPokemon choisitCombattant() {
        return super.choisitCombattant();
    }

    /**
     * Renvoie le pokemon actif choisit avec en parametre le pokemon adverse
     */
    @Override
    public IPokemon choisitCombattantContre(IPokemon pok) {
        return minMax.choisitCombattantContre(pok);
    }
}
