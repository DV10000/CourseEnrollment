package com.course.enrollment.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Courses")
public class Courses {

	@Id
	private String course_id;
	private String course_name;
	private String course_author;
	private String course_categoryId;
	private String course_creationDate;
	private String course_authorExp;
	private String status;

	public Courses() {
	}

	public Courses(String course_id, String course_name, String course_author, String course_category,
			String course_creationDate, String course_authorExp, String status) {
		super();
		this.course_id = course_id;
		this.course_name = course_name;
		this.course_author = course_author;
		this.course_categoryId = course_category;
		this.course_creationDate = course_creationDate;
		this.course_authorExp = course_authorExp;
		this.status = status;
	}

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getCourse_author() {
		return course_author;
	}

	public void setCourse_author(String course_author) {
		this.course_author = course_author;
	}

	public String getCourse_category() {
		return course_categoryId;
	}

	public void setCourse_category(String course_category) {
		this.course_categoryId = course_category;
	}

	public String getCourse_creationDate() {
		return course_creationDate;
	}

	public void setCourse_creationDate(String course_creationDate) {
		this.course_creationDate = course_creationDate;
	}

	public String getCourse_authorExp() {
		return course_authorExp;
	}

	public void setCourse_authorExp(String course_authorExp) {
		this.course_authorExp = course_authorExp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
