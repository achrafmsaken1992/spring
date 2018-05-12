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
public class Skill {
	@Id @GeneratedValue
	private Long id;
	private String name;
	private String note;
	private String certificat;
	


	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name="candidateId")
	Candidate candidate;
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
	public Skill() {
		super();
		// TODO Auto-generated constructor stub
	}
	@JsonIgnore
	public Candidate getCandidate() {
		return candidate;
	}
	@JsonSetter
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	
	
	
	

}
