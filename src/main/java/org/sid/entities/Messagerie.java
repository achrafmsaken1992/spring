package org.sid.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Messagerie {
	@Id @GeneratedValue
Long id;

	@ManyToOne()
	@JoinColumn(name="user1")
	Appuser user1;
	@ManyToOne()
	@JoinColumn(name="user2")
	Appuser user2;
public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Appuser getUser1() {
		return user1;
	}
	public void setUser1(Appuser user1) {
		this.user1 = user1;
	}
	public Appuser getUser2() {
		return user2;
	}
	public void setUser2(Appuser user2) {
		this.user2 = user2;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
private String message;
@Temporal(TemporalType.TIMESTAMP)

private Date date;

	
}
