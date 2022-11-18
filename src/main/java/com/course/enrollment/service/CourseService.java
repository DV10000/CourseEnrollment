package com.course.enrollment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.enrollment.model.Categories;
import com.course.enrollment.model.Courses;
import com.course.enrollment.model.EnrollCourse;
import com.course.enrollment.model.EnrolledCoursesByUser;
import com.course.enrollment.repository.CategoryRepository;
import com.course.enrollment.repository.CoursesRepository;
import com.course.enrollment.repository.EnrollCourseRepository;
import com.course.enrollment.repository.EnrolledCoursesByUserRepository;

@Service
public class CourseService {

	@Autowired
	private CoursesRepository repo;
	
	@Autowired 
	private CategoryRepository categoryRepo;

	@Autowired
	private EnrollCourseRepository enrollRepo;
	
	@Autowired
	private EnrolledCoursesByUserRepository enrollByUserRepo;

	public Courses saveCourse(Courses course) {
		return repo.save(course);
	}
	
	public Categories saveCategory(Categories category) {
		return categoryRepo.save(category);
	}

	public List<Courses> getCourses() {
		return repo.findAll();
	}
	
	public List<Categories> getCategory(){
		return categoryRepo.findAll();
	}

	public int updateRecord(String course_id, String status) {

		return repo.updateCourseStatus(course_id, status);

	}

	private EnrolledCoursesByUser preparePayload(Courses course) {
		EnrolledCoursesByUser enrollCourse = new EnrolledCoursesByUser();
		enrollCourse.setCourseId(course.getCourse_id());
//		enrollCourse.setCourse_name(course.getCourse_name());
//		enrollCourse.setCourse_author(course.getCourse_author());
//		enrollCourse.setCourse_category(course.getCourse_category());
//		enrollCourse.setCourse_creationDate(course.getCourse_creationDate());
//		enrollCourse.setCourse_authorExp(course.getCourse_authorExp());
//		enrollCourse.setStatus(course.getStatus());
		return enrollCourse;

	}

	public EnrolledCoursesByUser addEnrolledCourse(Courses course , String userid) {
		EnrolledCoursesByUser enroll = preparePayload(course);
		enroll.setUserId(userid);
		System.out.println("inside service" + enroll);
		EnrolledCoursesByUser enrolledCourse = null;
		int updateCount = repo.updateCourseStatus(course.getCourse_id(), course.getStatus());
		System.out.println("inside service row update count" + updateCount);
		if (updateCount != 0) {
			enrolledCourse = enrollByUserRepo.save(enroll);
			System.out.println(enrolledCourse);
		}
		return enrolledCourse;
	}

	public List<Courses> getEnrollCourses(String userid) {
		// TODO Auto-generated method stub
		return null;
	}
}
