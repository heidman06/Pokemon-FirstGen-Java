package modeDeJeu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import capacite.Capacite;
import dresseur.StrategyAleatoire;
import dresseurexpert.DresseurExpert;
import dresseurexpert.StrategyExperte;
import pokedex.Pokedex;
import pokemon.Pokemon;
import combat.Combat;
import dresseur.Dresseur;
import dresseur.DresseurHumain;
import dresseur.DresseurIA;

public class Tournoi {

    private int nbParticipant;
    private List<Dresseur> listeParticipant = new ArrayList<>();

    Pokedex pokedexMoves = new Pokedex();
    Pokedex pokedex = new Pokedex();


    /**
     * Affiche tous les noms des participants du tournoi
     */
    public void afficheParticipant()
    {
        //String s = "";
        for(Dresseur d : this.listeParticipant)
        {
            System.out.println(d.getNom());
        }
    }

    /**
     * Set les capacites de tous les pokemon d'un dresseur humain
     *
     */
    private void setCapacitePokemonHumain(DresseurHumain d1) {

        d1.initialiseCapacites();

    }
    private void setCapacitePokemonIAExperte(DresseurExpert d1){
        StrategyExperte strat = new StrategyExperte(d1);
        strat.initCapacitesRanch();
    }

    /**
     * Set les capacites de tous les pokemon d'un dresseur IA
     *
     */
    private void setCapacitePokemonIA(DresseurIA d1) {
        StrategyAleatoire strat = new StrategyAleatoire(d1);
        strat.initCapacitesRanch();
    }

    /**
     * Creer le tournoi avec le nombre de participants
     *
     */
    public Tournoi(int nb) throws IOException
    {
    	System.out.println("Choix du joueur ");
        System.out.println("\n\n\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("┃        0 - Humain     ┃");
        System.out.println("┃        1 - IAExperte  ┃");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        int choix = inputInt();



        pokedex.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");


        pokedexMoves.initialiseFromCsvMoves("listeCapacites.csv");

        Capacite cap = (Capacite)pokedexMoves.getCapacite("tackle");

        this.nbParticipant= nb;
        if(choix == 0){
                DresseurHumain dresseur = new DresseurHumain("");
                String nom = dresseur.getNom();
                System.out.println("Patientez le tournoi est entrain de se preparer : \n");
                dresseur.setRanch((Pokemon[])pokedex.engendreRanch());
                this.setCapacitePokemonHumain(dresseur);


                this.listeParticipant.add(dresseur);
            }
        else if(choix == 1){
                StrategyExperte strat = null;
                DresseurExpert dresseur = new DresseurExpert("Expert",strat);
                strat = new StrategyExperte(dresseur);

                System.out.println("Patientez le tournoi est entrain de se preparer : \n");
                dresseur.setRanch((Pokemon[])pokedex.engendreRanch());

                setCapacitePokemonIAExperte(dresseur);
                this.listeParticipant.add(dresseur);
            }

        else{
            System.out.println("Faites un choix parmi proposez \n");
        }
        int nbJoueur = 1;
        int cmp = nbJoueur;
        while (cmp <nb)
        {

            String str = "Dresseur"+cmp;

            DresseurIA ia = new DresseurIA(str,null);
            StrategyAleatoire strat = new StrategyAleatoire(ia);

            ia = new DresseurIA(str, strat);
            ia.setNom(ia.generateRandomName());

            ia.setRanch((Pokemon[])pokedex.engendreRanch());

            this.setCapacitePokemonIA(ia);




            this.listeParticipant.add(ia);
            cmp++;

        }

    }

    /**
     * Lance le tournoi et renvoie le gagnant
     *
     */
    public List<Dresseur> commenceTournoi(List<Dresseur> L)
    {

        // S'il ne reste q'un combattant, il est champion
        if (L.size()==1) {

            System.out.println("LE CHAMPION EST " + ((Dresseur) L.get(0)).getNom());
            return L;
        }
        // Sinon les combats continuent
        else {
            System.out.println("IL Y A "+L.size()+" COMBATTANTS");

            List<Dresseur> listeParticipantTourSuivant = new ArrayList<>();
            int j=1,i=0,ok=-1;

            while ( L.size()>j || ok == -1 ) {

                System.out.println("NOUVEAU COMBAT ENTRE "+((Dresseur)L.get(j)).getNom()+" ET "+((Dresseur)L.get(i)).getNom());

                Combat comb = new Combat(L.get(i), L.get(j));
                comb.commence();

                listeParticipantTourSuivant.add( (Dresseur)comb.getGagnant() );

                i+=2;
                j+=2;
                if (j>=L.size() || (L.get(j)==null || L.get(i)==null)) {
                    ok=1;
                }

            }

            return commenceTournoi(listeParticipantTourSuivant);

        }
    }

    /**
     * Renvoie le nombre de participant du tournoi
     *
     */
    public int getNbParticipant() {
        return nbParticipant;
    }

    /**
     * Renvoie une ArrayList de Dresseur de tout les participants du tournoi
     *
     */
    public List<Dresseur> getListeParticipant() {
        return listeParticipant;
    }

    /**
     * Renvoie le String entre au clavier
     *
     */
    public String input() {
        String saisie = "";
        Scanner scan = new Scanner(System.in);
        saisie += scan.nextLine();
        System.out.println("Vous avez saisi : " + saisie +"\n");



        return saisie;
    }

    public String inputText() {
        String saisie = "";
        Scanner scan = new Scanner(System.in);
        saisie += scan.nextLine();
        System.out.println("Vous avez saisi : " + saisie +"\n");



        return saisie;
    }
    /**
     * Renvoie le int entre au clavier
     *
     */
    public int inputInt() {
        int saisie;
        Scanner scan = new Scanner(System.in);
        saisie = scan.nextInt();
        System.out.println("Vous avez saisi : " + saisie +"\n");

        return saisie;
    }



}