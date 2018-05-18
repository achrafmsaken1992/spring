package org.sid.form;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.sid.entities.Offre;

public class QcmForm {
	Long id;
	int duree;
	String titre;
	Long offre;
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Long getOffre() {
		return offre;
	}
	public void setOffre(Long offre) {
		this.offre = offre;
	}
	
}
