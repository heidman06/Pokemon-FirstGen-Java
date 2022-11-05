package pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
//import org.junit.jupiter.api.Test;
import java.io.IOException;
import pokedex.Pokedex;
import type.Type;

public class TestEspece {

	@Test
    public void getNomTest() throws IOException {
    	Pokedex pok = new Pokedex();
        Espece esp = new Espece();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
		assertEquals(pok.getInfo("Pikachu").getNom(),"Pikachu");
    }

	@Test
    public void getNiveauDepartTest() throws IOException {
    	Pokedex pok = new Pokedex();
        Espece esp = new Espece();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
		assertEquals(pok.getInfo("Pikachu").getNiveauDepart(),1);
    }
	
	@Test
    public void getBaseExpTest() throws IOException {
    	Pokedex pok = new Pokedex();
        Espece esp = new Espece();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
		assertEquals(pok.getInfo("Pikachu").getBaseExp(),82);
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
    public void setType1Test() throws IOException {
    	Pokedex pok = new Pokedex();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        pok.getPokeInfoNom().get("Bulbasaur").setType1(Type.Normal);
		assertEquals(pok.getPokeInfoNom().get("Bulbasaur").getTypes()[0],Type.Normal);
	
	}

	@Test
    public void setType2Test() throws IOException {
    	Pokedex pok = new Pokedex();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        pok.getPokeInfoNom().get("Bulbasaur").setType2(Type.Normal);
		assertEquals(pok.getPokeInfoNom().get("Bulbasaur").getTypes()[1],Type.Normal);
	
	}
	
	@Test
    public void setNomTest() throws IOException {
    	Pokedex pok = new Pokedex();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        pok.getPokeInfoNom().get("Bulbasaur").setNom("Bob");
		assertEquals(pok.getPokeInfoNom().get("Bulbasaur").getNom(),"Bob");
	
	}
	
	@Test
    public void getBaseStatPvTest() throws IOException {
		Pokedex pok = new Pokedex();
		Espece esp = new Espece();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        esp.setNom("Bulbasaur");
		assertEquals(esp.getBaseStat().getPV(),0);   	
    }
	    
    @Test
    public void getBaseStatForceTest() throws IOException {
    	Pokedex pok = new Pokedex();
		Espece esp = new Espece();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        esp.setNom("Bulbasaur");
		assertEquals(esp.getBaseStat().getForce(),0);   	
    }

    @Test
    public void getBaseStatDefenseTest() throws IOException {
    	Pokedex pok = new Pokedex();
		Espece esp = new Espece();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        esp.setNom("Bulbasaur");
		assertEquals(esp.getBaseStat().getDefense(),0);   	
    }

    @Test
    public void getBaseStatSpecialTest() throws IOException {
    	Pokedex pok = new Pokedex();
		Espece esp = new Espece();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        esp.setNom("Bulbasaur");
		assertEquals(esp.getBaseStat().getSpecial(),0);   	
    }

    @Test
    public void getBaseStatVitesseTest() throws IOException {
    	Pokedex pok = new Pokedex();
		Espece esp = new Espece();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        esp.setNom("Bulbasaur");
		assertEquals(esp.getBaseStat().getVitesse(),0);   	
    }
    
    @Test
    public void getBaseStatSetPvTest() throws IOException {
    	Pokedex pok = new Pokedex();
		Espece esp = new Espece();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        esp.setNom("Bulbasaur");
        esp.getBaseStat().setPV(10);
		assertEquals(esp.getBaseStat().getPV(),10);   	
    }

    @Test
    public void getBaseStatSetForceTest() throws IOException {
    	Pokedex pok = new Pokedex();
		Espece esp = new Espece();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        esp.setNom("Bulbasaur");
        esp.getBaseStat().setForce(8);
		assertEquals(esp.getBaseStat().getForce(),8);   	
    }
	    
    @Test
    public void getBaseStatSetDefenseTest() throws IOException {
    	Pokedex pok = new Pokedex();
		Espece esp = new Espece();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        esp.setNom("Bulbasaur");
        esp.getBaseStat().setDefense(3);
		assertEquals(esp.getBaseStat().getDefense(),3);   	
    }
    
    @Test
    public void getBaseStatSetSpecialTest() throws IOException {
    	Pokedex pok = new Pokedex();
		Espece esp = new Espece();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        esp.setNom("Bulbasaur");
        esp.getBaseStat().setSpecial(9);
		assertEquals(esp.getBaseStat().getSpecial(),9);   	
    }
    
    @Test
    public void getBaseStatSetVitesseTest() throws IOException {
    	Pokedex pok = new Pokedex();
		Espece esp = new Espece();
        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
        esp.setNom("Bulbasaur");
        esp.getBaseStat().setVitesse(15);
		assertEquals(esp.getBaseStat().getVitesse(),15);   	
    }
	
}