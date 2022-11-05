package dresseurexpert;

import capacite.Capacite;
import dresseur.Attaque;
import interfaces.IAttaque;
import interfaces.ICapacite;
import interfaces.IPokemon;
import interfaces.IStrategy;
import pokedex.Pokedex;
import pokemon.Pokemon;
import type.Type;

import java.io.IOException;

public class StrategyExperte implements IStrategy {

    private final DresseurExpert dresseur;
    private Pokemon pokeIA;


    /**
     * Constructeur de StrategyExperte avec un DresseurExpert en parametre
     */
    public StrategyExperte(DresseurExpert dresseur) {
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
     * Renvoie un pokemon a envoye au combat aleatoirement
     * 
     */
    @Override
    public IPokemon choisitCombattant() {
        pokeIA = getRandomPokemon();
        return pokeIA;
    }



    /**
     * Renvoie un pokemon a envoe au combat aleatoirement avec le pokemon adverse en parametre
     * 
     */
    @Override
    public IPokemon choisitCombattantContre(IPokemon pokemonAdverse) {
        return choisitCombattant();
    }

    /**
     * Renvoie la meilleure attaque possible avec en parametre le pokemon attaquant et defenseur
     */
    @Override
    public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur) {

        boolean hpLower = HP(attaquant,defenseur); 
        boolean badCapacities = false;
        try {
            badCapacities = badCapacities(attaquant,defenseur);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Attaque attaque = new Attaque();
        if (badCapacities || attaquant.estEvanoui()) {
            pokeIA = (Pokemon) choisitCombattantContre(defenseur);
            attaque.setPokemon(pokeIA);

        } else {
            try {
                attaque.setCap(retourneCapacite(attaquant, defenseur));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
        for (int i = 0; i < 6; i++) {
            dresseur.getRanch()[i].apprendCapacites(pokedex.remplirTabDeCapacite());
        }
    }

    /**
     * Renvoie true si les PV du pokemon actuel de l'IA est plus bas que le pokemon actuel adverse
     */
    private boolean HP(IPokemon attaquant, IPokemon defenseur) {
        return defenseur.getPourcentagePV() < attaquant.getPourcentagePV();
    }

    /**
     * Verifie l'efficacite du pokemon actuel avec le defenseur
     * Renvoie truce si l'efficacite est inferieur a 1
     *
     */
    private boolean badCapacities(IPokemon attaquant, IPokemon defenseur) throws IOException {

        Pokedex pokedex = new Pokedex();
        pokedex.initialiseFromCsvMoves("listeCapacites.csv");
        pokedex.initialiseFromCsvEfficacity("efficacites.csv");

        int nCapacities = 0;

        for (int i = 0; i < attaquant.getCapacitesApprises().length; i++) {
            if (attaquant.getCapacitesApprises()[i] != null) {
                nCapacities++;
            }
        }

        if (nCapacities == 0) {
            Capacite cap = new Capacite(pokedex.getCapacite("struggle"));
            return pokedex.getEfficacite(cap.getType(), defenseur.getEspece().getTypes()[0]) < 1
                    || pokedex.getEfficacite(cap.getType(), defenseur.getEspece().getTypes()[1]) < 1;
        } else {
            boolean efective = false;
            int aux = 0;

            while (!efective && aux < attaquant.getCapacitesApprises().length) {

                efective = pokedex.getEfficacite(attaquant.getCapacitesApprises()[aux].getType(),
                        defenseur.getEspece().getTypes()[0]) < 1;

                if (defenseur.getEspece().getTypes()[1] != null && !efective) {
                    efective = pokedex.getEfficacite(attaquant.getCapacitesApprises()[aux].getType(),
                            defenseur.getEspece().getTypes()[1]) < 1;
                }
                aux++;
            }

            return efective;
        }
    }


    /**
     * Renvoie la meilleure capacite en fonction du type du pokemon adverse
     * 
     */
    public Capacite retourneCapacite(IPokemon attaquant, IPokemon defenseur) throws IOException {
        Pokedex pok = new Pokedex();
        pok.initialiseFromCsvEfficacity("efficacites.csv");
        pok.initialiseFromCsvMoves("listeCapacites.csv");

        double eficacit = 0; 
        int aux = 0;
        IAttaque choisit = null;

        Type[] tipDefenseur = (Type[]) defenseur.getEspece().getTypes(); //Tableau avec types de défenseurs

        while (eficacit <= 1 && aux < attaquant.getCapacitesApprises().length) {
            eficacit = pok.getEfficacite(attaquant.getCapacitesApprises()[aux].getType(), tipDefenseur[0]);

            //Si l'efficacité est supérieure à un, l'attaque est mémorisée de préférence.
            if (eficacit > 1) {
                System.out.println("super efficace");
                choisit = attaquant.getCapacitesApprises()[aux];
            }
            if (tipDefenseur[1] != null && eficacit <= 1) {
                if (eficacit > 1) {
                    System.out.println("super efficace");
                    choisit = attaquant.getCapacitesApprises()[aux];
                }
            }
            aux++;
        }

        //S'il n'y a pas d'attaques super efficaces, choisissez une attaque aléatoire
        if (eficacit <= 1) {
            System.out.println("neutre ou inefficace");
            int randomIndex = (int) Math.round(Math.random() * 3);
            choisit = attaquant.getCapacitesApprises()[randomIndex];
        }
        return (Capacite) choisit;
    }


//    private Capacite retourneCapacite(Pokemon pokemon) {
//        int nbDeCapacites = 0;
//        Capacite cap = null;
//        Pokedex pokedex = new Pokedex();
//        pokedex.initialiseFromCsvMoves("listeCapacites.csv");
//
//        for (int i = 0; i < pokemon.getCapacitesApprises().length; i++) {
//            if (pokemon.getCapacitesApprises()[i] != null) {
//                nbDeCapacites++;
//            }
//        }
//
//        if (nbDeCapacites <= 0) {
//            cap = new Capacite(pokedex.getCapacite("struggle"));
//        } else {
//            while (cap == null) {
//                int randomIndex = (int) (Math.random() * ((Pokemon) pokemon).nbCapacitesApprises());
//
//                if (pokemon.getCapacitesApprises()[randomIndex].getPP() > 0) {
//                    cap = pokemon.getCapacite()[randomIndex];
//                }
//            }
//        }
//
//        return cap;
//    }
}
