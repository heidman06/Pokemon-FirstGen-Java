package pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
//import org.junit.jupiter.api.Test;
import java.io.IOException;
import pokedex.Pokedex;

public class TestPokemon {
	
	@Test
    public void getNomTest() throws IOException {
    	Pokedex pok = new Pokedex();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
		assertEquals(pok.getInfo("Pikachu").getNom(),"Pikachu");
    }
	       
    @Test
    public void getStatPvTest() throws IOException {
    	Pokedex pok = new Pokedex();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        Pokemon pikachu = new Pokemon(pok.getPokeInfoNom().get("Pikachu"));
		assertEquals(pikachu.getStat().getPV(),11);   	
    }
	    
    @Test
    public void getStatForceTest() throws IOException {
    	Pokedex pok = new Pokedex();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        Pokemon pikachu = new Pokemon(pok.getPokeInfoNom().get("Pikachu"));
		assertEquals(pikachu.getStat().getForce(),6);   	
    }

    @Test
    public void getStatDefenseTest() throws IOException {
    	Pokedex pok = new Pokedex();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        Pokemon pikachu = new Pokemon(pok.getPokeInfoNom().get("Pikachu"));
		assertEquals(pikachu.getStat().getDefense(),5);   	
    }

    @Test
    public void getStatSpecialTest() throws IOException {
    	Pokedex pok = new Pokedex();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        Pokemon pikachu = new Pokemon(pok.getPokeInfoNom().get("Pikachu"));
		assertEquals(pikachu.getStat().getSpecial(),6);   	
    }

    @Test
    public void getStatSetForceTest() throws IOException {
    	Pokedex pok = new Pokedex();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        Pokemon pikachu = new Pokemon(pok.getPokeInfoNom().get("Pikachu"));
        pikachu.getStat().setForce(8);
		assertEquals(pikachu.getStat().getForce(),8);   	
    }

    @Test
    public void getStatSetDefenseTest() throws IOException {
    	Pokedex pok = new Pokedex();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        Pokemon pikachu = new Pokemon(pok.getPokeInfoNom().get("Pikachu"));
        pikachu.getStat().setDefense(9);
		assertEquals(pikachu.getStat().getDefense(),9);   	
    }

    @Test
    public void getStatSetSpecialTest() throws IOException {
    	Pokedex pok = new Pokedex();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        Pokemon pikachu = new Pokemon(pok.getPokeInfoNom().get("Pikachu"));
        pikachu.getStat().setSpecial(7);
		assertEquals(pikachu.getStat().getSpecial(),7);   	
    }
	    
    @Test
    public void getStatSetVitesseTest() throws IOException {
    	Pokedex pok = new Pokedex();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        Pokemon pikachu = new Pokemon(pok.getPokeInfoNom().get("Pikachu"));
        pikachu.getStat().setVitesse(10);
		assertEquals(pikachu.getStat().getVitesse(),10);   	
    }

	@Test
	void testGetNiveau() throws IOException { 
		Pokedex pok = new Pokedex();
		Espece esp = new Espece();
		pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
		Pokemon pikachu = new Pokemon(pok.getPokeInfoNom().get("Pikachu"));
	    assertEquals(pikachu.getNiveau(),1);
	}
	
	@Test
	void testGetPourcentagePV() throws IOException {
		Pokedex pok = new Pokedex();
		pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
		Pokemon pikachu = new Pokemon(pok.getPokeInfoNom().get("Pikachu"));
		assertEquals(pikachu.getPourcentagePV(),100.00);		
	}
		
	@Test
	void testEstEvanoui() throws IOException { 
		Pokedex pok = new Pokedex();
		pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
		Pokemon pikachu = new Pokemon(pok.getPokeInfoNom().get("Pikachu"));
		pikachu.getStat().setPV(0);
		assertEquals(pikachu.estEvanoui(),true);
	}
		
	@Test
	void testAChangeNiveau() throws IOException { 
		Pokedex pok = new Pokedex();
		pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
		Pokemon pikachu = new Pokemon(pok.getPokeInfoNom().get("Pikachu"));
		assertEquals(pikachu.aChangeNiveau(),false);
	}
		
	@Test
	void testPeutMuter() throws IOException {
		Pokedex pok = new Pokedex();
		pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
		Pokemon pikachu = new Pokemon(pok.getPokeInfoNom().get("Pikachu"));
		assertEquals(pikachu.peutMuter(),false);
	}
	
	@Test
    public void getNiveauMutationTest() throws IOException {
        Pokedex pok = new Pokedex();
        Espece esp = new Espece();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        Pokemon pikachu = new Pokemon(pok.getPokeInfoNom().get("Pikachu"));
        assertEquals(pok.getPokeInfoNom().get("Pikachu").getNiveauMutation(),22);
    }
	
    @Test
    public void getEvolutionTest() throws IOException {
        Pokedex pok = new Pokedex();
        Espece esp = new Espece();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        Pokemon pikachu = new Pokemon(pok.getPokeInfoNom().get("Pikachu"));
        assertEquals(pok.getPokeInfoNom().get("Pikachu").getEvolution(),"Raichu");
    }
		
    @Test
    public void soigneTest() throws IOException {
    	Pokedex pok = new Pokedex();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        Pokemon pikachu = new Pokemon(pok.getPokeInfoNom().get("Pikachu"));
        pikachu.soigne();
		assertEquals(pikachu.getStat().getPV(),pikachu.getPvMax());   	 	  	
    }
    
}