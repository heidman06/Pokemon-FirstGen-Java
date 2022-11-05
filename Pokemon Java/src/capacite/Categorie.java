package capacite;

import interfaces.ICategorie;

import java.util.Objects;

public enum Categorie implements ICategorie{

	Physique("Physique"), Special("Special");
	
	private final String nom;
	/**
	 * initialise la categorie du type Categorie avec le string en parametre
	 *	
	 */	
	Categorie(String nom) {
		this.nom = nom;
	}
	/**
	 * Renvoie true si la categorie est special
	 *	
	 */
	@Override
	public boolean isSpecial() {		
		return Objects.equals(nom, "special");
	}
	/**
	 * Renvoie le nom de la categorie
	 *	
	 */
	@Override
	public String getNom() {	
		return nom;
	}
}
