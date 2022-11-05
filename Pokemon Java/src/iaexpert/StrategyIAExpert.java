package iaexpert;

import capacite.Capacite;
import dresseur.Attaque;
import dresseur.Dresseur;
import dresseur.DresseurIA;
import interfaces.IPokemon;
import pokemon.Pokemon;

import java.util.ArrayList;
import java.util.Arrays;

public class StrategyIAExpert  {

    //d1 --> IA
    DresseurExpert d1;
    //d2 --> dresseur
    Dresseur d2;


    int totalValPok;
    int valP;

    ArrayList<Attaque>coupsPossibles = new ArrayList<>();
/*
    public StrategyIAExpert(DresseurExpert d1, Dresseur d2) {
        super(d1.getNom(), strat);
        this.d1 = d1;
        this.d2 = d2;
    }
*/

    /**
     * Renvoie true si les stats ou la puissance du ranch du DresseurExpert est supérieur au dresseur adverse
     */
    public boolean dGagnant(Dresseur d) {
        return pokemonStatsCalculRanch(d1) > pokemonStatsCalculRanch(d2) || calculPuissanceCapRanch(d1) > calculPuissanceCapRanch(d2);
    }

    /**
     * Renvoie le quotient de la valeur totale du dresseur expert et de la valeur totale des 2 dresseurs
     */
    public double etatDuJeu() {
        return (double) valeurTotalDresseur(d1) / (valeurTotalDresseur(d1) + valeurTotalDresseur(d2));
    }





    /**
     * Renvoie true si la somme de la puissance et de la precision des capacites du pokemon
     *  du dresseur adverse sont superieur au pokemon actif avec en parametre leurs pokemon
     */
    protected boolean attaque(Pokemon pokAtt, Pokemon pokDef) {
        int dmgPokD1 = calculCapPokemon(pokAtt);
        int dmgPokD2 = calculCapPokemon(pokDef);

        return dmgPokD1 < dmgPokD2;
    }

    /**
     * Renvoie true si la somme de la puissance et de la precision des capacites du pokemon defenseur est superieur a l'attaquant
     */
    private boolean changePoke(Pokemon pokAtt, Pokemon pokDef) {
        //True if pokAtt has a lower capacity power than pokDef
        return calculCapPokemon(pokAtt) < calculCapPokemon(pokDef);
    }

    /**
     * Renvoie la somme de toutes les stats des pokemon et la puissance et la precision des capacites du pokemon
     * en parametre
     */
    public int valeurTotalDresseur(Dresseur D) {
        return pokemonStatsCalculRanch(D) + calculPuissanceCapRanch(D);
    }


    /**
     * Renvoie la somme de toutes les stats des pokemon du dresseur en parametre
     */
    public int pokemonStatsCalculRanch(Dresseur D) {
        totalValPok = 0;
        for (int i = 0; i < D.getRanch().length; i++) {
            valP = (D.getPokemon(i).getStat().getForce() + D.getPokemon(i).getStat().getDefense() +
                    D.getPokemon(i).getStat().getPV() + D.getPokemon(i).getStat().getVitesse() + D.getPokemon(i).getStat().getSpecial());
            totalValPok += valP;
        }
        System.out.println(totalValPok);
        return totalValPok;
    }

    /**
     * Renvoie la somme de toutes les stats du pokemon en parametre
     */
    public int pokemonStatCalcul(Pokemon pok) {

        valP = pok.getStat().getForce() + pok.getStat().getDefense() +
                pok.getStat().getPV() + pok.getStat().getVitesse() + pok.getStat().getSpecial();


        return valP;
    }


    /**
     * Renvoie la somme de la puissance et de la precision des capacites d'un pokemon en parametre
     */
    public int calculCapPokemon(Pokemon poke) {
        int total = 0;
        for (int i = 0; i < poke.nbCapacitesApprises(); i++) {
            Capacite capa = (Capacite) poke.getCapacitesApprises()[i];
            total += capa.getPuissance() + capa.getPrecision();
        }
        return total;
    }

    /**
     * Renvoie la somme de la puissance et de la precision des capacites du ranch du dresseur en parametre
     */
    public int calculPuissanceCapRanch(Dresseur D) {
        int total = 0;
        for (int i = 0; i < 6; i++) {
            total += calculCapPokemon(D.getRanch()[i]);
        }
        return total;
    }


    /**
     * Affiche les ranch des 2 dresseurs
     */
    public void afficheRanch() {
        System.out.println("Ranch : " + Arrays.toString(d1.getRanch()));
        System.out.println("Ranch : " + Arrays.toString(d2.getRanch()));
    }


    /**
     * Renvoie le pokemon qui recevra moins de 13 points de degats s'il recoit l'attaque mise en parametre
     */
    public IPokemon choisitPokemonPrudent(Pokemon attaq) {

        int dmg = 0;

        for (int j = 0; j < attaq.getCapacitesApprises().length; j++) {
            for (int i = 0; i < d1.getRanch().length; i++) {
                dmg = attaq.getCapacitesApprises()[j].calculeDommage(attaq, d1.getPokemonD());
                if (dmg <= 13) {
                    d1.setPokemonD((Pokemon) d1.getPokemon(i));
                }
            }
        }
        return d1.getPokemonD();
    }
}
