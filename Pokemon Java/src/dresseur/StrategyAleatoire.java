package dresseur;

import capacite.Capacite;
import interfaces.IAttaque;
import interfaces.ICapacite;
import interfaces.IPokemon;
import interfaces.IStrategy;
import pokedex.Pokedex;
import pokemon.Pokemon;

public class StrategyAleatoire implements IStrategy {

    private DresseurIA dresseur;
    private Pokemon pokeIA ;


    /**
     * Constructeur de StrategyAleatoire avec un DresseurIA en parametre
     * @param dresseur
     */
    public StrategyAleatoire(DresseurIA dresseur) {
        this.dresseur = dresseur;
    }


    /**
     * Renvoie un pokemon aleatoire du ranch
     * 
     */
    public Pokemon getRandomPokemon() {
        Pokemon pok = null;

        while (pok == null){
            int randomIndex = (int) (Math.random() * dresseur.getRanch().length);
            if (!dresseur.getPokemon(randomIndex).estEvanoui())
                pok = dresseur.getRanch()[randomIndex];
        }

        return pok;
    }


    /**
     * Renvoie une capacite aleatoire parmi les 4 d'un pokemon
     * 
     */
    public Capacite getRandomCapacite(ICapacite[] capacite) {

        Pokemon pok = getRandomPokemon();
        int randomIndex = (int) (Math.random() * 4);
        pok.apprendCapacites(capacite);
        return (Capacite) pok.getCapacitesApprises()[randomIndex];
    }

    /**
     * Renvoie un pokemon a envoye au combat aleatoirement
     * 
     */
    @Override
    public IPokemon choisitCombattant() {
        pokeIA = getRandomPokemon();
        return pokeIA;
    }
    
    /**
     * Renvoie un pokemon a envoye au combat aleatoirement avec un pokemon en parametre
     * 
     */
    @Override
    public IPokemon choisitCombattantContre(IPokemon pok) {

        return choisitCombattant();

    }

    /**
     * Renvoie une attaque choisit aleatoirement a utiliser contre le pokemon defenseur
     * 
     */
    @Override
    public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur) {

        int random = (int) (Math.random() * 2);

        Attaque attaque = new Attaque();

        if(random >= 1 || attaquant.estEvanoui()){
            pokeIA = (Pokemon) choisitCombattant();
            attaque.setPokemon(pokeIA);
            //System.out.println(getNom() + " choisi " + pokeIA.getNom());
        }
        else {
            attaque.setCap(retourneCapacite((Pokemon) attaquant));
        }

        return attaque;
    }

    /**
     * Enseigne des capacites a tous les pokemon du ranch
     */
    @Override
    public void initCapacitesRanch() {
        Pokedex pokedex = new Pokedex();
        pokedex.initialiseFromCsvMoves("listeCapacites.csv");
        for(int i = 0 ; i<6 ; i++){
            dresseur.getRanch()[i].apprendCapacites(pokedex.remplirTabDeCapacite());
        }
    }


    /**
     * Enseigne des capacites aleatoirement a un pokemon en parametre qu'il peut apprendre
     * 
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
