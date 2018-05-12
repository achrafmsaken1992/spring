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
public class Competance {
	@Id @GeneratedValue
	private Long id;
	private String name;
	private String note;
	private String certificat;
	


	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name="etudiantId")
	Appuser etudiant;
	

	public Competance(Long id, String name, String note, String certificat, Appuser etudiant) {
		super();
		this.id = id;
		this.name = name;
		this.note = note;
		this.certificat = certificat;
		this.etudiant = etudiant;
	}
	@JsonIgnore
	public Appuser getEtudiant() {
		return etudiant;
	}
	@JsonSetter
	public void setEtudiant(Appuser etudiant) {
		this.etudiant = etudiant;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}


	
	
	public String getCertificat() {
		return certificat;
	}
	public void setCertificat(String certificat) {
		this.certificat = certificat;
	}
	public Competance() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	


	

}
