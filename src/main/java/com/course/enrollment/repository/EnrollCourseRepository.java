package com.course.enrollment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.course.enrollment.model.Courses;
import com.course.enrollment.model.EnrollCourse;

@Repository
public interface EnrollCourseRepository extends JpaRepository<EnrollCourse, String>{

	@Query(value= "SELECT COURSEID FROM ENROLL_COURSE where userid = :userid", nativeQuery = true)
	List<String> getCoursesById(@Param("userid") String userid);

}
