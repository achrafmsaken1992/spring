package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.Table;


import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
@Table(name = "candidat")
public class Candidate implements Serializable{
	
	@Id @GeneratedValue
	private	Long candidateId;
	private String lastName;
	private String firstName;
	private String email;
	private String tel;
	private String title;

	private String adresse;
	private String cv;
	private String photo;
	public String getPhoto() {
		return photo;
	}
















	public void setPhoto(String photo) {
		this.photo = photo;
	}


	@OneToMany(mappedBy="candidate",cascade=javax.persistence.CascadeType.REMOVE)
	private Collection<Experience> experience;	
	
	

	public Candidate(Long candidateId, String lastName, String firstName, String email, String tel, String title,
			String adresse, String cv, String photo, Collection<Experience> experience, Collection<Training> training,
			Collection<Skill> skill, Collection<Language> langue, Collection<Project> project,
			Collection<Resume> resume, Collection<Certificate> certificate, AppUser appUser) {
		super();
		this.candidateId = candidateId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.tel = tel;
		this.title = title;
		this.adresse = adresse;
		this.cv = cv;
		this.photo = photo;
		this.experience = experience;
		this.training = training;
		this.skill = skill;
		this.langue = langue;
		this.project = project;
		this.resume = resume;
		this.certificate = certificate;
		this.appUser = appUser;
	}
















	public Candidate(Long candidateId, String lastName, String firstName, String email, String tel, String title,
			String adresse, String cv, Collection<Experience> experience, Collection<Training> training,
			Collection<Skill> skill, Collection<Language> langue, AppUser appUser) {
		super();
		this.candidateId = candidateId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.tel = tel;
		this.title = title;
		this.adresse = adresse;
		this.cv = cv;
		this.experience = experience;
		this.training = training;
		this.skill = skill;
		this.langue = langue;
		this.appUser = appUser;
	}
















	public Long getCandidateId() {
		return candidateId;
	}
















	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}


	@OneToMany(mappedBy="candidate",cascade=javax.persistence.CascadeType.REMOVE)
	private Collection<Training> training;	


	@OneToMany(mappedBy="candidate",cascade=javax.persistence.CascadeType.REMOVE)
	private Collection<Skill> skill;


	@OneToMany(mappedBy="candidate",cascade=javax.persistence.CascadeType.REMOVE)
	private Collection<Language> langue;
	
	@OneToMany(mappedBy="candidate",cascade=javax.persistence.CascadeType.REMOVE)
	private Collection<Project> project;
	
	@OneToMany(mappedBy="candidate",cascade=javax.persistence.CascadeType.REMOVE)
	private Collection<Resume> resume;
	
	@OneToMany(mappedBy="candidate",cascade=javax.persistence.CascadeType.REMOVE)
	private Collection<Certificate> certificate;

	@ManyToOne
	@JoinColumn(name="appUserId")
	private AppUser appUser;

	public Collection<Project> getProject() {
		return project;
	}
















	public void setProject(Collection<Project> project) {
		this.project = project;
	}
















	public Collection<Resume> getResume() {
		return resume;
	}
















	public void setResume(Collection<Resume> resume) {
		this.resume = resume;
	}
















	public Collection<Certificate> getCertificate() {
		return certificate;
	}
















	public void setCertificate(Collection<Certificate> certificate) {
		this.certificate = certificate;
	}
















	public Candidate() {
		super();
		// TODO Auto-generated constructor stub
	}
















	public String getLastName() {
		return this.lastName;
	}





	public void setLastName(String lastName) {
		this.lastName = lastName;
	}





	public String getFirstName() {
		return firstName;
	}





	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}





	public String getEmail() {
		return email;
	}





	public void setEmail(String email) {
		this.email = email;
	}





	public String getTel() {
		return tel;
	}





	public void setTel(String tel) {
		this.tel = tel;
	}





	public String getTitle() {
		return title;
	}





	public void setTitle(String title) {
		this.title = title;
	}





	public String getAdresse() {
		return adresse;
	}





	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}





	public String getCv() {
		return cv;
	}





	public void setCv(String cv) {
		this.cv = cv;
	}





	public Collection<Experience> getExperience() {
		return experience;
	}





	public void setExperience(Collection<Experience> experience) {
		this.experience = experience;
	}





	public Collection<Training> getTraining() {
		return training;
	}





	public void setTraining(Collection<Training> training) {
		this.training = training;
	}





	public Collection<Skill> getSkill() {
		return skill;
	}





	public void setSkill(Collection<Skill> skill) {
		this.skill = skill;
	}





	public Collection<Language> getLangue() {
		return langue;
	}





	public void setLangue(Collection<Language> langue) {
		this.langue = langue;
	}





	@JsonIgnore
	public AppUser getAppUser() {
		return appUser;
	}


	@JsonSetter
	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}















	
	
	

}
