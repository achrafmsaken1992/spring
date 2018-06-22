package org.sid.form;

public class OffreStat {
	Long nbr;
	String entreprise;
	public Long getNbr() {
		return nbr;
	}
	public void setNbr(Long nbr) {
		this.nbr = nbr;
	}
	public String getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}
	public OffreStat(Long nbr, String entreprise) {
		super();
		this.nbr = nbr;
		this.entreprise = entreprise;
	}
	
}
