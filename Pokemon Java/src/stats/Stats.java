package stats;

import interfaces.IStat;

public class Stats implements IStat{

	private int basePv;
	private int baseForce;
	private int baseDef;
	private int baseSpecialDef;
	private int baseVitesse;
	private int evPV;
	private int evForce;
	private int evDef;
	private int evSpecial;
	private int evVitesse;
	
	public Stats () {
		
	}
	/**
	 * toString de Stats
	 *	
	 */		
	@Override
	public String toString() {
		return "Stats [basePv=" + basePv + ", baseForce=" + baseForce + ", baseDef=" + baseDef + ", baseSpecialDef="
				+ baseSpecialDef + ", baseVitesse=" + baseVitesse + ", evPV=" + evPV + ", evForce=" + evForce
				+ ", evDef=" + evDef + ", evSpecial=" + evSpecial + ", evVitesse=" + evVitesse + "]";
	}
	/**
	 * Renvoie les pv du Type Stats
	 *	
	 */	
	@Override
	public int getPV() {		
		return basePv;
	}
	/**
	 * Renvoie la force du Type Stats
	 *	
	 */	
	@Override
	public int getForce() {		 
		return  baseForce;
	}
	/**
	 * Renvoie la defense du Type Stats
	 *	
	 */	
	@Override
	public int getDefense() {		 
		return baseDef;
	}
	/**
	 * Renvoie le special du Type Stats
	 *	
	 */	
	@Override
	public int getSpecial() {	 
		return baseSpecialDef;
	}
	/**
	 * Renvoie la vitesse du Type Stats
	 *	
	 */	
	@Override
	public int getVitesse() {	 
		return  baseVitesse;
	}
	/**
	 * initialise les pv du Type Stats
	 *	
	 */	
	@Override
	public void setPV(int i) {		 
		basePv = i;
	}
	/**
	 * initialise la force du Type Stats
	 *	
	 */
	@Override
	public void setForce(int i) {		 
		baseForce = i;
	}
	/**
	 * initialise la defense du Type Stats
	 *	
	 */
	@Override
	public void setDefense(int i) {		 
		baseDef = i;
	}
	/**
	 * initialise la vitesse du Type Stats
	 *	
	 */
	@Override
	public void setVitesse(int i) {
		baseVitesse = i;
	}
	/**
	 * initialise le special du Type Stats
	 *	
	 */
	@Override
	public void setSpecial(int i) {		 
		baseSpecialDef = i;
	}
}
