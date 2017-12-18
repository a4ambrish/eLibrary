package com.javatpoint.beans;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="e_book")
public class BookBean implements Serializable{
private String callno,name,author,publisher;
private int quantity,issued;
public BookBean() {
	super();
	// TODO Auto-generated constructor stub
}

public BookBean(String callno, String name, String author, String publisher, int quantity) {
	super();
	this.callno = callno;
	this.name = name;
	this.author = author;
	this.publisher = publisher;
	this.quantity = quantity;
}
@Id
@Column( unique = true, nullable = false, precision = 5, scale = 0)

public String getCallno() {
	return callno;
}
public void setCallno(String callno) {
	this.callno = callno;
}
@Column(nullable = false)
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@Column(nullable = false)
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
@Column(nullable = false)
public String getPublisher() {
	return publisher;
}
public void setPublisher(String publisher) {
	this.publisher = publisher;
}
@Column(nullable = false)
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
@Column(nullable = false)
public int getIssued() {
	return issued;
}
public void setIssued(int issued) {
	this.issued = issued;
}

}
