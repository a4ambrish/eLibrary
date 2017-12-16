package com.javatpoint.beans;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="e_issuebook")

public class IssueBookBean implements Serializable {
private static final String  = null;
private String callno,studentid,studentname;
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

@Column( unique = true, nullable = false, precision = 5, scale = 0)

public String getReturnstatus() {
	return returnstatus;
}
public void setReturnstatus(String returnstatus) {
	this.returnstatus = returnstatus;
}
@Column( unique = true, nullable = false, precision = 5, scale = 0)
public Date getIssueddate() {
	return issueddate;
}
public void setIssueddate(Date issueddate) {
	this.issueddate = issueddate;
}

@Column( unique = true, nullable = false, precision = 5, scale = 0)

public String getCallno() {
	return callno;
}
public void setCallno(String callno) {
	this.callno = callno;
}
@Id
@Column( unique = true, nullable = false, precision = 5, scale = 0)
public String getStudentid() {
	return studentid;
}
public void setStudentid(String studentid) {
	this.studentid = studentid;
}
@Column( unique = true, nullable = false, precision = 5, scale = 0)
public String getStudentname() {
	return studentname;
}
public void setStudentname(String studentname) {
	this.studentname = studentname;
}
@Column(columnDefinition="BigDecimal", unique = true, nullable = false, precision = 5, scale = 0)
public long getStudentmobile() {
	return studentmobile;
}
public void setStudentmobile(long studentmobile) {
	this.studentmobile = studentmobile;
}

}
