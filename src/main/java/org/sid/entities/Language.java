package org.sid.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Language {
	
	@Id @GeneratedValue
	private Long id;
	private String nom;
	private int	niveau;
	
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name="etudiantId")
	Appuser etudiant;
	

	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getNiveau() {
		return niveau;
	}
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	@JsonIgnore
	public Appuser getEtudiant() {
		return etudiant;
	}
	@JsonSetter
	public void setEtudiant(Appuser etudiant) {
		this.etudiant = etudiant;
	}
	public Language(Long id, String nom, int niveau, Appuser etudiant) {
		super();
		this.id = id;
		this.nom = nom;
		this.niveau = niveau;
		this.etudiant = etudiant;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	

	public Language() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
