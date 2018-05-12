package org.sid.form;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class RegisterForm {
	private String nom;
	private String prenom;
	private String email;
	private String nomEntreprise;
	private  String DateNaissance;
private Date dateCreation;
private String tel;
private String image;
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
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
public String getNomEntreprise() {
	return nomEntreprise;
}
public void setNomEntreprise(String nomEntreprise) {
	this.nomEntreprise = nomEntreprise;
}
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
}
