package com.javatpoint.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="e_issuebook")

public class IssueBookBean implements Serializable {
//private static final String  = null;
	@Id
	
private String callno;
	@Id
private String studentid;
private String studentname;
private long studentmobile;
private Date issueddate;
private String returnstatus;

public IssueBookBean() {}
public IssueBookBean(String callno, String studentid, String studentname, long studentmobile) {
	super();
	this.callno = callno;
	this.studentid = studentid;
	this.studentname = studentname;
	this.studentmobile = studentmobile;
}


public String getReturnstatus() {
	return returnstatus;
}
public void setReturnstatus(String returnstatus) {
	this.returnstatus = returnstatus;
}

public Date getIssueddate() {
	return issueddate;
}
public void setIssueddate(Date issueddate) {
	this.issueddate = issueddate;
}



public String getCallno() {
	return callno;
}
public void setCallno(String callno) {
	this.callno = callno;
}

public String getStudentid() {
	return studentid;
}
public void setStudentid(String studentid) {
	this.studentid = studentid;
}

public String getStudentname() {
	return studentname;
}
public void setStudentname(String studentname) {
	this.studentname = studentname;
}

public long getStudentmobile() {
	return studentmobile;
}
public void setStudentmobile(long studentmobile) {
	this.studentmobile = studentmobile;
}

}
