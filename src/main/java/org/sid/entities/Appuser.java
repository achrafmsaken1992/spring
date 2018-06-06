package org.sid.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import javax.persistence.JoinColumn;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

//import lombok.Data;
//import lombok.EqualsAndHashCode;

@Entity


public class Appuser implements Serializable{
	

	@Id @GeneratedValue
	 Long id;

	String nom;
	 String email;
	 String prenom;
	 String nomEntreprise;
	 String token;
	 String tokenExpiration;
	 String tokenRecovery;
	String  dateExpiration;
	String DateNaissance;
	String tel;
	String image;
	String cin;
	 String tokenNotification;
	 @ColumnDefault("0")
	 private int active;
	 @Column( length=512)
	 String resume;
	 public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}




	public int valide;
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


	
	
	 @ManyToMany(fetch=FetchType.EAGER)
		private Collection<AppRole> roles=new ArrayList<>();
	
	 @OneToMany(mappedBy="manager",cascade=javax.persistence.CascadeType.REMOVE)
	 private Collection<Offre> offres;
	 @OneToMany(mappedBy="etudiant",cascade=javax.persistence.CascadeType.REMOVE)
	 private Collection<Note> notes;
	
		public String getTokenNotification() {
			return tokenNotification;
		}
		public void setTokenNotification(String tokenNotification) {
			this.tokenNotification = tokenNotification;
		}

	 
	 @OneToMany(mappedBy="user1",cascade=javax.persistence.CascadeType.REMOVE)
	 private Collection<Messagerie> messageries1;
	 @OneToMany(mappedBy="user2",cascade=javax.persistence.CascadeType.REMOVE)
	 private Collection<Messagerie> messageries2;
	 @JsonIgnore
	 public Collection<Messagerie> getMessageries1() {
		return messageries1;
	}
	 @JsonSetter
	public void setMessageries1(Collection<Messagerie> messageries1) {
		this.messageries1 = messageries1;
	}
	@JsonIgnore
	public Collection<Messagerie> getMessageries2() {
		return messageries2;
	}
	@JsonSetter
	public void setMessageries2(Collection<Messagerie> messageries2) {
		this.messageries2 = messageries2;
	}
	@JsonIgnore
	public Collection<Note> getNotes() {
		return notes;
	}
	 @JsonSetter
	public void setNotes(Collection<Note> notes) {
		this.notes = notes;
	}
	public int getValide() {
		return valide;
	}
	public void setValide(int valide) {
		this.valide = valide;
	}
	
	
	
	
	 /*@ManyToMany(cascade = { 
		        CascadeType.PERSIST, 
		        CascadeType.MERGE
		    })
	
    @JoinTable(name = "messagerie",
    joinColumns = @JoinColumn(name = "user1_id"),
    inverseJoinColumns = @JoinColumn(name = "user2_id")
)
//private List<Tag> tags = new ArrayList<>();
	 private Collection<Appuser> user2;
	
	@ManyToMany(mappedBy = "user2")
	private Collection<Appuser> user1;*/
	
	@OneToMany(mappedBy="etudiant")
	private Collection<Reponse> reponses;
	
	@JsonIgnore
	public Collection<Reponse> getReponses() {
		return reponses;
	}
	@JsonSetter
	public void setReponses(Collection<Reponse> reponses) {
		this.reponses = reponses;
	}
	@OneToMany(mappedBy="etudiant")
	private Collection<Competance> competance; 
	 
	@OneToMany(mappedBy="etudiant")
	private Collection<Experience> experience;
	
	@OneToMany(mappedBy="etudiant")
	private Collection<Formation> formation;
	
	@OneToMany(mappedBy="etudiant")
	
	
	private Collection<Language> langue;
	
	public Collection<Offre> getOffres() {
		return offres;
	}
	public void setOffres(Collection<Offre> offres) {
		this.offres = offres;
	}
	public Collection<Competance> getCompetance() {
		return competance;
	}
	public void setCompetance(Collection<Competance> competance) {
		this.competance = competance;
	}
	public Collection<Experience> getExperience() {
		return experience;
	}
	public void setExperience(Collection<Experience> experience) {
		this.experience = experience;
	}
	public Collection<Formation> getFormation() {
		return formation;
	}
	public void setFormation(Collection<Formation> formation) {
		this.formation = formation;
	}
	public Collection<Language> getLangue() {
		return langue;
	}
	
	public void setLangue(Collection<Language> langue) {
		this.langue = langue;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@JsonIgnore
	public String getToken() {
		return token;
	}
	@JsonSetter
	public void setToken(String token) {
		this.token = token;
	}
	public String getTokenExpiration() {
		return tokenExpiration;
	}
	public void setTokenExpiration(String tokenExpiration) {
		this.tokenExpiration = tokenExpiration;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	

	@Temporal(TemporalType.TIMESTAMP)

	private Date dateCreation;
	 public String getDateNaissance() {
		return DateNaissance;
	}
	public void setDateNaissance(String dateNaissance) {
		DateNaissance = dateNaissance;
	}
	
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getNomEntreprise() {
		return nomEntreprise;
	}
	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}
	
	 private String password;
	
	public Collection<AppRole> getRoles() {
		return roles;
	}
	public void setRoles(Collection<AppRole> roles) {
		this.roles = roles;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	
	public Appuser() {

	}
	 
	

	


}
