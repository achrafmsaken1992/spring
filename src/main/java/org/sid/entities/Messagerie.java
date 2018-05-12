package org.sid.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Messagerie {
	@Id @GeneratedValue
Long id;

private Long user1;
private Long user2;
private String message;
@Temporal(TemporalType.TIMESTAMP)

private Date date;
public Long getId() {
	return id;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public void setId(Long id) {
	this.id = id;
}
public Long getUser1() {
	return user1;
}
public void setUser1(Long user1) {
	this.user1 = user1;
}
public Long getUser2() {
	return user2;
}
public void setUser2(Long user2) {
	this.user2 = user2;
}
public Messagerie() {
	super();
	// TODO Auto-generated constructor stub
}
public Messagerie(Long id, Long user1, Long user2, String message) {
	super();
	this.id = id;
	this.user1 = user1;
	this.user2 = user2;
	this.message = message;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
	
}
