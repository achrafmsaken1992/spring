package org.sid.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;


@Entity
public class Qcm {
@Id @GeneratedValue
Long id;
int duree;
String titre;


@ManyToOne()
@JoinColumn(name="OffreId")
Offre offre;

@OneToMany(mappedBy="qcm",cascade=javax.persistence.CascadeType.REMOVE)
private Collection<Question> questions;
@JsonIgnore
public Collection<Question> getQuestions() {
	return questions;
}
@JsonSetter
public void setQuestions(Collection<Question> questions) {
	this.questions = questions;
}

public Qcm() {
	super();
	// TODO Auto-generated constructor stub
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public int getDuree() {
	return duree;
}
public void setDuree(int duree) {
	this.duree = duree;
}
public String getTitre() {
	return titre;
}
public void setTitre(String titre) {
	this.titre = titre;
}

public Offre getOffre() {
	return offre;
}

public void setOffre(Offre offre) {
	this.offre = offre;
}


}
