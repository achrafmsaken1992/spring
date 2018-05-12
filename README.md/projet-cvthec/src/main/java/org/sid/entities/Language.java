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
	private String Name;
	private int	level;
	
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
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@JsonIgnore
	public Candidate getCandidate() {
		return candidate;
	}
	@JsonSetter
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public Language() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
