package org.sid.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;


@Entity
public class Resume {
	@Id @GeneratedValue
	Long id;
String resume;
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
public String getResume() {
	return resume;
}
public void setResume(String resume) {
	this.resume = resume;
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
