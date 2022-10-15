package com.course.enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.enrollment.model.User;

public interface CourseEnrollmentRepository extends JpaRepository<User,Integer>{

	public User findByEmailId(String email);

	public User findByUserIDAndPassword(Integer userID, String password);

	public User findByUserID(Integer userId);
 
}
