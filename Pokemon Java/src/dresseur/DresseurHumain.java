package dresseur;

import capacite.Capacite;
import interfaces.IAttaque;
import interfaces.ICapacite;
import interfaces.IPokemon;
import pictures.ImagesPokemon;
import pokedex.Pokedex;
import pokemon.Espece;
import pokemon.Pokemon;

import java.util.Arrays;
import java.util.Scanner;

public class DresseurHumain extends Dresseur {

	Scanner choixDresseur = new Scanner(System.in);


	private Pokemon pokeDresseur;
	

	/**
	 * Constructeur de DresseurHumain avec un nom en parametre
	 * 
	 */
	public DresseurHumain(String nom) {

		super(nom);
		System.out.println("Bienvenue veuillez tapper votre nom pour commencer : ");
		nom = choixDresseur.nextLine();
		this.setNom(nom);
		System.out.println("Bienvenue : " + nom);


	}


	/**
	 * Choisit le premier Pokemon pour combattre
	 * 
	 */
	@Override
	public Pokemon choisitCombattant() {

		System.out.println("Voici la liste des pokemons dont vous disposez: \n");

		int cptID = 0;

		for (int i = 0; i < getRanch().length; i++) {
			System.out.println(" " + "(" + cptID + ")" + " " + getRanch()[i].getNom() + " \n");
			cptID++;
		}

		pokeDresseur = choisirPok();

		return pokeDresseur;
	}

	


	/**
	 * Renvoie la capacite choisit a utiliser
	 * 
	 */
	public Capacite choisirCapCombat() {
		
		int cptID = 0;
		for (int i = 0; pokeDresseur.getCapacitesApprises().length > i; i++) {
			if(!(pokeDresseur.getCapacitesApprises()[i] ==null))
			System.out.println(" " + "(" + cptID + ")" + " " + pokeDresseur.getCapacitesApprises()[i].getNom() + " " + " PP  " +"(" + pokeDresseur.getCapacitesApprises()[i].getPP() + ")" + " \n");
			cptID++;
		}

		Capacite cap = null;

		while (cap == null) {
			System.out.println("Choisisez une capacite : \t");
			int capID = choixDresseur.nextInt();

			for (int i = 0; i < pokeDresseur.nbCapacitesApprises(); i++){
				if (i == capID && pokeDresseur.getCapacitesApprises()[i].getPP() > 0){
					cap = (Capacite) pokeDresseur.getCapacitesApprises()[i];
				}
			}
		}

		return cap;
	}



	/**
	 * Renvoie un Pokemon choisit
	 * 
	 */
	public Pokemon choisirPok() {
		ImagesPokemon images = new ImagesPokemon();
		Pokemon pokemon = null;

		while (pokemon == null){
			System.out.println("Choisisez un pokemon : \t");
			int pokID = choixDresseur.nextInt();

			for (int i = 0; i < getRanch().length; i++){
				if (i == pokID && !getPokemon(i).estEvanoui()){
					pokemon = (Pokemon) getPokemon(i);
				}
			}
		}

		return pokemon;
	}




	/**
	 * Renvoie l'attaque choisit a utiliser contre le pokemon defenseur
	 * 
	 */
	@Override
	public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur) {
		System.out.println("Veuillez prendre une decision : \n");
		System.out.println(" (0) " + "Echanger le Pokemon \n");
		System.out.println(" (1) " + "Choisir une capacite \n");

		int decisionDresseur = choixDresseur.nextInt();

		Attaque attaque = new Attaque();


		if(decisionDresseur == 0 || attaquant.estEvanoui()){

			System.out.println("Choisissez avec quel Pokémon vous souhaitez faire l'échange, " +getNom()+ " : ");
			int cptID = 0;

			for (int i = 0; i < getRanch().length; i++) {
				System.out.println(" " + "(" + cptID + ")" + " " + getRanch()[i].getNom() + " \n");
				cptID++;
			}

			pokeDresseur = choisirPok();
			attaque.setPokemon(pokeDresseur);
			attaque.setCap(null);
		}
		else{
			System.out.println("-----------------------------------------");
			attaque.setCap(choisirCapCombat());
			attaque.utilise();
			//System.out.println(attaque.getCap().getPP());
		}
		return attaque;

	}


	/**
	 * Set un Pokemon au combat
	 * 
	 */
	public void setPokemon(IPokemon pok) {

		this.pokeDresseur = (Pokemon) pok;

	}


	/**
	 * Renvoie le Pokemon au combat
	 * 
	 */
	public Pokemon getPokemon() {

		return pokeDresseur;
	}


	/**
	 * Enseigne a tous les pokemon du ranch du dresseur des capacites
	 */
	public void initialiseCapacites(){
		Pokedex pokedex = new Pokedex();
		pokedex.initialiseFromCsvMoves("listeCapacites.csv");
		for(int i = 0 ; i < 6 ; i++){
			this.getRanch()[i].apprendCapacites(pokedex.remplirTabDeCapacite());
		}
	}



	/**
	 * Enseigne au Pokemon en parametre les capacites qu'il peut apprendre
	 * 
	 */
	@Override
	public void enseigne(IPokemon pok, ICapacite[] caps) {
		Espece esp = new Espece();
		esp.setNom(pok.getNom().toLowerCase());
		Capacite[] capaDispo = (Capacite[]) esp.getCapSet();
		for (ICapacite cap : caps) {
			for (Capacite capacite : capaDispo) {
				if (cap.getNom().equals(capacite.getNom())) {
					for (int i = 0; i < getRanch().length; i++) {
						if (pok.getNom().equals(getRanch()[i].getNom()) && pok.getNiveau() >= capacite.getLvl()) {

							System.out.println(Arrays.toString(pok.getCapacitesApprises()));
							boolean remp = false;
							while (!remp) {
								System.out.println("Choississez une capacité à enlever à votre pokémon");
								String nomCapacite = choixDresseur.nextLine();
								for (int t = 0; t < pok.getCapacitesApprises().length; t++) {
									if (pok.getCapacitesApprises()[t].getNom().equals(nomCapacite)) {
										try {
											pok.remplaceCapacite(t, capacite);
										} catch (Exception e) {
											throw new RuntimeException(e);
										}
										remp = true;
										System.out.println(Arrays.toString(getRanch()));
									}

								}

							}

						}
					}
				}
			}
		}

	}




}
