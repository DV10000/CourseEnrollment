package com.course.enrollment.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Categories")
public class Categories {

	@Id
	private String categoryId;
	private String courseName;
	
	
	public Categories() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Categories(String categoryId, String courseName) {
		super();
		this.categoryId = categoryId;
		this.courseName = courseName;
	}
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	
}
