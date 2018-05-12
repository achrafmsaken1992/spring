package org.sid.form;

import java.util.Date;

public class TrainingForm {
	private String university;
	private String graduate;
	private String fieldStudy;
	private String date1;
	private String date2;
	private String result;

	private Long candidateId;

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

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	
}
