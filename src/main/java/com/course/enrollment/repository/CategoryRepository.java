package com.course.enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.course.enrollment.model.Categories;
import com.course.enrollment.model.Courses;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, String> {

}
