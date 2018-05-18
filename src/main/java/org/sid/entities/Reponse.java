package org.sid.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reponse {
	@Id @GeneratedValue
	Long id;
	@ManyToOne()
	@JoinColumn(name="etudiantId")
	Appuser etudiant;
	@ManyToOne()
	@JoinColumn(name="questionId")
	Question question;
	
	int resultat;

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

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getResultat() {
		return resultat;
	}

	public void setResultat(int resultat) {
		this.resultat = resultat;
	}

	public Reponse() {
		super();
		// TODO Auto-generated constructor stub
	}


}
