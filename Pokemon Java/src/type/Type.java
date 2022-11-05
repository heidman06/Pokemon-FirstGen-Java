package type;

import interfaces.IType;

public enum Type implements IType {
	
	Fighting("fighting"),
	Dragon("dragon"),
	Water("water"),
	Electric("electric"),
	Fire("fire"),
	Ice("ice"),
	Bug("bug"),
	Normal("normal"),
	Grass("grass"),
	Poison("poison"),
	Psychic("psychic"),
	Rock("rock"),
	Ground("ground"),
	Ghost("ghost"),
	Flying("flying"),;

	private final String nom;
	/**
	 * Initialise le nom du type
	 *	
	 */	
	Type(String nom) {
		this.nom=nom;
	}	
	/**
	 * Renvoie le nom du type
	 *	
	 */		
	@Override
	public String getNom() {	
		return nom;
	}
}
