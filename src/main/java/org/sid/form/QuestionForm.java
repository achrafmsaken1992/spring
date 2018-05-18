package org.sid.form;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.sid.entities.Qcm;

public class QuestionForm {
	Long id;
	String question;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	Long qcm;
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Long getQcm() {
		return qcm;
	}
	public void setQcm(Long qcm) {
		this.qcm = qcm;
	}
}
