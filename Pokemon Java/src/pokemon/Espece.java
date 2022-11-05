package pokemon;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.Map.Entry;

import java.util.Scanner;


import org.json.simple.parser.ParseException;

import capacite.Capacite;

import interfaces.ICapacite;
import interfaces.IEspece;

import interfaces.IStat;
import interfaces.IType;
import pokedex.Pokedex;
import stats.Stats;
import type.Type;

public class Espece implements IEspece{

	private int id;
	private String nom;
	private int basePv;
	private int baseForce;
	private int baseDef;
	private int baseSpecial;
	private int baseVitesse;
	private int baseExp;	
	private int pvGain;
    private int forceGain;
    private int defenseGain;
    private int specialGain;
    private int vitesseGain;
	private Type type1;
	private Type type2;
	private int niveauDepart;
	private int niveauMutation;
	private String evolution;



	/**
	 * Constructeur de espece
	 *	
	 */	
	public Espece(int id ,String nom, int pvBase, int forceBase, int defBase, int specialBase, int vitesseBase, int expBase,
			int pvGain, int forceGain, int defenseGain, int specialGain, int vitesseGain, Type[] types, int niveauBase,
			int nivMutation, String evolution) {
		this.id = id;
		this.nom = nom;
		this.basePv = pvBase;
		this.baseForce= forceBase;
		this.baseDef = defBase;
		this.baseSpecial = specialBase;
		this.baseVitesse = vitesseBase;
		this.baseExp = expBase;
		this.pvGain = pvGain;
		this.forceGain = forceGain;
		this.defenseGain = defenseGain;
		this.type1 = types[0];
		this.type2 = types[1];
		this.specialGain = specialGain;
		this.vitesseGain = vitesseGain;
		this.niveauDepart = niveauBase;
		this.niveauMutation = nivMutation;
		this.evolution = evolution;		
	}
	/**
	 * Constructeur vide de espece
	 *	
	 */		
	public Espece () {		
	}
	/**
	 * Constructeur de espece 2
	 *	
	 */	
	public Espece(String nom,Type[] types) {
		this.nom = nom;
		this.type1 = types[0];
		this.type2 = types[1];
	}
	/**
	 * toString de Espece
	 *	
	 */	
	@Override
	public String toString() {
		return "Espece [nom=" + nom + ", basePv=" + basePv + ", baseForce=" + baseForce + ", baseDef=" + baseDef
				+ ", baseSpecial=" + baseSpecial + ", baseVitesse=" + baseVitesse + ", baseExp=" + baseExp + ", pvGain="
				+ pvGain + ", forceGain=" + forceGain + ", defenseGain=" + defenseGain + ", specialGain=" + specialGain
				+ ", vitesseGain=" + vitesseGain + ", types1=" + type1 + " " + "type2=" + type2 + ", niveauDepart="
				+ niveauDepart + ", niveauMutation=" + niveauMutation + " "+ "evolution=" + evolution + "]";
	}
	/**
	 * Affichage structure de espece
	 *	
	 */	
	public void afficheEsp() {
		
		System.out.println("Espece :  \n");
	    System.out.println(" ------------------------------------------------");	
		System.out.printf("%-12s %-12s %-12s %-12s %-12s %-12s %-12s %-12s %-12s %-12s %-12s %-12s %-12s %-12s %-12s %-12s %-12s\n", "Nom","BasePv","BaseForce","BaseDef", "BaseSpecial" ,"BaseVitesse" , "BaseExp" , "PvGain", "forceGain","defenseGain",
							"specialGain","vitesseGain","Type1","Type2","NiveauDepart","NiveauMutation","Evolution");
		System.out.printf("%-12s %-12s %-12s %-12s %-12s %-12s %-12s %-12s %-12s %-12s %-12s %-12s %-12s %-12s %-12s %-12s %-12s\n", nom,basePv,baseForce,baseDef,baseSpecial,
							baseVitesse,baseExp,pvGain,forceGain,defenseGain,specialGain,vitesseGain,type1,type2,niveauDepart,niveauMutation,evolution);
	    System.out.println(" --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		}
	/**
	 * Renvoie les stats de base
	 *	
	 */	
	@Override
	public IStat getBaseStat() {
		return new Stats() {
			public int getPV() {
				return basePv ;
			}

			@Override
			public int getForce() {
				return baseForce;
			}

			@Override
			public int getDefense() {
				return baseDef;
			}

			@Override
			public int getSpecial() {
				return baseSpecial;
			}

			@Override
			public int getVitesse() {
				return baseVitesse;
			}

			@Override
			public void setPV(int i) {
				basePv = i;
			}

			@Override
			public void setForce(int i) {
				baseForce = i;
			}

			@Override
			public void setDefense(int i) {
				baseDef = i;
			}

			@Override
			public void setVitesse(int i) {
				baseVitesse = i;
			}

			@Override
			public void setSpecial(int i) {
				baseSpecial =i;
			}
		};
	}
	/**
	 * Renvoie le nom de l'espece
	 *	
	 */	
	@Override
	public String getNom() {		
		return nom;
	}
	/**
	 * Renvoie le niveau de depart
	 *	
	 */	
	@Override
	public int getNiveauDepart() {	
		return niveauDepart;
	}
	/**
	 * Renvoie l'experience de depart
	 *	
	 */	
	@Override
	public int getBaseExp() {	
		return baseExp;
	}
	/**
	 * Renvoie les Stats du pokemon
	 *	
	 */	
	@Override
	public IStat getGainsStat() {
		return new Stats() {
			public int getPV() {
				return pvGain ;
			}

			@Override
			public int getForce() {
				return forceGain;
			}

			@Override
			public int getDefense() {
				return defenseGain;
			}

			@Override
			public int getSpecial() {
				return specialGain;
			}

			@Override
			public int getVitesse() {
				return vitesseGain;
			}

			@Override
			public void setPV(int i) {
				pvGain = i;
			}

			@Override
			public void setForce(int i) {
				forceGain = i;
			}

			@Override
			public void setDefense(int i) {
				defenseGain = i;
			}

			@Override
			public void setVitesse(int i) {
				vitesseGain = i;
			}

			@Override
			public void setSpecial(int i) {
				specialGain = i;
			}
		};
	}
	/**
	 * Renvoie un tableau de capacite qui sont les capacite de base de l'espece
	 *	
	 */


	@Override
	public ICapacite[] getCapSet() {
		Pokedex pok = new Pokedex();
		try {
			pok.recupCapPok(this.nom);
		} catch (ParseException | java.text.ParseException | IOException e) {
			throw new RuntimeException(e);
		}
		Capacite[] cap = new Capacite[pok.getMap().size()];
		int i = 0;
		for (Entry<Capacite, Long> capac : pok.getMap().entrySet()) {
			cap[i] = capac.getKey();
			i++;
		}
		return cap;
	}
	/**
	 * Renvoie l'espece de l'evolution 
	 *	
	 */	
	@Override
	public IEspece getEvolution(int niveau) {
        Espece esp=new Espece();
        Pokedex pok = new Pokedex();
    if (niveau >= niveauMutation && niveauMutation!=0) {
            try {
                FileReader fichier = new FileReader("listePokemon1G.csv");
                BufferedReader reader = new BufferedReader(fichier);
                reader.readLine();
                while (reader.ready()) {
                    String line = reader.readLine();
                    Scanner scan = new Scanner(line).useDelimiter(";");
                    scan.next();
                    if (evolution.equals(scan.next())) {
                            pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
                            esp= (Espece) pok.getInfo(evolution);
                    }
                    scan.close();
                }
                reader.close();
                fichier.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
		return esp;
        }
        else {
            return null;
        }
}
	
	/**
	 * Renvoie les types de l'espece
	 *	
	 */		

	@Override
	public IType[] getTypes() {
            Type[] type = new Type[2];
            type[0]=this.type1;
            type[1]=this.type2;           
    return type;
	}
	/**
	 * Renvoie le niveau de l'evolution en une autre espece 
	 *	
	 */	
	public int getNiveauMutation() {
		return niveauMutation;
	}
	/**
	 * renvoie le string de l'espece de l'evolution 
	 *	
	 */	
	public String getEvolution() {
		return evolution;
	}
	/**
	 * Set le nom de l'espece
	 *	
	 */	
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * Set le type 1 de l'espece
	 *	
	 */	
	public void setType1(Type type1) {
		this.type1 = type1;
	}
	/**
	 * Set le type 2 de l'espece
	 *	
	 */	
	public void setType2(Type type2) {
		this.type2 = type2;
	}

	public int getId() {
		return id;
	}
}
