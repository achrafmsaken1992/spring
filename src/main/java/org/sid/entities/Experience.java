package org.sid.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Experience {
	@Id @GeneratedValue
	Long id;
	private String description;
	private String entreprise;
	private String lieu;
	private String date1;
	private String date2;
	private String titre;
	private String motCle;
  	public String getMotCle() {
		return motCle;
	}


	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}


	public String getEntreprise() {
		return entreprise;
	}


	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}


	public String getLieu() {
		return lieu;
	}


	public void setLieu(String lieu) {
		this.lieu = lieu;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}

@JsonIgnore
	public Appuser getEtudiant() {
		return etudiant;
	}

@JsonSetter
	public void setEtudiant(Appuser etudiant) {
		this.etudiant = etudiant;
	}


	@JsonIgnore
    @ManyToOne()
	@JoinColumn(name="etudiantId")
	Appuser etudiant;

	


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}




	public String getDate1() {
		return date1;
	}


	public void setDate1(String date1) {
		this.date1 = date1;
	}


	public String getDate2() {
		return date2;
	}


	public void setDate2(String date2) {
		this.date2 = date2;
	}


    
}
