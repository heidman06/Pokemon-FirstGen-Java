package dresseur;




import java.util.ArrayList;



import capacite.Capacite;
import interfaces.*;
import pokemon.Espece;
import pokemon.Pokemon;

public class DresseurIA extends Dresseur {

	ArrayList<String> nomDresseur = new ArrayList<>();
	
	private Pokemon pokeIA;
	private final StrategyAleatoire strat ;
	

	/**
	 * Constructeur de DresseurIA avec un nom et une strategie en parametre
	 */
	public DresseurIA(String nom, StrategyAleatoire strat) {
		super(nom);
		this.strat = new StrategyAleatoire(this);

	}


	/**
	 * Renvoie le pokemon au combat de l'IA
	 * 
	 */
	public Pokemon getPokemonIA () {
		return pokeIA;
	}



	/**
	 * Renvoie un nom aleatoire pour l'IA
	 * 
	 */
	public String generateRandomName() {
		
		nomDresseur.add("Pablo");
		nomDresseur.add("Guillaume");
		
		int randomIndex = (int) (Math.random() * nomDresseur.size());
		
		return nomDresseur.get(randomIndex);
	}


	/**
	 * Set un pokemon au combat 
	 * 
	 */
	public void setPokemon(IPokemon pok) {
		
		this.pokeIA = (Pokemon) pok;
		
	}


	/**
	 * Renvoie un pokemon aleatoirement qui va etre au combat
	 * 
	 */
	public IPokemon echangeCombattant() {

		boolean different = false;
		while(!different) {
			int randomIndex = (int) (Math.random() * getRanch().length);
			if(!getRanch()[randomIndex].getNom().equals(pokeIA.getNom())) {
				setPokemon(getRanch()[randomIndex]);
				different = true;
			}
		}
		return pokeIA;
	}


	/**
	 * Enseigne au Pokemon en parametre les capacites qu'il peut apprendre
	 * 
	 */
	public void enseigne(IPokemon pok, ICapacite[] caps) {
		Espece esp = new Espece();
		esp.setNom(pok.getNom().toLowerCase());
		Capacite[] capacites = (Capacite[]) esp.getCapSet();
		for (ICapacite cap : caps) {
			for (Capacite capacite : capacites) {
				if (cap.getNom().equals(capacite.getNom())) {
					for (int i = 0; i < getRanch().length; i++) {
						if (pok.getNom().equals(getRanch()[i].getNom()) && pok.getNiveau() >= capacite.getLvl()) {

							int randomIndex = (int) (Math.random() * pok.getCapacitesApprises().length);
							try {
								pok.remplaceCapacite(randomIndex, capacite);
							} catch (Exception e) {
								throw new RuntimeException(e);
							}
						}

					}

				}

			}
		}
			}


	/**
	 * Renvoie l'attaque choisit aleatoirement a utiliser contre le pokemon defenseur
	 * 
	 */
	@Override
	public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur) {
		return strat.choisitAttaque(attaquant,defenseur);
	}
}







	







