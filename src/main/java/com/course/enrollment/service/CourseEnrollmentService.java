package com.course.enrollment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.enrollment.model.User;
import com.course.enrollment.repository.CourseEnrollmentRepository;

@Service
public class CourseEnrollmentService {
	
	@Autowired
	private CourseEnrollmentRepository  repo;
	
	public User saveUser(User user) {
		return repo.save(user);
	}
	
	public User fetchUserByEmailId(String email) {
		return repo.findByEmailId(email);
	}
	
	public User fetchUserByUserId(Integer userId) {
		return repo.findByUserID(userId);
	}

	public User fetchUserByUserIDAndPassword(Integer userID, String password) {
		return repo.findByUserIDAndPassword(userID,password);
	}
}
