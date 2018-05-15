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
public class Question {
	@Id @GeneratedValue
	Long id;
	String question;
	
	@ManyToOne()
	@JoinColumn(name="QcmId")
	Qcm qcm;
	@OneToMany(mappedBy="question",cascade=javax.persistence.CascadeType.REMOVE)
	private Collection<Suggestion> suggestions;
	@JsonIgnore
	public Collection<Suggestion> getSuggestions() {
		return suggestions;
	}
	@JsonSetter
	public void setSuggestions(Collection<Suggestion> suggestions) {
		this.suggestions = suggestions;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Qcm getQcm() {
		return qcm;
	}
	public void setQcm(Qcm qcm) {
		this.qcm = qcm;
	}
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

}
