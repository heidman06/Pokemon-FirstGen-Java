package combat;

import java.util.ArrayList;

import dresseur.Attaque;
import dresseur.Dresseur;
import interfaces.IAttaque;
import interfaces.ICombat;
import interfaces.IDresseur;
import interfaces.IPokemon;
import interfaces.ITour;
import pokemon.Pokemon;
import tour.Tours;


public class Combat implements ICombat {
    private final Dresseur dresseur1;
    private final Dresseur dresseur2;

    private int nbTours = 1;


    private Dresseur gagnant;
    

    /**
     * Renvoie le gagnant du combat
     * 
     */
    public Dresseur getGagnant() {
		return gagnant;
	}

    /**
     * Set le gagnant du combat
     * 
     */
	public void setGagnant(Dresseur gagnant) {
		this.gagnant = gagnant;
	}


    /**
     * Constructeur de Combat avec en parametre 2 dresseurs
     *
     */
    public Combat(Dresseur d1, Dresseur d2) {
        this.dresseur1 = d1;
        this.dresseur2 = d2;
    }

    /**
     * Commence le combat
     */
    @Override
    public void commence() {
        new Tours();
        Tours tour;
        new Attaque();
        Attaque attaque1;
        new Attaque();
        Attaque attaque2;

        System.out.println("Le combat commence");

        // Choisit les premiers combattants a envoyer au combat
        Pokemon pokemon1 = (Pokemon) dresseur1.choisitCombattant();
        Pokemon pokemon2 = (Pokemon) dresseur2.choisitCombattant();

        System.out.println("\n");
        System.out.println("                                            ╔═════════════════════════════╗");
        System.out.println("                                             " + dresseur1.getNom() + " (" + dresseur1.nbDePokemonRestants() + "/6)");
        System.out.println("                                            ╠╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╣");
        System.out.println("                                             " + pokemon1.getNom() + " N. "+ pokemon1.getNiveau());
        System.out.println("                                             " + barreDeVie(pokemon1));
        //System.out.println("                                            (" + pokemon1.getPourcentagePV() + "/" + pokemon1.getPvMax() + ")");
        System.out.println("                                            ╚═════════════════════════════╝");

        System.out.println("\n");



        System.out.println("                              ╔═══════════════════╗");
        System.out.println("                              ║   VS ⚡ Tour n°0  ║");
        System.out.println("                              ╚═══════════════════╝");





        System.out.println("\n");

        System.out.println("╔═════════════════════════════╗");
        System.out.println(" " + dresseur2.getNom() + " (" + dresseur2.nbDePokemonRestants() + "/6)");
        System.out.println("╠╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╣");
        System.out.println(" " + pokemon2.getNom() + " N. "+ pokemon2.getNiveau());
        System.out.println(" PV (" + pokemon2.getPourcentagePV() +"/" + pokemon2.getPvMax() +")");
        System.out.println(" " + barreDeVie(pokemon2));
        System.out.println("╚═════════════════════════════╝");
        System.out.println("\n");

        // Tant que les deux dresseurs peuvent combattre
        while (!dresseur1.dresseurEvanoui() && !dresseur2.dresseurEvanoui()) {

            // Choix des attaques par les dresseurs
            attaque1 = (Attaque) dresseur1.choisitAttaque(pokemon1, pokemon2);
            attaque2 = (Attaque) dresseur2.choisitAttaque(pokemon2, pokemon1);

            System.out.println("\n");
            System.out.println("Resumer du tour !!!");
            System.out.println("\n");

            // Procede aux echanges (s'il y en a)
            if (!attaque1.estUneCapacite()) {
                System.out.println(pokemon1.getNom() + " a ete echange avec " + attaque1.echangeCombattant().getNom());
                pokemon1 = (Pokemon) attaque1.echangeCombattant();
            }
            if (!attaque2.estUneCapacite()) {
                System.out.println(pokemon2.getNom() + " a ete echange avec " + attaque2.echangeCombattant().getNom());
                pokemon2 = (Pokemon) attaque2.echangeCombattant();
            }
            // Creer un nouveau tour
            tour = (Tours) nouveauTour(pokemon1, attaque1, pokemon2, attaque2);



            tour.commence();

            System.out.println("\n");

            System.out.println("\n");



            System.out.println("                                            ╔═════════════════════════════╗");
            System.out.println("                                             " + dresseur1.getNom() + " (" + dresseur1.nbDePokemonRestants() + "/6)");
            System.out.println("                                            ╠╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╣");
            System.out.println("                                             " + pokemon1.getNom() + " N. "+ pokemon1.getNiveau());
            System.out.println("                                             " + barreDeVie(pokemon1));
            //System.out.println("                                            (" + pokemon1.getPourcentagePV() + "/" + pokemon1.getPvMax() + ")");
            System.out.println("                                            ╚═════════════════════════════╝");

            System.out.println("\n");



            System.out.println("                              ╔═══════════════════╗");
            System.out.println("                                  VS ⚡ Tour n°" + nbTours);
            System.out.println("                              ╚═══════════════════╝");





            System.out.println("\n");

            System.out.println("╔═════════════════════════════╗");
            System.out.println(" " + dresseur2.getNom() + " (" + dresseur2.nbDePokemonRestants() + "/6)");
            System.out.println("╠╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╣");
            System.out.println(" " +pokemon2.getNom() + " N. "+ pokemon2.getNiveau());
            System.out.println(" PV (" + pokemon2.getPourcentagePV() +"/" + pokemon2.getPvMax() +")");
            System.out.println(" " +barreDeVie(pokemon2));
            System.out.println("╚═════════════════════════════╝");
            System.out.println("\n");

            // Commence le tour
            //tour.commence();

            // Si le pokemon actif est tombe KO, envoie un nouveau pokemon a la place
            if (pokemon1.estEvanoui() && !dresseur1.dresseurEvanoui()) {
                pokemon1 = (Pokemon) dresseur1.choisitCombattant();
            }
            if (pokemon2.estEvanoui() && !dresseur2.dresseurEvanoui()) {
                pokemon2 = (Pokemon) dresseur2.choisitCombattant();
            }

            // Augmente le nb de tour
            nbTours++;
        }

        termine();
    }

    /**
     * Renvoie le dresseur 1
     * @return
     */
    @Override
    public IDresseur getDresseur1() {
        return dresseur1;
    }

    /**
     * Renvoie le dresseur 2
     * @return
     */
    @Override
    public IDresseur getDresseur2() {
        return dresseur2;
    }

    /**
     * Renvoie un nouveau tour avec en parametre les 2 pokemon et leurs attaques
     *
     */
    @Override
    public ITour nouveauTour(IPokemon pok1, IAttaque atk1, IPokemon pok2, IAttaque atk2) {
        return new Tours(pok1, atk1, pok2, atk2);
    }

    /**
     * Renvoie le resultat du combat
     */
    @Override
    public void termine() {
        if (dresseur1.dresseurEvanoui()) {
            System.out.println("Le dresseur " + dresseur1.getNom() + " est tombe KO !!\n" +
                    "Le dresseur " + dresseur2.getNom() + " a gagne !!");
            this.gagnant=dresseur2;
        } else {
            System.out.println("Le dresseur " + dresseur2.getNom() + " est tombe KO !!\n" +
                    "Le dresseur " + dresseur1.getNom() + " a gagne !!");
            this.gagnant=dresseur1;
        }

        dresseur1.soigneRanch();
        dresseur2.soigneRanch();
    }
    
    /**
     * Renvoie une barre de vie du pokemon en parametre selon ses PV actuels
     *
     */
    public String barreDeVie(Pokemon pok){
        int pourcentage = (int) (100*pok.getPourcentagePV()/pok.getPvMax());
        if (pourcentage == 100){
            return("|♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥|");
        }
        else if (pourcentage < 100 && pourcentage >= 75) {
            return("|♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♡♡♡♡♡|");
        }
        else if (pourcentage < 75 &&  pourcentage >= 50) {
            return("|♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♡♡♡♡♡♡♡♡♡♡|");
        }
        else if (pourcentage < 50 && pourcentage >= 25) {
            return("|♥♥♥♥♥♥♥♥♥♥♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡|");
        }
        else if (pourcentage < 25 && pourcentage >0 ){
            return("|♥♥♥♥♥♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡|");
        }
        else {
            return("|♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡|");
        }
    }

    //On verifie que l'etat du jeu est terminal c'est a dire que la partie est fini donc un des deux dresseurs a gagné
    /**
     * Renvoie true si l'un des 2 dresseurs est évanoui
     */
    public boolean etatduJeu(){
        return dresseur1.dresseurEvanoui() || dresseur2.dresseurEvanoui();
    }
    
    //On determine si le dresseur x est gagnant ou pas
    /**
     * Renvoie 1 si le dresseur 2 a perdu, 0 si le dresseur en parametre a perdu, ou 0.0 si le resultat est indetermine
     */
    public double fonctionEvaluation(Dresseur x) {

        if (etatduJeu() && dresseur2.dresseurEvanoui()) {
            return 1;
        } else if (etatduJeu() && x.dresseurEvanoui()) {
            return 0;
        }
        else
            return 0.0;
    }


    /**
     * Renoie une arraylist de type attaque avec en parametre le dresseur et son pokemon
     */
    public ArrayList<Attaque> getListeCoups(Dresseur dresseur, Pokemon pokemon){
        ArrayList<Attaque> attaques = new ArrayList<>();

        for (int i = 0; i < pokemon.nbCapacitesApprises(); i++){
            if(pokemon.getCapacitesApprises()[i].getPP() > 0)
                attaques.add((Attaque) pokemon.getCapacitesApprises()[i]);
        }
        for (int i = 0; i < dresseur.getRanch().length; i++){
            if (!dresseur.getPokemon(i).estEvanoui() && dresseur.getPokemon(i) != pokemon) {
                Attaque echange = new Attaque();
                echange.setPokemon(dresseur.getPokemon(i));
                attaques.add(echange);
            }
        }

        return attaques;
    }


}