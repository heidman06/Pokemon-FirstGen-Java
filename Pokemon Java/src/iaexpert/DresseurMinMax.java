package iaexpert;

import dresseur.Dresseur;
import interfaces.IAttaque;
import interfaces.IPokemon;

public class DresseurMinMax extends Dresseur {
    private MinMax minMax;

    /**
     * Constructeur de DresseurMinMax avec en parametre un nom
     */
    public DresseurMinMax(String nom){
        super(nom);
        this.minMax = new MinMax(this);
    }

    /**
     * Renvoie le MinMax
     */
    public MinMax getMinMax(){
        return minMax;
    }

    /**
     * Renvoie une attaque choisit par minMax avec en parametre le pokemon attaquant et le pokemon defenseur
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
     * Renvoie le pokemon actif choisit par minMax
     */
    @Override
    public IPokemon choisitCombattantContre(IPokemon pok) {
        return minMax.choisitCombattantContre(pok);
    }
}
