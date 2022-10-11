package com.course.enrollment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.course.enrollment.model.User;
import com.course.enrollment.service.CourseEnrollmentService;

@RestController
public class CourseEnrollmentController {
	
	@Autowired
	private CourseEnrollmentService courseEnrollmentService;
	
	@PostMapping("/registeruser")
	public User registerUser(@RequestBody User user) throws Exception {
		String tempEmailId = user.getEmailId();
		if(tempEmailId !=null && "".equals(tempEmailId)) {
		User userobj =courseEnrollmentService.fetchUserByEmailId(tempEmailId);
		if(userobj !=null) {
			throw new Exception("User with " + tempEmailId + "is already exist");
		  }
		}
		User userobj = null;
		userobj = courseEnrollmentService.saveUser(user);
		return userobj;
	}

//	@GetMapping("/loginuser")
//	public User loginUser(@RequestBody User user) throws Exception {
//		String userName = user.getUserName();
//		String tempPass = user.getPassword();
//		User userobj = null;
//		if(userName != null && tempPass != null) {
//			userobj = courseEnrollmentService.fetchUserByUserIdAndPassword(userName, tempPass);
//		}
//		if(userobj ==null) {
//			throw new Exception("Bad Credential");
//		  }
//		return userobj;
//	}
	
}
