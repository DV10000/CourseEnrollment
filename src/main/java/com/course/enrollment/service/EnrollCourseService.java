package com.course.enrollment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.enrollment.model.Courses;
import com.course.enrollment.model.EnrollCourse;
import com.course.enrollment.repository.CoursesRepository;
import com.course.enrollment.repository.EnrollCourseRepository;

@Service
public class EnrollCourseService {

	@Autowired
	private EnrollCourseRepository repo;

	@Autowired
	private CoursesRepository courseRepo;

	public EnrollCourse saveCourse(EnrollCourse enrollCourse) {
		return repo.save(enrollCourse);
	}

	public List<EnrollCourse> getEnrolledCourses() {
		return repo.findAll();
	}

	public List<String> getEnrollCourses(String userid) {
		return repo.getCoursesById(userid);
	}
	
	
}
