package org.sid.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

@Entity

public class AppRole {
	@Id @GeneratedValue
	private Long id;
	private String roleName;
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public AppRole(Long id, String roleName) {
		
		this.id = id;
		this.roleName = roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleName() {
		return roleName;
	}
	public AppRole() {
		
		// TODO Auto-generated constructor stub
	}
	
}
