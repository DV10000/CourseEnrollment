package com.course.enrollment.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	
	//for login
	@Id
	private Integer userID;
	private String password;
	
	//for registration
	private String emailId;
	private String firstName;
	private String lastName;
	private String dob;
	
	public User() {
		
	}
	
	
	public User(String password, Integer userID, String emailId, String firstName, String lastName,
			String dob) {
		super();
		this.password = password;
		this.userID = userID;
		this.emailId = emailId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
	}
	
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	

}
