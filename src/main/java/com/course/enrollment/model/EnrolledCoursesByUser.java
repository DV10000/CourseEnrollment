package com.course.enrollment.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EnrolledCoursesByUser")
public class EnrolledCoursesByUser {

	
	@Id
	private String courseId;
	private String userId;
	
	
	public EnrolledCoursesByUser() {}
	public EnrolledCoursesByUser(String userId, String courseId) {
		super();
		this.userId = userId;
		this.courseId = courseId;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	
}
