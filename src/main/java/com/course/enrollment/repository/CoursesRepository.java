package com.course.enrollment.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.course.enrollment.model.Courses;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, String>{

	
	@Modifying
	@Transactional
	@Query(value = "Update Courses set status= :status where course_id = :course_id", nativeQuery = true)
	public int updateCourseStatus(String course_id, String status);

}
