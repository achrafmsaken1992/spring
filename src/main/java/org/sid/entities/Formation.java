package org.sid.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;


@Entity
public class Formation {
	@Id @GeneratedValue
	Long id;
	private String universite;
	private String section;
	private String specialite;
	private String date1;
	private String date2;
	private String result;
	
	
	public String getUniversite() {
		return universite;
	}

	public void setUniversite(String universite) {
		this.universite = universite;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	@JsonIgnore
	public Appuser getEtudiant() {
		return etudiant;
	}
@JsonSetter
	public void setEtudiant(Appuser etudiant) {
		this.etudiant = etudiant;
	}


	public Formation(Long id, String universite, String section, String specialite, String date1, String date2,
		String result, Appuser etudiant) {
	super();
	this.id = id;
	this.universite = universite;
	this.section = section;
	this.specialite = specialite;
	this.date1 = date1;
	this.date2 = date2;
	this.result = result;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	

	public Formation() {
		super();
		// TODO Auto-generated constructor stub
	}



}
