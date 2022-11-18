package com.course.enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.course.enrollment.model.EnrollCourse;
import com.course.enrollment.model.EnrolledCoursesByUser;

@Repository
public interface EnrolledCoursesByUserRepository extends JpaRepository<EnrolledCoursesByUser, String>{

}
