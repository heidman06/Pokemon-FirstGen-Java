package pokemon;

import dresseur.Attaque;
import interfaces.IAttaque;
import interfaces.ICapacite;
import interfaces.IEspece;
import interfaces.IPokemon;
import interfaces.IStat;
import pokedex.Pokedex;

import java.io.IOException;

import java.util.Arrays;
import java.util.Map.Entry;
import java.util.Objects;

import capacite.Capacite;


public class Pokemon implements IPokemon {

	private final int id;
	private final String nom;
	private int niveau;
	private double exp;
	private int pv;
	private int pourcentagePV;
	private int force;
	private int defense;
	private int special;
	private int vitesse;
	private int pvDv;
	private int forceDv;
	private int defenseDv;
	private int specialDv;
	private int vitesseDv;
	private int pvEv;
	private int forceEv;
	private int defenseEv;
	private int specialEv;
	private int vitesseEv;
	private int pvMax;

	private Capacite[] capacite;

	private int tailleCapTab = 0;

	private final Espece espece;

	/**
	 * Constructeur de Pokemon avec en parametre une espece
	 * 
	 */
	public Pokemon(Espece espece) {

		this.nom = espece.getNom();
		this.espece = espece;

		this.id = espece.getId() ;

		this.niveau = espece.getNiveauDepart();

		setDVStat();
		gagneStats();
		soigne();
		int tailleMaxTabCap = 4;
		this.capacite = new Capacite[tailleMaxTabCap];
	}


	/**
	 * Constructeur de Pokemon avec en parametre une espece et le nom du pokemon
	 * 
	 */
	public Pokemon(Espece espece, String nom) {

		this.espece = espece;
		this.nom = nom;
		this.id = espece.getId() ;


		this.niveau = espece.getNiveauDepart();
		setDVStat();
		gagneStats();
		getPourcentagePV();
		soigne();

	}

	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", nom=" + nom + ", niveau=" + niveau + ", exp=" + exp + ", pv=" + pv
				+ ", pourcentagePV=" + pourcentagePV + ", force=" + force + ", defense=" + defense + ", special="
				+ special + ", vitesse=" + vitesse + ", pvDv=" + pvDv + ", forceDv=" + forceDv + ", defenseDv="
				+ defenseDv + ", specialDv=" + specialDv + ", vitesseDv=" + vitesseDv + ", pvEv=" + pvEv + ", forceEv="
				+ forceEv + ", defenseEv=" + defenseEv + ", specialEv=" + specialEv + ", vitesseEv=" + vitesseEv
				+ ", pvMax=" + pvMax + " \n  capacite=" + Arrays.toString(capacite) + "] \n";
	}

	private void gagneStats() {

		setPvMax(gainPV());

		force = gainStat(getEspece().getBaseStat().getForce(), forceDv, forceEv);
		defense = gainStat(getEspece().getBaseStat().getDefense(), defenseDv, defenseEv);
		vitesse = gainStat(getEspece().getBaseStat().getVitesse(), vitesseDv, vitesseEv);
		special = gainStat(getEspece().getBaseStat().getSpecial(), specialDv, specialEv);
	}

	public int gainStat(int baseStat, int dV, int eV) {

		return (((2 * (baseStat + dV) + eV / 4) * niveau) / 100) + 5;

	}

	private int gainPV() {
		return (((2 * (getEspece().getBaseStat().getPV() + pvDv) + pvEv / 4) * niveau) / 100) + niveau + 10;
	}

	@Override
	public IStat getStat() {

		return new IStat() {
			@Override
			public int getPV() {
				return pvMax;
			}

			@Override
			public int getForce() {

				return force;
			}

			@Override
			public int getDefense() {

				return defense;
			}

			@Override
			public int getSpecial() {

				return special;
			}

			@Override
			public int getVitesse() {

				return vitesse;
			}

			@Override
			public void setPV(int i) {

				pv = i;

			}

			@Override
			public void setForce(int i) {

				force = i;
			}

			@Override
			public void setDefense(int i) {

				defense = i;
			}

			@Override
			public void setVitesse(int i) {

				vitesse = i;
			}

			@Override
			public void setSpecial(int i) {

				special = i;

			}

		};
	}

	/**
	 * Renvoie l'experience du pokemon
	 * 
	 */
	@Override
	public double getExperience() {
		return exp;
	}

	/**
	 * Renvoie le niveau du pokemon
	 * 
	 */
	@Override
	public int getNiveau() {
		return niveau;
	}

	/**
	 * Renvoie l'id du pokemon
	 * 
	 */
	@Override
	public int getId() {
		return id;
	}

	/**
	 * Renvoie le nom du pokemon
	 * 
	 */
	@Override
	public String getNom() {
		return nom;
	}

	/**
	 * Renvoie le poucentage de pv restant du pokemon
	 * 
	 */
	@Override
	public double getPourcentagePV() {
		return pourcentagePV;
	}

	/**
	 * Renvoie l'espece du pokemon
	 * 
	 */
	@Override
	public IEspece getEspece() {
		return espece;
	}

	/**
	 * Renvoie l'espece de l'evolution du pokemon
	 * 
	 */
	@Override
	public void vaMuterEn(IEspece esp) {
		Pokedex pok = new Pokedex();
		try {
			pok.initializeFromCSVRecupInfoEspece("listePokemon1G.csv");
		} catch (IOException e) {

			e.printStackTrace();
		}
		for (Entry<String, Espece> espece : pok.getPokeInfoNom().entrySet()) {

			if (espece.getValue().getNom().equals(esp.getNom()))
				espece.getValue().afficheEsp();
		}

	}

	/**
	 * Renvoie les capacites du pokemon
	 * 
	 */
	@Override
	public ICapacite[] getCapacitesApprises() {

		
		return capacite;
	}

	/**
	 * Affiche la capacite apprise (capacite en parametre) du pokemon
	 * 
	 */

	@Override
	public void apprendCapacites(ICapacite[] caps) {
		Espece esp = new Espece();
		esp.setNom(nom.toLowerCase());
		int tailleTabCapacite = 0;

		Capacite[] capacitesAuDepart = (Capacite[]) esp.getCapSet();

		for (ICapacite cap : caps)
			for (Capacite value : capacitesAuDepart) {
				if ((Objects.equals(value.getNom(), cap.getNom())) && (tailleTabCapacite < 4)) {
					setCapacite(value);
					tailleTabCapacite++;
				}
			}
	}
		

	

	/**
	 * remplace une des capacites se trouvant a l'indice i du pokemon en une autre
	 * passee en parametre
	 * 
	 */
	@Override
	public void remplaceCapacite(int i, ICapacite cap) {
		this.getCapacitesApprises()[i] = cap;
	}

	/**
	 * Augmente l'experience du pokemon en fonction du pokemon (mit en parametre)
	 * battu
	 * 
	 */
	@Override
	public void gagneExperienceDe(IPokemon pok) {
		double expGagnee = (1.5 * pok.getNiveau() * pok.getEspece().getBaseExp()) / 7;
		exp += expGagnee;
		System.out.println(this.getNom() + " a gagne " + expGagnee + " points d'exp");

		if(peutChangerNiveau()) {
			niveau++;
			gagneStats();
		}

		System.out.println(exp);
	}

	private boolean peutChangerNiveau() {
		return (exp >= 0.8 * Math.pow(niveau+1, 3)) && niveau < 100;
	}

	/**
	 * Reduit les pv du pokemon en fonction du pokemon attaquant et de l'attaque
	 * envoy�e
	 * 
	 */

	@Override
	public void subitAttaqueDe(IPokemon pokAttaquant, IAttaque atk) {
		this.pourcentagePV -= ((Attaque) atk).getCap().calculeDommage(pokAttaquant, this);
		//System.out.println("Le pokemon " + this.getNom() + " a pris " + ((Attaque) atk).getCap().calculeDommage(pokAttaquant,this) + " point de degats");
		if(this.estEvanoui()) {
			pokAttaquant.gagneExperienceDe(this);
		}
	}

	/**
	 * Renvoie true si les pv du pokemon sont a 0
	 * 
	 */
	@Override
	public boolean estEvanoui() {
		return pourcentagePV <= 0;
	}

	/**
	 * Augmente le niveau du pokemon
	 * 
	 */
	@Override
	public boolean aChangeNiveau() {
		return this.niveau >= this.espece.getNiveauMutation();
	}

	/**
	 * Renvoie true si le pokemon peut evoluer sinon revoie false
	 * 
	 */

	@Override
	public boolean peutMuter() {
		return getEspece().getEvolution(niveau) != null;

	}

	/**
	 * met les pv du pokemon au max
	 * 
	 */

	@Override
	public void soigne() {

		this.pourcentagePV = this.pvMax;
	}

	/**
	 * Genere les Dv du pokemon
	 * 
	 */
	private void setDVStat() {
		String pVBinaire = "";

		this.forceDv = (int) (Math.random() * (16));
		this.defenseDv = (int) (Math.random() * (16));
		this.vitesseDv = (int) (Math.random() * (16));
		this.specialDv = (int) (Math.random() * (16));

		pVBinaire += dernierBit(forceDv);
		pVBinaire += dernierBit(defenseDv);
		pVBinaire += dernierBit(vitesseDv);
		pVBinaire += dernierBit(specialDv);

		this.pvDv = Integer.parseInt(pVBinaire, 2);
	}

	private String dernierBit(int i) {
		if (i % 2 == 0) {
			return "0";
		} else {
			return "1";
		}
	}

	/**
	 * Renvoie les pv max du pokemon
	 * 
	 */
	public int getPvMax() {
		return pvMax;
	}

	/**
	 * Set les pv max du pokemon
	 * 
	 */

	public void setPvMax(int pvMax) {
		this.pvMax = gainPV();

	}

	public Capacite[] getCapacite() {
		return capacite;
	}

	public void setCapacite(Capacite[] capacite) {
		this.capacite = capacite;
	}

	public void setCapacite(Capacite caps) {
		this.capacite[tailleCapTab] = caps;
		tailleCapTab++;
	}

	public int nbCapacitesApprises(){
		int resultat = 0;

		for (int i = 0; i < 4; i++){
			if (getCapacitesApprises()[i] != null){
				resultat++;
			}
		}

		return resultat;
	}
}
