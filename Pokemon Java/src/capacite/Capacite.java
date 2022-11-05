package capacite;

import java.io.FileNotFoundException;
import java.io.IOException;

import dresseur.Attaque;
import interfaces.*;
import pokedex.Pokedex;
import type.Type;

public class Capacite implements ICapacite {

	private String nom;
	private long puissance;
	private double precision;
	private long pp;
	private long num;
	private Categorie categorie;
	private Type type;
	private long lvl;
	private long ppMax;
	/**
	 * Constructeur de capacite tout a saisir
	 *	
	 */
	public Capacite(String nom, long puissance, double precision, long num, long pp, Categorie categorie , Type type , long lvl) {
		this.nom = nom;
		this.puissance = puissance;
		this.precision = precision;
		this.pp = pp;
		this.num = num;
		this.categorie = categorie;
		this.type = type;
		this.lvl = lvl;
	}

	public Capacite (){

	}
	/**
	 * Constructeur de capacite tout a saisir sauf level
	 *	
	 */	
	public Capacite(String nom, long puissance, double precision, long num, long pp, Categorie categorie , Type type) {
		this.nom = nom;
		this.puissance = puissance;
		this.precision = precision;
		this.pp = pp;
		this.num = num;
		this.categorie = categorie;
		this.type = type;
	}
	/**
	 * Constructeur de capacite a partir d'une capacite en parametre
	 *	
	 */
	public Capacite(ICapacite capacite) {
		this.nom = capacite.getNom();
		this.puissance = capacite.getPuissance();
		this.precision = capacite.getPrecision();
		this.pp = capacite.getPP();
		this.num = 0;
		this.categorie = (Categorie) capacite.getCategorie();
		this.type = (Type) capacite.getType();
	}
	
	
	
	/**
	 * toString capacite
	 *	
	 */
	@Override
	public String toString() {
		return "Capacite [nom=" + nom + ", puissance=" + puissance + ", precision=" + precision + ", pp=" + pp
				+ ", num=" + num + ", categorie=" + categorie + ", type=" + type + ", lvl=" + lvl + "]";
	}
	/**
	 * Affichage structure d'une capacite
	 *	
	 */	
	public void afficheCap() {	
    System.out.println("Capacite :  \n");
    System.out.println(" ------------------------------------------------");
	System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n", "Nom","Puissance","Precision","PP", "Num" ,"Categorie" , "Type" , "Niveau");
	System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n", nom,puissance,precision,pp,num,categorie,type,lvl);
	System.out.println(" --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");   
	}
	/**
	 * Renvoie les dommages causes par une attaque en fonction du pokemon lanceur et du receveur
	 *	
	 */	
	@Override
	public int calculeDommage(IPokemon lanceur, IPokemon receveur) {
		
			double degat;
			if(precision <= 0.3 ) {
				return receveur.getStat().getPV();
			}
			
			if(categorie.isSpecial()) {
				degat = (((lanceur.getNiveau() * 0.4 + 2) * lanceur.getStat().getSpecial() * puissance)  /  (receveur.getStat().getSpecial() * 50) + 2);
			}else {
				degat = (((lanceur.getNiveau() * 0.4 + 2) * lanceur.getStat().getForce() * puissance)  /  (receveur.getStat().getDefense() * 50) + 2);
			}
			
			try {
				degat *= coefficientMultiplicateur(lanceur, receveur);
			} catch (IOException e) {

				e.printStackTrace();
			}

		return (int)degat;
		}
		
	
	/**
	 * Renvoie le coefficient multiplicateur necessaire pour le calcul des dommages
	 *	
	 */	
		private double coefficientMultiplicateur(IPokemon lanceur, IPokemon receveur) throws IOException{
			double stab = 1;
			double produitAleatoire;
			double efficacite;
			Pokedex pokedex = new Pokedex();
			pokedex.initialiseFromCsvEfficacity("efficacites.csv");
			
			if(lanceur.getEspece().getTypes()[1] != null) {
				if(lanceur.getEspece().getTypes()[0].equals(type) || lanceur.getEspece().getTypes()[1].equals(type)) {
					stab = 1.5;
				}
			}else {
				if(lanceur.getEspece().getTypes()[0].equals(type)) {
					stab = 1.5;
				}
			}
		
			produitAleatoire = 0.85 + (Math.random() * (1 - 0.85));
			

			efficacite = pokedex.getEfficacite(this.type, receveur.getEspece().getTypes()[0]);
			if(receveur.getEspece().getTypes()[1] != null) {
				efficacite *= pokedex.getEfficacite(this.type, receveur.getEspece().getTypes()[1]);
			}

			return stab * produitAleatoire * efficacite;	
	}
		/**
		 * Retire une pp a la capacite (simule l'utilisation)
		 *	
		 */
	@Override
	public void utilise() {	
		  this.pp-=1;
	}
	/**
	 * Renvoie le nom de la capacite
	 *	
	 */
	@Override
	public String getNom() {	
		return nom;
	}
	/**
	 * Renvoie la precision de la capacite
	 *	
	 */
	@Override
	public double getPrecision() {	
		return precision;
	}
	/**
	 * Renvoie la puissance de la capacite
	 *	
	 */
	@Override
	public int getPuissance() {		
		return (int) puissance;
	}
	/**
	 * Renvoie les pp(nombre d'utilisation) de la capacite
	 *	
	 */
	@Override
	public int getPP() {		
		return (int) pp;
	}
	/**
	 * Remet les pp a leur valeur d'origine 
	 *	
	 */
	@Override
	public void resetPP() {		
		this.pp = this.ppMax;
	}
	/**
	 * Renvoie la categorie de la capacite
	 *	
	 */
	@Override
	public ICategorie getCategorie() {			
		return categorie;
	}
	/**
	 * Renvoie le type de la capacite
	 *	
	 */
	@Override
	public IType getType() {		
		return type;
	}
	/**
	 * Renvoie le numero de la capacite
	 *	
	 */
	public long getNum() {
		return  num;
	}
	/**
	 * Renvoie le niveau de la capacite
	 *	
	 */
	public long getLvl() {
		return lvl;
	}
	/**
	 * set le niveau de la capacite
	 *	
	 */
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
}