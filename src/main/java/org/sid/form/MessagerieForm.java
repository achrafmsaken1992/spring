package org.sid.form;

public class MessagerieForm {
	Long id;
String message;
Long user1;
Long user2;
String role;
String image;
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
String body;
public String getBody() {
	return body;
}
public void setBody(String body) {
	this.body = body;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
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
}
