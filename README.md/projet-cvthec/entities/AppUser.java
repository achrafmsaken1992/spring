package org.sid.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@DiscriminatorValue("CU")
public class AppUser extends Compte{
	
	
	String first_name_user;
	String last_name_user;
	String phone_user;
	String company_name;
	public String getFirst_name_user() {
		return first_name_user;
	}
	public void setFirst_name_user(String first_name_user) {
		this.first_name_user = first_name_user;
	}
	public String getLast_name_user() {
		return last_name_user;
	}
	public void setLast_name_user(String last_name_user) {
		this.last_name_user = last_name_user;
	}
	public String getPhone_user() {
		return phone_user;
	}
	public void setPhone_user(String phone_user) {
		this.phone_user = phone_user;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public AppUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AppUser(String first_name_user, String last_name_user, String phone_user, String company_name) {
		super();
		this.first_name_user = first_name_user;
		this.last_name_user = last_name_user;
		this.phone_user = phone_user;
		this.company_name = company_name;
	}
	
	
	


	
	
	
	

}
