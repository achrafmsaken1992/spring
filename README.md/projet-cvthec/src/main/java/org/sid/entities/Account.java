package org.sid.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.*;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.Builder.Default;


@Entity

public  class Account implements Serializable{
	@Id @GeneratedValue
	private Long id;
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<AppRole> roles=new ArrayList<>();
	
	@Column(unique=true)

	private String email;
	@Temporal(TemporalType.TIMESTAMP)

	private Date dateCreation;

	private String password;
	
	
	private String tokenRecovery;
	private String dateExpiration;	
	
	@ColumnDefault("0")
	private int active;
public String getTokenRecovery() {
		return tokenRecovery;
	}

	public void setTokenRecovery(String tokenRecovery) {
		this.tokenRecovery = tokenRecovery;
	}

	public String getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(String dateExpiration) {
		this.dateExpiration = dateExpiration;
	}


	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<AppRole> getRoles() {
		return roles;
	}

	public void setRoles(Collection<AppRole> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}
}
