package tour;


import dresseur.Attaque;
import interfaces.*;
import pokemon.Pokemon;

public class Tours  implements ITour {
    Pokemon pokemon1;
    Pokemon pokemon2;

    Attaque attaque1;
    Attaque attaque2;

    /**
     * Constructeur vide de Tours
     */
    public Tours(){

    }

    /**
     * Constructeur de Tours avec en parametre les 2 pokemons et leurs attaques
     */
    public Tours(IPokemon pok1, IAttaque atk1, IPokemon pok2, IAttaque atk2) {
        attaque1 = (Attaque) atk1;
        attaque2 = (Attaque) atk2;

        pokemon1 = (Pokemon) pok1;
        pokemon2 = (Pokemon) pok2;
    }

    /**
     * Commence un tour selon quel pokemon est le plus rapide
     */
    @Override
    public void commence() {
        // Verifie quel pokemon attaque en premier
        if (pokemon1PlusRapideQuePokemon2()){
            tourDresseur(pokemon1, pokemon2, attaque1);
            tourDresseur(pokemon2, pokemon1, attaque2);
        }else{
            tourDresseur(pokemon2, pokemon1, attaque2);
            tourDresseur(pokemon1, pokemon2, attaque1);
        }
    }


    /**
     * Effectue l'action de l'un des 2 dresseurs
     */
    private void tourDresseur(Pokemon attaquant, Pokemon defenseur, Attaque attaque){
        // Si le pokemon n'est pas evanoui et que l'attaque est une capacite
        if (!attaquant.estEvanoui() && attaque.estUneCapacite()){
            if (attaqueTouche(attaque)){
                defenseur.subitAttaqueDe(attaquant, attaque);
                System.out.println(defenseur.getNom() + " s'est fait attaque par " + attaquant.getNom() + " avec l'attaque " + attaque.getCap().getNom());
                if (defenseur.estEvanoui()){
                    System.out.println(defenseur.getNom() + " est KO");
                }
            }else {
                System.out.println("L'attaque de " + attaquant.getNom() + " n'a pas touche l'adversaire");
            }
        }
    }

    /**
     * Renvoie true si le pokemon 1 est plus rapide que le pokemon 2
     * 
     */
    private boolean pokemon1PlusRapideQuePokemon2() {
        return pokemon1.getStat().getVitesse() >= pokemon2.getStat().getVitesse();
    }


    /**
     * Renvoie true si l'attaque du pokemon est un succes
     */
    private boolean attaqueTouche(Attaque attaque){
        return (Math.random()) <= attaque.getCap().getPrecision();
    }

}


