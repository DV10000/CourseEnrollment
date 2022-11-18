package com.course.enrollment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.enrollment.model.Categories;
import com.course.enrollment.model.Courses;
import com.course.enrollment.model.EnrollCourse;
import com.course.enrollment.model.EnrolledCoursesByUser;
import com.course.enrollment.service.CourseService;
import com.course.enrollment.service.EnrollCourseService;

@RestController
@CrossOrigin(origins = "http://localhost:4200") //give generic name for url
public class CourseController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private EnrollCourseService enrollService;

	// Method to add new course by the admin
	@PostMapping("/add/courses")
	public ResponseEntity<Courses> addNewCourse(@RequestBody Courses course) {
		Courses newCourse = courseService.saveCourse(course);
		return new ResponseEntity<>(newCourse, HttpStatus.CREATED);

	}

	// method to add the enrolled/updated status courses in Enrollment table

//	@PutMapping("/enrollCourses")
//	public ResponseEntity<EnrollCourse> getEnrollCourse(@RequestBody Courses course , @RequestHeader String userid) {
//		EnrollCourse enrollCourse = courseService.addEnrolledCourse(course, userid);
//		return new ResponseEntity<>(enrollCourse, HttpStatus.OK);
//
//	}
	
	@PutMapping("/enrollCourses")
	public ResponseEntity<EnrolledCoursesByUser> getEnrollCourse(@RequestBody Courses course , @RequestHeader String userid) {
		EnrolledCoursesByUser enrollCourse = courseService.addEnrolledCourse(course, userid);
		return new ResponseEntity<>(enrollCourse, HttpStatus.OK);

	}

	// Method to fetch all the courses
	@GetMapping("/getCourses")
	public ResponseEntity<List<Courses>> getCourseList() {
		List<Courses> list = courseService.getCourses();
		return new ResponseEntity<>(list, HttpStatus.OK);

	}
	
	// Method to fetch all course id for enrolled courses for particular user
	@GetMapping("get/enrolledcourses")
	public ResponseEntity<List<String>> getEnrolledCourseList(@RequestHeader String userid) {
		List<String> list = enrollService.getEnrollCourses(userid);
		return new ResponseEntity<>(list, HttpStatus.OK);

	}
	
	@PostMapping("/add/Categories")
	public ResponseEntity<Categories> addCategories(@RequestBody Categories category){
		Categories newCategory = courseService.saveCategory(category);
		return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
	}
	
	@GetMapping("/getCategories")
	public ResponseEntity<List<Categories>> getCategories(){
		List<Categories> list = courseService.getCategory();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
