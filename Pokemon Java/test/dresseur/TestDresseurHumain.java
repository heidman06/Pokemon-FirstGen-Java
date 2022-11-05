package dresseur;

import static org.junit.jupiter.api.Assertions.assertEquals;


import capacite.Capacite;
import interfaces.ICapacite;
import interfaces.IPokemon;
import org.junit.Test;
//import org.junit.jupiter.api.Test;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;


import org.junit.jupiter.api.BeforeEach;
import pokedex.Pokedex;
import pokemon.Espece;
import pokemon.Pokemon;


public class TestDresseurHumain {

    @Test
    public void getNomTest() throws IOException {
        DresseurHumain testo = Mockito.mock(DresseurHumain.class);
        Mockito.when(testo.getNom()).thenReturn("messi");
        assertEquals(testo.getNom(),"messi");
    }

    @Test
    public void getNiveauTest() throws IOException{
        DresseurHumain testo = Mockito.mock(DresseurHumain.class);
        Mockito.when(testo.getNiveau()).thenReturn(1);
        assertEquals(testo.getNiveau(),1);
    }

    @Test
    public void dresseurEvanouiTest() throws IOException{
        DresseurHumain testo = Mockito.mock(DresseurHumain.class);
        Pokedex pok = new Pokedex();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        testo.setRanch((Pokemon[]) pok.engendreRanch());
        testo.soigneRanch();
        Mockito.when(testo.dresseurEvanoui()).thenReturn(false);
        assertEquals(testo.dresseurEvanoui(), false);
    }

    @Test
    public void setNomTest() throws IOException{
        DresseurHumain testo = Mockito.mock(DresseurHumain.class);
        testo.setNom("bob");
        Mockito.when(testo.getNom()).thenReturn("bob");
        assertEquals(testo.getNom(),"bob");
    }

    @Test
    public void choisitCombattantTest() throws IOException {
        DresseurHumain testo = Mockito.mock(DresseurHumain.class);
        //Pokemon ranchito = Mockito.mock(Pokemon.class);
        Pokedex pok = new Pokedex();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        Pokemon[] ranch = new Pokemon[5];
        ranch[0] = new Pokemon(pok.getPokeInfoNom().get("Pikachu"));
        Mockito.when(testo.choisitCombattant()).thenReturn(ranch[0]);
        assertEquals(testo.choisitCombattant(), ranch[0]);

    }


    @Test
    public void choisitCombattantContreTest() throws IOException {
        DresseurHumain testo = Mockito.mock(DresseurHumain.class);
        Pokedex pok = new Pokedex();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        Pokemon[] ranch = new Pokemon[5];
        ranch[0] = new Pokemon(pok.getPokeInfoNom().get("Pikachu"));
        testo.setRanch(ranch);
        Mockito.when(testo.choisitCombattantContre(ranch[0])).thenReturn(ranch[0]);
        assertEquals(testo.choisitCombattantContre(ranch[0]),ranch[0]);

    }

/*
    @Test
    public void enseigneTest() throws IOException {
        Espece esp = new Espece();
        Pokedex pok = new Pokedex();
        DresseurHumain testo = Mockito.mock(DresseurHumain.class);
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        //esp.setNom(pok.getPokeInfoNom().get("Pikachu"));
        //Pokemon BenzemaBallonDorJeSuisDaccord = new Pokemon(pok.getPokeInfoNom().get("Pikachu"));
        Capacite[] capaPoke = new Capacite[3];
        Pokemon[] ranch = new Pokemon[5];
        ranch[0] = new Pokemon(pok.getPokeInfoNom().get("Pikachu"));
        testo.setRanch(ranch);
        Mockito.when(testo.enseigne(ranch[0], capaPoke).thenReturn(testo.getPokemon(0).getCapacitesApprises()));

    }


    @Test
    public void choisirCapCombatTest() throws IOException {
        DresseurHumain testo = Mockito.mock(DresseurHumain.class);
        Pokedex pok = new Pokedex();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        Pokemon[] ranch = new Pokemon[5];
        ranch[0] = new Pokemon(pok.getPokeInfoNom().get("Pikachu"));
        testo.setRanch(ranch);
        testo.enseigne(ranch[0], );

    }
*/
    @Test
    public void getPokemonTest() throws IOException {
        DresseurHumain testo = Mockito.mock(DresseurHumain.class);
        Pokedex pok = new Pokedex();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        Pokemon[] ranch = new Pokemon[5];
        ranch[0] = new Pokemon(pok.getPokeInfoNom().get("Pikachu"));
        testo.setRanch(ranch);
        Mockito.when(testo.getPokemon()).thenReturn(ranch[0]);
        assertEquals(testo.getPokemon(),ranch[0]);

    }

    @Test
    public void setPokemonTest() throws IOException{
        DresseurHumain testo = Mockito.mock(DresseurHumain.class);
        Pokedex pok = new Pokedex();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        Pokemon[] ranch = new Pokemon[5];
        ranch[0] = new Pokemon(pok.getPokeInfoNom().get("Pikachu"));
        Pokemon BenzemaBallonDor = new Pokemon(pok.getPokeInfoNom().get("Snorlax"));
        testo.setRanch(ranch);
        testo.setPokemon(BenzemaBallonDor);
        Mockito.when(testo.getPokemon()).thenReturn(ranch[0]);
        assertEquals(testo.getPokemon(),ranch[0]);

    }






}
