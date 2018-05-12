package org.sid.entities;


import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;





@Entity

public class AppUser extends Account{
	
	
	
	String firstName;
	String lastName;
	String phoneUser;
	
	String companyName;
	String photo;
	


	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@ManyToOne
	@JoinColumn(name="userCompanyId")
	AppUser appUser;
	
	@OneToMany(mappedBy="appUser")
	private Collection<Candidate> candidate;

	@OneToMany(mappedBy="appUser")
	private Collection<AppUser> rhUser;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneUser() {
		return phoneUser;
	}

	public void setPhoneUser(String phoneUser) {
		this.phoneUser = phoneUser;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Collection<Candidate> getCandidate() {
		return candidate;
	}

	public void setCandidate(Collection<Candidate> candidate) {
		this.candidate = candidate;
	}

	public Collection<AppUser> getRhUser() {
		return rhUser;
	}

	public void setRhUser(Collection<AppUser> rhUser) {
		this.rhUser = rhUser;
	}
	
	
	
	


	
	
	
	

}
