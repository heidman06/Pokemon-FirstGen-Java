package dresseur;

import java.util.Arrays;


import capacite.Capacite;
import interfaces.IAttaque;
import interfaces.ICapacite;
import interfaces.IDresseur;
import interfaces.IPokemon;

import pokemon.Espece;
import pokemon.Pokemon;

public abstract class Dresseur implements IDresseur {

	private String nom;
	private final int niveau = 1;

	private Pokemon pokemonD;
	private Pokemon[] ranch = new Pokemon[6];


	/**
	 * toString de Dresseur
	 */
	@Override
	public String toString() {
		return "Dresseur [nom=" + getNom() + ", niveau=" + niveau + ", ranch=" + Arrays.toString(ranch) + "]";
	}

	/**
	 * Constructeur de Dresseur avec son nom en parametre
	 * 
	 */
	public Dresseur(String nom) {

		this.nom = nom;

	}

	/**
	 * Renvoie true si le dresseur n'a plus de pokemon en vie
	 * 
	 */
	public boolean dresseurEvanoui(){
		boolean eva=true;
		for (Pokemon pokemon : this.ranch) {
			if (!pokemon.estEvanoui()) {
				eva = false;
			}
		}
		return eva;
	}


	/**
	 * Renvoie le nom du dresseur
	 * 
	 */
	@Override
	public String getNom() {

		return nom;
	}

	/**
	 * Renvoie le niveau du dresseur
	 * 
	 */
	@Override
	public int getNiveau() {

		return niveau;
	}


	/**
	 * Renvoie le pokemon a l'indice i rentre en parametre
	 * 
	 */
	@Override
	public IPokemon getPokemon(int i) {

		return ranch[i];
	}

	/**
	 * Enseigne au pokemon en parametre les capacites qu'il peut apprendre
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
					for (Pokemon pokemon : ranch) {
						if (pok.getNom().equals(pokemon.getNom())) {
							boolean tabRempli = true;
							for (int t = 0; t < pok.getCapacitesApprises().length; t++) {
								if (pok.getCapacitesApprises()[t] == null) {
									tabRempli = false;
								}
							}
							if (!tabRempli) {
								pokemon.setCapacite(capacite);

							}

						}

					}

				}

			}
		}
	}


	/**
	 * Soigne tous les pokemon du ranch du dresseur
	 */
	@Override
	public void soigneRanch() {
		for (Pokemon pokemon : ranch) {
			pokemon.soigne();
		}
	}


	/**
	 * Choisit le premier Pokemon pour combattre
	 * 
	 */
	@Override
	public IPokemon choisitCombattant() {

		return ranch[0];
	}


	/**
	 * Choisit le Pokemon pour combattre contre pok
	 * 
	 */
	@Override
	public IPokemon choisitCombattantContre(IPokemon pok) {

		return choisitCombattant();
	}


	/**
	 * Renvoie l'attaque choisit a utiliser contre le pokemon defenseur
	 * 
	 */
	@Override
	public abstract IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur);


	/**
	 * Renvoie le ranch du dresseur
	 * 
	 */
	public Pokemon[] getRanch() {
		return ranch;
	}


	/**
	 * Set un ranch de Pokemon a un dresseur
	 * 
	 */
	public void setRanch(Pokemon[] ranch) {
		this.ranch = ranch;
	}


	/**
	 * Set le nom du dresseur
	 * 
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Renvoie le nombre de Pokemon dans le ranch du dresseur encore en vie 
	 * 
	 */
	public int nbDePokemonRestants(){
		int resultat = 0;

		for (Pokemon pokemon : ranch){
			if (!pokemon.estEvanoui()){
				resultat++;
			}
		}

		return resultat;
	}


	/**
	 * Renvoie le pokemon actif au combat
	 * 
	 */
	public Pokemon getPokemonD() {
		return pokemonD;
	}
	

	/**
	 * Renvoie une copie du ranch du dresseur
	 * 
	 */
	@Override
	public IPokemon[] getRanchCopy(){
		Pokemon copyTabPok[] = getRanch();
		return copyTabPok;
	}


	/**
	 * Set le pokemon actif au combat
	 * 
	 */
	public void setPokemonD(Pokemon pokemonD) {
		this.pokemonD = pokemonD;
	}
}
