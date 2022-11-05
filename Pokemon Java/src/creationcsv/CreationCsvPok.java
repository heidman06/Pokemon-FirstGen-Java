package creationcsv;

import capacite.Capacite;
import org.json.simple.parser.ParseException;
import pokedex.Pokedex;
import pokemon.Espece;
import pokemon.Pokemon;

import java.io.*;
import java.util.*;

public class CreationCsvPok {

    String nom;

    public CreationCsvPok() {
    }

    public void remplirCsv() throws IOException, ParseException, java.text.ParseException {
        Pokedex pok = new Pokedex();
        Espece esp = new Espece();
        String[] tabNomEsp = new String[151];
        Capacite[] cap = null;

        List<String> rows = new ArrayList<>();

        pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");

        Iterator iterator = pok.getPokeInfoNom().keySet().iterator();

        String separator = ";";
        String lineSeparator = "\n";
        int numPoke = 0;

        Writer writer = new FileWriter("info.csv");

        while (iterator.hasNext()) {
            System.out.println("Poke num --> " + numPoke + '\n');
            //Keys of the HashMap
            String key = (String) iterator.next();
            System.out.println("KEY --> " + key);
            //Poke with the determinate key
            Espece poke = pok.getPokeInfoNom().get(key);
            System.out.println("POKE --> " + poke);

            writer.write(poke.getNom());
            writer.write(separator);

            Capacite[] capacites = (Capacite[]) poke.getCapSet();
            System.out.println("CAPACITES --> " + Arrays.toString(capacites));

            for (int i = 0; i < capacites.length; i++) {
                writer.write(capacites[i].getNom());
                writer.write(separator);
                writer.write(Integer.toString(capacites[i].getPuissance()));
                writer.write(separator);
                writer.write(Double.toString(capacites[i].getPrecision()));
                writer.write(separator);
                writer.write(Integer.toString(capacites[i].getPP()));
                writer.write(separator);
                writer.write(Long.toString(capacites[i].getNum()));
                writer.write(separator);
                writer.write(capacites[i].getCategorie().getNom());
                writer.write(separator);
                        if(capacites[i].getType() != null){
                            writer.write(capacites[i].getType().getNom());
                            writer.write(separator);
                        }

            }

            numPoke++;
            writer.write(lineSeparator);
        }
        writer.close();


    }
}
