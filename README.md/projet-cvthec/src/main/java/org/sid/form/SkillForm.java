package org.sid.form;

public class SkillForm {
	private Long id;
	private String name;
	private String note;
	private String certificat;
	private Long candidateId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCertificat() {
		return certificat;
	}
	public void setCertificat(String certificat) {
		this.certificat = certificat;
	}
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	
}
