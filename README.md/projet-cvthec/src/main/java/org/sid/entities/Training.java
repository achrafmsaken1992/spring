package org.sid.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;


@Entity
public class Training {
	@Id @GeneratedValue
	Long id;
	private String university;
	private String graduate;
	private String fieldStudy;
	private String date1;
	private String date2;
	private String result;
	
	
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

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getGraduate() {
		return graduate;
	}

	public void setGraduate(String graduate) {
		this.graduate = graduate;
	}

	public String getFieldStudy() {
		return fieldStudy;
	}

	public void setFieldStudy(String fieldStudy) {
		this.fieldStudy = fieldStudy;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	@JsonIgnore
	public Candidate getCandidate() {
		return candidate;
	}
    @JsonSetter
	public void setCandidat(Candidate candidate) {
		this.candidate = candidate;
	}

	public Training(Long id, String university, String graduate, String fieldStudy, String date1, String date2,
			String result, Candidate candidat) {
		super();
		this.id = id;
		this.university = university;
		this.graduate = graduate;
		this.fieldStudy = fieldStudy;
		this.date1 = date1;
		this.date2 = date2;
		this.result = result;
		this.candidate = candidat;
	}

	public Training() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
