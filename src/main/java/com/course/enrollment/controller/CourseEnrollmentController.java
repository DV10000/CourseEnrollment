package com.course.enrollment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.enrollment.model.User;
import com.course.enrollment.service.CourseEnrollmentService;

@RestController
@RequestMapping("/enrollment")
public class CourseEnrollmentController {
	
	@Autowired
	private CourseEnrollmentService courseEnrollmentService;
	
	@PostMapping("/registeruser")
	@CrossOrigin(origins = "http://localhost:4200")
	public User registerUser(@RequestBody User user) throws Exception {
		
		  Integer userId = user.getUserID(); 
		  if(userId !=null) { 
			  User userobj =courseEnrollmentService.fetchUserByUserId(userId);
			  System.out.println(userobj);
			  System.out.println(userId);
			  if(userobj !=null){ 
				  throw new Exception("User with " + userId + "is already exist"); 
				  } 
			  }
		 
		User userobj = null;
		userobj = courseEnrollmentService.saveUser(user);
		return userobj;
	}

	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:4200")
	public User loginUser(@RequestBody User user) throws Exception {
		Integer userID = user.getUserID();
		String tempPass = user.getPassword();
		User userobj = null;
		if(userID != null && tempPass != null) {
			userobj = courseEnrollmentService.fetchUserByUserIDAndPassword(userID, tempPass);
		}
		if(userobj ==null) {
			throw new Exception("Bad Credential");
		  }
		return userobj;
	}
	
}
