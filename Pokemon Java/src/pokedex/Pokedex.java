package pokedex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import capacite.Capacite;
import capacite.Categorie;
import interfaces.ICapacite;
import interfaces.IEspece;
import interfaces.IPokedex;
import interfaces.IPokemon;
import interfaces.IType;
import pokemon.Espece;
import pokemon.Pokemon;


import type.Type;

/**
 * 
 *	
 */
public class Pokedex implements IPokedex {

	HashMap<Integer , Espece> pokeInfoIndice = new HashMap <>();
	HashMap<String , Espece> pokeInfoNom = new HashMap <>();	
	HashMap<String , Capacite> capInfo = new HashMap <>();	
	HashMap<List<Type>, Double> tableEffi = new HashMap<>();	
	HashMap<Capacite , Long> capDispo = new HashMap <>();
	
	/**
	 * Renvoie une HashMap de type Capacite et Long
	 *	
	 */	
	  public HashMap<Capacite, Long> getMap() {
	         return capDispo;
	  }



	int num ;
	String nom ;
	int pvBase ;
	int forceBase ;
	int defBase ;
	int specialBase ;
	int vitesseBase;
	int expBase ;
	int evPv ;
	int evForce ;
	int evDef ;
	int evSpecial;
	int evVitesse ;

	int niveauBase ;
	int nivMutation ;
	String evolution ;
	private Type[] types = new Type[2] ;
	private String nomCap;
	private int puissance;
	private double precision;
	private int pp;
	private int numCap;
	private Categorie categorie;
	private Type typeCap;



	/**
	 * Initialise les attributs d'un pokemon du pokedex et creer l'espece a partir d'un fichier csv
	 *	
	 */


	public void initializeFromCSVRecupInfoEspece(String nomfichier) throws IOException {
		
	    try {	    	
	    	FileReader fichier = new FileReader(nomfichier);
	    	BufferedReader reader = new BufferedReader (fichier) ;
	    	reader.readLine();
	    	
	    	while (reader.ready()) {
	    		String line = reader.readLine();
	    		Scanner st = new Scanner(line).useDelimiter(";");

	    		 num = st.nextInt();
	    		 nom = st.next();
	    		 pvBase = st.nextInt();
	    		 forceBase = st.nextInt();
	    		 defBase = st.nextInt();
	    		 specialBase = st.nextInt();
	    		 vitesseBase = st.nextInt();
	    		 expBase = st.nextInt();
	    		 evPv = st.nextInt();
	    		 evForce = st.nextInt();
	    		 evDef = st.nextInt();
	    		 evSpecial = st.nextInt();
	    		 evVitesse = st.nextInt();
	    		 String type1 = st.next();    		
	    		 types[0] = conversionStringType(type1);
	    		 String type2 = st.next();
	    		 types[1] = conversionStringType(type2);	    
	    		 niveauBase = st.nextInt();
	    		 nivMutation = st.nextInt();
	    		 evolution = st.next();
	
	    		Espece pok = new Espece (num,nom,pvBase,forceBase,defBase,specialBase,vitesseBase,expBase,evPv,evForce,evDef,evSpecial,evVitesse,types,niveauBase,nivMutation,evolution);
	    		pokeInfoIndice.put(num, pok);
	    		pokeInfoNom.put(nom, pok);



	    		
	    		pok.getBaseStat().setPV(pvBase);
	    		pok.getBaseStat().setDefense(defBase);
	    		pok.getBaseStat().setForce(forceBase);
	    		pok.getBaseStat().setSpecial(specialBase);
	    		pok.getBaseStat().setVitesse(vitesseBase);
    		
	    	st.close();
	    	}
	    	reader.close();
	    	fichier.close();
	    	} catch (IOException e) {
	    	e.printStackTrace();
	    	}
	}



	 public void afficheIntroPokemon(){

		 try {
			 FileReader reader = new FileReader("Intro.txt");
			 int info = reader.read();
			 while(info != -1) {
				 System.out.print((char)info);

				 info = reader.read();
			 }
			 reader.close();

		 } catch (IOException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
	 }



	
	
	
	/**
	 * Initialise les capacites d'un pokemon et creer la capacite
	 *	
	 */

  public void initialiseFromCsvMoves(String nomfichier) {

	    try {   	
	    	FileReader fichier = new FileReader(nomfichier);
	    	BufferedReader reader = new BufferedReader (fichier) ;
	    	reader.readLine();
	    	
	    	while (reader.ready()) {
	    		String line = reader.readLine();
	    		Scanner s = new Scanner(line).useDelimiter(";");
	    	
	    			nomCap = s.next();
	    			puissance = s.nextInt() ;
	    			String prec = s.next();
	    			precision = Double.parseDouble(prec);
	    			pp = s.nextInt();
	    			numCap= s.nextInt();
	    			String cat = s.next() ;
	    			String typeC = s.next();
	    			categorie = conversionStringCategorie(cat);
	    			typeCap = conversionStringType(typeC);
		
		Capacite cap = new Capacite (nomCap,puissance, precision,pp,numCap,categorie,typeCap);
		capInfo.put(nomCap, cap);
	
		s.close();
	    	}
	    	reader.close();
	    	fichier.close();
	    	} catch (IOException e) {
	    	e.printStackTrace();
	    	}


  }
  
	/**
	 * Recupere une capacite depuis pokeapi avec le nom de la capacite en parametre
	 *	
	 */     
            
  public void recupCapPok(String nom) throws org.json.simple.parser.ParseException, ParseException, IOException {
      

                
                URL hpo = new URL("https://pokeapi.co/api/v2/pokemon/"+nom.toLowerCase()+"/");
                
                
                    
                    HttpURLConnection hpCone = (HttpURLConnection) hpo.openConnection();
                    hpCone.connect();

                    BufferedReader streamReaders = new BufferedReader(new InputStreamReader(hpCone.getInputStream()));
                    StringBuilder responseStrBuilders = new StringBuilder();

                    String inputStrs;
                    
                        while ((inputStrs = streamReaders.readLine()) != null) {
                            responseStrBuilders.append(inputStrs);
                        }
                        
                inputStrs = responseStrBuilders.toString();
                streamReaders.close();

                JSONObject objV2 = (JSONObject) new JSONParser().parse(inputStrs);
   
                    JSONArray moves = (JSONArray) objV2.get("moves");
                  
                    for (Object m : moves) {    
                            JSONObject jsonObjV1 = (JSONObject) m;
                                JSONObject move = (JSONObject) jsonObjV1.get("move");
                                String nameCap = (String) move.get("name");
                            	URL hp1 = new URL("https://pokeapi.co/api/v2/move/"+ move.get("name") +"/");
	                            HttpURLConnection hpCon1 = (HttpURLConnection) hp1.openConnection();
	                            hpCon1.connect();

	                            BufferedReader streamReader1 = new BufferedReader(new InputStreamReader(hpCon1.getInputStream()));
	                            StringBuilder responseStrBuilder1 = new StringBuilder();

	                            String inputStr1;
	                            while ((inputStr1 = streamReader1.readLine()) != null) {
	                                responseStrBuilder1.append(inputStr1);
	                            }
	                            inputStr1 = responseStrBuilder1.toString();
	                            streamReader1.close();

	                            JSONObject obj1 = (JSONObject) new JSONParser().parse(inputStr1);
                            
	                            JSONObject cat = (JSONObject) obj1.get("damage_class");
	                                             
	                            long pp =0;
	                            if(obj1.get("pp")!=null) {
	                            	pp = (long) obj1.get("pp");
	                            }
	                            long puissance=0;
	                            if(obj1.get("power")!=null) {
	                            	puissance = (long) obj1.get("power");
	                            }
	                            long precision = 0;
	                            if(obj1.get("accuracy")!=null) {
	                            	precision= (long) obj1.get("accuracy");
	                            }
                            
	                            long id = (long) obj1.get("id");
	                            
	                            JSONObject getType = (JSONObject) obj1.get("type");
	                            String typ = (String) getType.get("name"); 
	                            String categori = (String) cat.get("name");
                                
	                            Type type = conversionStringType(typ);
	                            Categorie categorie = conversionStringCategorie(categori);                             
                                
                                    JSONArray groupDet = (JSONArray) jsonObjV1.get("version_group_details");
                                    for(Object l : groupDet) {
                                            JSONObject jsonObjV2 = (JSONObject) l;
                                            JSONObject firstG = (JSONObject) jsonObjV2.get("version_group");
                                            String version = (String) firstG.get("name");
                                            long lvlcap = (long) jsonObjV2.get("level_learned_at");
                                            String gRed = "red-blue";
                                           
                                                if(version.contains(gRed) && (categori.contains("physical") || categori.contains("special")) ) {
                                                        Capacite capa = new Capacite(nameCap,puissance,precision,id,pp,categorie,type,lvlcap);
														capDispo.put(capa,lvlcap);
                                                    }
                                            }
                                    }
                           }




	public void initialiseFromCsvMovesRecoveredFromPokeApi(String nomfichier) {
			long lvlcap = 1;
		try {
			FileReader fichier = new FileReader(nomfichier);
			BufferedReader reader = new BufferedReader (fichier) ;
			reader.readLine();

			while (reader.ready()) {
				String line = reader.readLine();
				Scanner s = new Scanner(line).useDelimiter(";");
				String nomCapacite = s.next();
				int puissanceCap = s.nextInt() ;
				String prec = s.next();
				double precisionCap = Double.parseDouble(prec);
				int ppCap = s.nextInt();
				int numeroCap = s.nextInt();
				String categ = s.next() ;
				String typeCap = s.next();
				categorie = conversionStringCategorie(categ);
				Type typeCapac = conversionStringType(typeCap);


				Capacite capacite = new Capacite (nomCapacite,puissanceCap, precisionCap,ppCap,numeroCap,categorie,typeCapac);



				s.close();
			}
			reader.close();
			fichier.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
	/**
	 * Initialise le tableau d'efficaite des types 
	 *	
	 */  
  public void initialiseFromCsvEfficacity(String nomfichier) throws IOException {
  
	  FileReader fichier = new FileReader(nomfichier);
      BufferedReader reader = new BufferedReader(fichier);
      reader.readLine();

      while(reader.ready()) {

          String line = reader.readLine();
          try (Scanner ste = new Scanner(line).useDelimiter(";")) {
			String typeOffensifString = ste.next();
			  Type typeOffensif = conversionStringType(typeOffensifString);

			  for(Type type : Type.values()) {
				  double efficacite = Double.parseDouble(ste.next());
				  List<Type> types = Arrays.asList(typeOffensif, type);
			          tableEffi.put(types, efficacite);
			      }
		} catch (NumberFormatException e) {		
			e.printStackTrace();
		}
      }
      reader.close();
      fichier.close();
  }
  
  
	/**
	 * Renvoie le string mit en parametre en type 'Type'
	 *	
	 */
	
	private Type conversionStringType(String EspeceType) {
		return switch (EspeceType) {
			case "fighting" -> Type.Fighting;
			case "dragon" -> Type.Dragon;
			case "water" -> Type.Water;
			case "electric" -> Type.Electric;
			case "fire" -> Type.Fire;
			case "ice" -> Type.Ice;
			case "bug" -> Type.Bug;
			case "normal" -> Type.Normal;
			case "grass" -> Type.Grass;
			case "poison" -> Type.Poison;
			case "psychic" -> Type.Psychic;
			case "rock" -> Type.Rock;
			case "ground" -> Type.Ground;
			case "ghost" -> Type.Ghost;
			case "flying" -> Type.Flying;
			default -> Type.Fighting;
		};
    }
	
	/**
	 * Renvoie le string mit en parametre en type 'Categorie'
	 *	
	 */	
	private Categorie conversionStringCategorie(String categorie) {

		return switch (categorie) {
			case "physical" -> Categorie.Physique;
			case "special" -> Categorie.Special;
			default -> Categorie.Physique;
		};
    }
	
	/**
	 * Renvoie un tableau de pokemon
	 *	
	 */
	
	

	@Override
	public IPokemon[] engendreRanch() {
        ArrayList<Espece> pokemonsLVL1 = new ArrayList<>();
        Pokemon[] ranch = new Pokemon[6];
        Random random = new Random();

        
        for(Map.Entry<Integer,Espece> pokemons :  pokeInfoIndice.entrySet()) {
            if(pokemons.getValue().getNiveauDepart() == 1 ) {
            	pokemonsLVL1.add(pokemons.getValue());
            }
        }
       
        for(int i = 0; i < ranch.length; i++) {
            Pokemon pokemon = new Pokemon(pokemonsLVL1.get(random.nextInt(pokemonsLVL1.size())));
				ranch[i] = pokemon;
        }

        return ranch;
    }
	
	/**
	 * Renvoie l'IEspece du string mit en parametre
	 *	
	 */
	@Override
	public IEspece getInfo(String nomEspece) {	
		for(Entry<String,Espece>espece : pokeInfoNom.entrySet()) {
			if (espece.getValue().getNom().equals(nomEspece)) {
				 num = espece.getValue().getId();
	    		 nom = espece.getKey();
	    		 pvBase = espece.getValue().getBaseStat().getPV();
	    		 forceBase = espece.getValue().getBaseStat().getForce();
	    		 defBase = espece.getValue().getBaseStat().getDefense();
	    		 specialBase = espece.getValue().getBaseStat().getSpecial();
	    		 vitesseBase = espece.getValue().getBaseStat().getVitesse();	    		 
	    		 types = (Type[]) espece.getValue().getTypes();
	    		 expBase = espece.getValue().getBaseExp();
		         niveauBase = espece.getValue().getNiveauDepart();
	    		 nivMutation = espece.getValue().getNiveauMutation();
	    		 evolution = espece.getValue().getEvolution();
	    		 evPv = espece.getValue().getGainsStat().getPV();
	    		 evForce = espece.getValue().getGainsStat().getForce();
	    		 evDef = espece.getValue().getGainsStat().getDefense();
	    		 evSpecial = espece.getValue().getGainsStat().getSpecial();
	    		 evVitesse = espece.getValue().getGainsStat().getVitesse();	            
	        }
	    }
		Espece esp = new Espece(num,nom,pvBase,forceBase,defBase,specialBase,
				vitesseBase,expBase,evPv,evForce,evDef,evSpecial,evVitesse,types,niveauBase,nivMutation,evolution);		
		esp.afficheEsp();		
	    return esp;	    
	}
	/**
	 * Renvoie 0 , 0.5 , 1 ou 2 en fonction des deux types mit en parametre
	 *	
	 */
	@Override
	public Double getEfficacite(IType attaque, IType defense) {			
		Type att = (Type) attaque;
        Type def = (Type) defense;
        List<Type> types = Arrays.asList(def, att);
        return tableEffi.get(types);
	}
	
	/**
	 * Renvoie la ICapacite du string mit en parametre
	 *	
	 */
	@Override
	public ICapacite getCapacite(String nomCapacite) {
	
		for(Entry<String,Capacite>cap : capInfo.entrySet()) {
			
			if (cap.getValue().getNom().equals(nomCapacite)) {
		 
				nomCap = cap.getValue().getNom();
				puissance = cap.getValue().getPuissance();
				precision = cap.getValue().getPrecision();
				pp = cap.getValue().getPP();
				numCap = (int) cap.getValue().getNum();
				categorie = (Categorie) cap.getValue().getCategorie();
				typeCap = (Type) cap.getValue().getType();
				
			}
		}

		return new Capacite(nomCap,puissance, precision,pp,numCap,categorie,typeCap);
	}
	
	
	  public Capacite[] remplirTabDeCapacite() {
		  
		  	Capacite[] cap = new Capacite[110];
		  	for(int i = 0 ; i<cap.length ; i++)
		  		for(Entry<String, Capacite> capac : capInfo.entrySet()) {
					cap[i] = capac.getValue();
					i++;
			}
				return cap;
	  }
	  
	

	
	/**
	 * Renvoie la ICapacite du int mit en parametre
	 *	
	 */
	@Override
	public ICapacite getCapacite(int nunCapacite) {
		for(Entry<String,Capacite>cap : capInfo.entrySet()) {	
			if (cap.getValue().getNum() == nunCapacite) {
				nomCap = cap.getValue().getNom();
				puissance = cap.getValue().getPuissance();
				precision = cap.getValue().getPrecision();
				pp = cap.getValue().getPP();
				numCap = (int) cap.getValue().getNum();
				categorie = (Categorie) cap.getValue().getCategorie();
				typeCap = (Type) cap.getValue().getType();			
			}
		}

		return new Capacite(nomCap,puissance, precision,pp,numCap,categorie,typeCap);
	}

	/**
	 * Renvoie le niveau de mutation au quel le pokemon peut evoluer
	 *	
	 */	

	public int getNivMutation() {
		return nivMutation;
	}

	/**
	 * Renvoie le HashMap de type Integer et Espece
	 *	
	 */
	public HashMap<Integer, Espece> getPokeInfoIndice() {
		return pokeInfoIndice;
	}

	/**
	 * Renvoie le HashMap de type String et Espece
	 *	
	 */	
	public HashMap<String, Espece> getPokeInfoNom() {
		return pokeInfoNom;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public HashMap<String, Capacite> getCapInfo() {
		return capInfo;
	}
}
