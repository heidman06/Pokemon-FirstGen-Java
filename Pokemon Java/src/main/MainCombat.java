package main;

import combat.Combat;
import dresseur.Dresseur;
import dresseur.DresseurHumain;
import dresseur.DresseurIA;
import dresseur.StrategyAleatoire;
import dresseurexpert.DresseurExpert;
import dresseurexpert.StrategyExperte;
import iaminmax.DresseurMinMax;
import modeDeJeu.Tournoi;
import pokedex.Pokedex;
import pokemon.Pokemon;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MainCombat {

    public static void main(String[] args) throws IOException, InterruptedException {
        //Demarrage programme
        Scanner scann = new Scanner(System.in);
        String decision;
        boolean valide = false;
        Pokedex pokIntro = new Pokedex();
        pokIntro.afficheIntroPokemon();

        while (!valide) {
        	
        	System.out.println("\n Avant de commencer veuillez mettre votre IDE en UTF-8");

            System.out.println("\n\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃ Choisir une modalité  ┃");
            System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
            System.out.println("┃  1 - IA vs IA         ┃");
            System.out.println("┃  2 - Humain vs IA     ┃");
            System.out.println("┃  3 - Humain vs MinMax ┃");
            System.out.println("┃  4 - Humain vs Expert ┃");
            System.out.println("┃  5 - IA vs Expert     ┃");
            System.out.println("┃  6 - Tournoi          ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");

            decision = scann.nextLine();
            switch (decision.replace(" ", "")) {
                case "1" -> {
                    Pokedex pok = new Pokedex();
                    System.out.println("\n");
                    extracted(pok);
                    combatIA(pok);
                    valide = true;
                }
                case "2" -> {
                    Pokedex pok2 = new Pokedex();
                    System.out.println("\n");
                    extracted(pok2);
                    combatHumainIa(pok2);
                    valide = true;
                }
                case "3" -> {
                    Pokedex pok3 = new Pokedex();
                    System.out.println("\n");
                    extracted(pok3);
                    combatIAElabore(pok3);
                    valide = true;
                }

                case "4" -> {
                    Pokedex pok4 = new Pokedex();
                    System.out.println("\n");
                    extracted(pok4);
                    combatHumainVsExpert(pok4);
                    valide = true;
                }

                case "5" -> {
                    Pokedex pok5 = new Pokedex();
                    System.out.println("\n");
                    extracted(pok5);
                    combatIAvsExpert(pok5);
                    valide = true;
                }
                case "6" -> {
                    Pokedex pok6 = new Pokedex();
                    System.out.println("\n");
                    extracted(pok6);
                    tournoi(pok6);
                    valide = true;

                }
                default -> System.out.println("sélectionner une option valide");
            }
        }
    }

    /**
     * Cree un combat entre un humain et une IA avec un pokedex en parametre
     */
    private static void combatHumainIa(Pokedex pok) {
        StrategyAleatoire strat = null;
        DresseurIA ia = new DresseurIA("Ayoub", strat);
        strat = new StrategyAleatoire(ia);
        DresseurHumain dresHumain = new DresseurHumain("");

        System.out.println("Veuillez attendre quelques instants les infos sont extraites depuis le PokeApi cela peut prendre quelques minutes .... \n");
        ia.setRanch((Pokemon[]) pok.engendreRanch());
        ia.generateRandomName();
        dresHumain.setRanch((Pokemon[]) pok.engendreRanch());
        dresHumain.initialiseCapacites();
        strat.initCapacitesRanch();

        System.out.println("------------------------------------------------------------------------------------------------------ \n");
        Combat combat = new Combat(ia, dresHumain);
        combat.commence();
    }
    
    /**
     * Cree un combat entre un humain et un IA experte avec un pokedex en parametre
     */
    private static void combatHumainVsExpert(Pokedex pok) {
        StrategyExperte strat = null;
        DresseurExpert ia = new DresseurExpert("Expert", strat);
        strat = new StrategyExperte(ia);
        DresseurHumain dresHumain = new DresseurHumain("");

        System.out.println("Veuillez attendre quelques instants les infos sont extraites depuis le PokeApi cela peut prendre quelques minutes .... \n");
        ia.setRanch((Pokemon[]) pok.engendreRanch());
        dresHumain.setRanch((Pokemon[]) pok.engendreRanch());
        dresHumain.initialiseCapacites();
        strat.initCapacitesRanch();

        System.out.println("------------------------------------------------------------------------------------------------------ \n");
        Combat combat = new Combat(ia, dresHumain);
        combat.commence();
    }

    /**
     * Cree un combat entre une IA aleatoire et une IA experte avec en parametre un pokedex
     */
    private static void combatIAvsExpert (Pokedex pok) {
        StrategyAleatoire strat = null;
        StrategyExperte stratV2 = null;
        DresseurIA ia = new DresseurIA("Ayoub", strat);
        ia.generateRandomName();
        DresseurExpert iaV2 = new DresseurExpert("", stratV2);
        strat = new StrategyAleatoire(ia);
        stratV2 = new StrategyExperte(iaV2);

        System.out.println("Veuillez attendre quelques instants les infos sont extraites depuis le PokeApi cela peut prendre quelques minutes .... \n");
        ia.setRanch((Pokemon[]) pok.engendreRanch());
        iaV2.setRanch((Pokemon[]) pok.engendreRanch());
        strat.initCapacitesRanch();
        stratV2.initCapacitesRanch();


        System.out.println("------------------------------------------------------------------------------------------------------ \n");
        Combat combat = new Combat(iaV2, ia);
        combat.commence();
    }
    
    /**
     * Cree un combat entre 2 IA aleatoire avec en parametre un pokedex
     */
    private static void combatIA(Pokedex pok) {
        StrategyAleatoire strat = null;
        StrategyAleatoire stratV2 = null;
        DresseurIA ia = new DresseurIA("Ayoub", strat);
        DresseurIA iaV2 = new DresseurIA("Reda", stratV2);
        ia.generateRandomName();
        iaV2.generateRandomName();
        strat = new StrategyAleatoire(ia);
        stratV2 = new StrategyAleatoire(iaV2);

        System.out.println("Veuillez attendre quelques instants les infos sont extraites depuis le PokeApi cela peut prendre quelques minutes .... \n");
        ia.setRanch((Pokemon[]) pok.engendreRanch());
        iaV2.setRanch((Pokemon[]) pok.engendreRanch());
        for (StrategyAleatoire strategyAleatoire : Arrays.asList(strat, stratV2)) {
            strategyAleatoire.initCapacitesRanch();
        }

        System.out.println("------------------------------------------------------------------------------------------------------ \n");
        Combat combat = new Combat(ia, iaV2);
        combat.commence();
    }


    private static void extracted(Pokedex pok) throws IOException {
        System.out.println("\n");
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        pok.initialiseFromCsvMoves("listeCapacites.csv");
        pok.initialiseFromCsvEfficacity("efficacites.csv");
    }

    /**
     * Cree un combat entre une IA elabore et un humain avec en parametre un pokedex
     */
    private static void combatIAElabore(Pokedex pok){
        DresseurMinMax ia = new DresseurMinMax("Expert");
        DresseurHumain humain = new DresseurHumain("Humain");

        ia.setRanch((Pokemon[]) pok.engendreRanch());
        humain.setRanch((Pokemon[]) pok.engendreRanch());
        humain.initialiseCapacites();
        ia.getMinMax().initCapacitesRanch();


        System.out.println("------------------------------------------------------------------------------------------------------ \n");
        Combat combat = new Combat(ia, humain);
        ia.getMinMax().setCombat(combat);
        combat.commence();
    }

    /**
     * Cree un combat entre 2 IA experte avec en parametre un pokedex
     */
    private static void combat2IAExpert(Pokedex pok){
        DresseurMinMax ia = new DresseurMinMax("Expert");
        DresseurMinMax ia2 = new DresseurMinMax("Pedro");

        ia.setRanch((Pokemon[]) pok.engendreRanch());
        ia.getMinMax().initCapacitesRanch();
        ia2.setRanch((Pokemon[]) pok.engendreRanch());
        ia2.getMinMax().initCapacitesRanch();

        System.out.println("------------------------------------------------------------------------------------------------------ \n");
        Combat combat = new Combat(ia, ia2);
        ia.getMinMax().setCombat(combat);
        ia2.getMinMax().setCombat(combat);
        combat.commence();
    }
    /**
     * Cree un tournoi avec en parametre un pokedex
     */
    private static void tournoi(Pokedex pok) throws IOException {
        Tournoi tour = new Tournoi(8);
        tour.commenceTournoi(tour.getListeParticipant());
    }
}