package com.javatpoint.beans;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="e_librarian")
public class LibrarianBean implements Serializable {
private int id;
private String name,email,password;
private String mobile;

public LibrarianBean() {}

public LibrarianBean(int id, String name, String email, String password, String mobile) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.password = password;
	this.mobile = mobile;
}
public LibrarianBean(String name, String email, String password, String mobile) {
	super();
	this.name = name;
	this.email = email;
	this.password = password;
	this.mobile = mobile;
}
@Id
@Column( unique = true, nullable = false, precision = 5, scale = 0)
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}


@Column( unique = true, nullable = false, precision = 5, scale = 0)
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}


@Column( unique = true, nullable = false, precision = 5, scale = 0)
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}


@Column( unique = true, nullable = false, precision = 5, scale = 0)
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}


@Column( unique = true, nullable = false, precision = 5, scale = 0)
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}

}
