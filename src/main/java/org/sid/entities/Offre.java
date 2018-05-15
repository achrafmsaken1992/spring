package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
@Entity
public class Offre implements Serializable{
	
@Id @GeneratedValue
Long id;
String titre;
@Column( length=512)
String description;
String image;
@ColumnDefault("1")
int active;
String entreprise;
@JsonIgnore
@ManyToOne()
@JoinColumn(name="managerId")
Appuser manager;
@OneToMany(mappedBy="offre",cascade=javax.persistence.CascadeType.REMOVE)
private Collection<Qcm> qcms;
public String getEntreprise() {
	return entreprise;
}
public void setEntreprise(String entreprise) {
	this.entreprise = entreprise;
}
@Temporal(TemporalType.TIMESTAMP)

private Date date;
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}




public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getTitre() {
	return titre;
}
public void setTitre(String titre) {
	this.titre = titre;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public int getActive() {
	return active;
}
public void setActive(int active) {
	this.active = active;
}
public Appuser getManager() {
	return manager;
}
public void setManager(Appuser manager) {
	this.manager = manager;
}
}
