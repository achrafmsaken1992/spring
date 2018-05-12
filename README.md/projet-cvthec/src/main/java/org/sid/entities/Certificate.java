package org.sid.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Certificate {
	@Id @GeneratedValue
	Long id;
	String link;
	String title;

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

public String getLink() {
	return link;
}

public void setLink(String link) {
	this.link = link;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
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
