package org.sid.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Suggestion {
	@Id @GeneratedValue
Long id;
String suggestion;
int reponse;
@ManyToOne()
@JoinColumn(name="QuestionId")
Question question;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getSuggestion() {
	return suggestion;
}
public void setSuggestion(String suggestion) {
	this.suggestion = suggestion;
}
public int getReponse() {
	return reponse;
}
public void setReponse(int reponse) {
	this.reponse = reponse;
}
public Question getQuestion() {
	return question;
}
public void setQuestion(Question question) {
	this.question = question;
}
}
