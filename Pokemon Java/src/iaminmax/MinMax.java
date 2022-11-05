package iaminmax;

import combat.Combat;
import dresseur.Dresseur;
import interfaces.IAttaque;
import interfaces.IPokemon;
import interfaces.IStrategy;
import pokemon.Pokemon;

public class MinMax implements IStrategy {
    private Dresseur dresseur;
    private Combat combat;
    private final int PROFONDEUR = 9;

    public MinMax(Dresseur d) { dresseur = d; }

    /**
     * Renvoie la meilleure evaluation 
     */
    public static <Attaque> double minimax(Combat combat, Attaque attaque, boolean maximizing, Dresseur dresseurOriginal, int maxDepth, Pokemon pokemon) {
        //Condition d'arret  : On verifie que le combat est fini ou profendeur maximale atteinte , on retourne une valeur du futur possible
        if (combat.etatduJeu() || maxDepth == 0) {
            return combat.fonctionEvaluation(dresseurOriginal);
        }
        //MiniMax
        if (maximizing) {
            //On dit que x est initialisé a la meilleure des evaluation pour max , c'est a dire x = -infini;
            double x = Double.NEGATIVE_INFINITY;

            //On parcours les etats suceseurs = les coups qui peuvent etre realisés
            for (dresseur.Attaque atk : combat.getListeCoups(dresseurOriginal, pokemon)) {
                //J'invoque recursivement minimax qui est a la fois maximisateur et minimisateur dans la meme fonction, je fait appel a minimax a partir du prochain coup succeseur c'est a dire la prochaine branche de minmax
                //Si je sors de maximisateur je rentre dans minimisateur c'est a dire de maximizing va devenir true;
                double resultat = minimax(combat, atk, false, dresseurOriginal, maxDepth - 1, pokemon); //Je rentre dans une nouvelle etape d'une branche donc j'enleve -1 a la profondeur
                //Je retourne une valeur de x qui est le maximum entre la valeur de x original et le resultat de la recharche dans l'arbre
                x = Math.max(resultat, (x));
            }
            return x;
        } else {
            //On dit que x est initialisé a la meilleure des evaluation pour min , c'est a dire x = +infini;
            double x = Double.POSITIVE_INFINITY;
            //On parcours les etats suceseurs = les coups qui peuvent etre realisés
            for (dresseur.Attaque atk : combat.getListeCoups(dresseurOriginal, pokemon)) {
                //J'invoque recursivement minimax qui est a la fois maximisateur et minimisateur dans la meme fonction, je fait appel a minimax a partir du prochain coup succeseur c'est a dire la prochaine branche de minmax
                //Si je sors du minimisateur je rentre dans maximisateur c'est a dire de maximizing va devenir true;
                double resultat = minimax(combat, atk, true, dresseurOriginal, maxDepth - 1, pokemon); //Je rentre dans une nouvelle etape d'une branche donc j'enleve -1 a la profondeur
                //Je retourne une valeur de x qui est le minimum entre la valeur de x original et le resultat de la recharche dans l'arbre
                x = Math.min(resultat, (x));
            }
            //La premiere condition est l'etat terminale
            //SINON j'alterne entre min max
            return x;
        }
    }

    /**
     * Renvoie le meilleur coup
     */
    //Meilleur coup represente celui de l'adversaire
    public static <Attaque> Attaque meilleurCoup(Combat combat, int maxDepth, Dresseur dresseur, Pokemon pokemon) {
        //Meilleur coup initial
        double bestValue = Double.POSITIVE_INFINITY;
        //On a null pour le moment car on le connait pas
        Attaque bestCoup = null;
        for (dresseur.Attaque attaque : combat.getListeCoups(dresseur, pokemon)) {
            //Je ne maximize pas car adversaire
            double resultat = minimax(combat, attaque, false, dresseur, maxDepth, pokemon);
            if (bestValue > resultat) {
                bestValue = resultat;
                bestCoup = (Attaque) attaque;
            }
        }
        return bestCoup;
    }

    /**
     * Renvoie un pokemon actif aleatoirement
     */
    public IPokemon choisitCombattant(){
        Pokemon pok = null;

        while (pok == null){
            int randomIndex = (int) (Math.random() * dresseur.getRanch().length);
            if (!dresseur.getPokemon(randomIndex).estEvanoui())
                pok = dresseur.getRanch()[randomIndex];
        }

        return pok;
    }

    /**
     * Renvoie le pokemon qui recevra moins de 13 points de degats avec en parametre le pokemon et le dresseur
     */
    public IPokemon choisitPokemonPrudent(Pokemon p, Dresseur d) {
        int dmg = 0;

        for (int j = 0; j < p.getCapacitesApprises().length; j++) {
            for (int i = 0; i < d.getRanch().length; i++) {
                dmg = p.getCapacitesApprises()[j].calculeDommage(p, d.getPokemonD());
                if (dmg <= 13) {
                    d.setPokemonD((Pokemon) d.getPokemon(i));
                }
            }
        }

        return d.getPokemonD();
    }

    /**
     * Set le combat
     */
    public void setCombat(Combat c){combat = c;}

    /**
     * Renvoie un pokemon actif s'il inflige suffisament de degat avec en parametre un pokemon
     */
    public IPokemon choisitCombattantContre(IPokemon pok){
        return choisitPokemonPrudent((Pokemon) pok, dresseur);
    }
    
    /**
     * Renvoie la meilleure attaque possible avec en parametre un pokemon attaquant et un defenseur
     */
    public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur){
        return meilleurCoup(combat, PROFONDEUR, dresseur, (Pokemon) defenseur);
    }
    public void initCapacitesRanch(){}

    /*
    private Dresseur dresseur;
    private Combat combat;
    private final int PROFONDEUR = 9;

    public MinMax(Dresseur d) { dresseur = d; }

    public static <Attaque> double minimax(Combat combat, Attaque attaque, boolean maximizing, Dresseur dresseurOriginal, int maxDepth, Pokemon pokemon) {
        //Condition d'arret  : On verifie que le combat est fini ou profendeur maximale atteinte , on retourne une valeur du futur possible
        if (combat.etatduJeu() || maxDepth == 0) {
            return combat.fonctionEvaluation(dresseurOriginal);
        }
        //MiniMax
        if (maximizing) {
            //On dit que x est initialisé a la meilleure des evaluation pour max , c'est a dire x = -infini;
            double x = Double.NEGATIVE_INFINITY;

            //On parcours les etats suceseurs = les coups qui peuvent etre realisés
            for (dresseur.Attaque atk : combat.getListeCoups(dresseurOriginal, pokemon)) {
                //J'invoque recursivement minimax qui est a la fois maximisateur et minimisateur dans la meme fonction, je fait appel a minimax a partir du prochain coup succeseur c'est a dire la prochaine branche de minmax
                //Si je sors de maximisateur je rentre dans minimisateur c'est a dire de maximizing va devenir true;
                double resultat = minimax(combat, atk, false, dresseurOriginal, maxDepth - 1, pokemon); //Je rentre dans une nouvelle etape d'une branche donc j'enleve -1 a la profondeur
                //Je retourne une valeur de x qui est le maximum entre la valeur de x original et le resultat de la recharche dans l'arbre
                x = Math.max(resultat, (x));
            }
            return x;
        } else {
            //On dit que x est initialisé a la meilleure des evaluation pour min , c'est a dire x = +infini;
            double x = Double.POSITIVE_INFINITY;
            //On parcours les etats suceseurs = les coups qui peuvent etre realisés
            for (dresseur.Attaque atk : combat.getListeCoups(dresseurOriginal, pokemon)) {
                //J'invoque recursivement minimax qui est a la fois maximisateur et minimisateur dans la meme fonction, je fait appel a minimax a partir du prochain coup succeseur c'est a dire la prochaine branche de minmax
                //Si je sors du minimisateur je rentre dans maximisateur c'est a dire de maximizing va devenir true;
                double resultat = minimax(combat, atk, true, dresseurOriginal, maxDepth - 1, pokemon); //Je rentre dans une nouvelle etape d'une branche donc j'enleve -1 a la profondeur
                //Je retourne une valeur de x qui est le minimum entre la valeur de x original et le resultat de la recharche dans l'arbre
                x = Math.min(resultat, (x));
            }
            //La premiere condition est l'etat terminale
            //SINON j'alterne entre min max
            return x;
        }
    }

    //Meilleur coup represente celui de l'adversaire
    public static <Attaque> Attaque meilleurCoup(Combat combat, int maxDepth, Dresseur dresseur, Pokemon pokemon) {
        //Meilleur coup initial
        double bestValue = Double.NEGATIVE_INFINITY;
        //On a null pour le moment car on le connait pas
        Attaque bestCoup = null;
        for (dresseur.Attaque attaque : combat.getListeCoups(dresseur, pokemon)) {
            //Je ne maximize pas car adversaire
            double resultat = minimax(combat, attaque, false, dresseur, maxDepth, pokemon);
            if (resultat > bestValue) {
                bestValue = resultat;
                bestCoup = (Attaque) attaque;
            }
        }
        return bestCoup;
    }

    public IPokemon choisitCombattant(){
        Pokemon pok = null;

        while (pok == null){
            int randomIndex = (int) (Math.random() * dresseur.getRanch().length);
            if (!dresseur.getPokemon(randomIndex).estEvanoui())
                pok = dresseur.getRanch()[randomIndex];
        }

        return pok;
    }

    public IPokemon choisitPokemonPrudent(Pokemon p, Dresseur d) {
        int dmg = 0;

        for (int j = 0; j < p.getCapacitesApprises().length; j++) {
            for (int i = 0; i < d.getRanch().length; i++) {
                dmg = p.getCapacitesApprises()[j].calculeDommage(p, d.getPokemonD());
                if (dmg <= 13) {
                    d.setPokemonD((Pokemon) d.getPokemon(i));
                }
            }
        }

        return d.getPokemonD();
    }

    public void setCombat(Combat c){combat = c;}

    public IPokemon choisitCombattantContre(IPokemon pok){
       return meilleurCoup(combat, PROFONDEUR, dresseur, combat.getPokemon1());
    }
    public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur){
        return meilleurCoup(combat, PROFONDEUR, dresseur, combat.getPokemon1());
    }
    public void initCapacitesRanch(){}*/
}
