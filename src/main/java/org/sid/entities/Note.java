package org.sid.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
@Entity
public class Note {
@Id @GeneratedValue
Long id;
@ManyToOne()
@JoinColumn(name="etudiantId")
Appuser etudiant;
@ManyToOne()
@JoinColumn(name="QcmId")
Qcm qcm;
@ColumnDefault("0")
int reponseCorrect;
@ColumnDefault("0")
int reponseFausse;
public Note() {
	super();
	// TODO Auto-generated constructor stub
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Appuser getEtudiant() {
	return etudiant;
}
public void setEtudiant(Appuser etudiant) {
	this.etudiant = etudiant;
}
public Qcm getQcm() {
	return qcm;
}
public void setQcm(Qcm qcm) {
	this.qcm = qcm;
}
public int getReponseCorrect() {
	return reponseCorrect;
}
public void setReponseCorrect(int reponseCorrect) {
	this.reponseCorrect = reponseCorrect;
}
public int getReponseFausse() {
	return reponseFausse;
}
public void setReponseFausse(int reponseFausse) {
	this.reponseFausse = reponseFausse;
}
}
