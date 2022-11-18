package com.course.enrollment.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.course.enrollment.model.Courses;
import com.course.enrollment.model.User;

public interface UserRepository extends JpaRepository<User, String> {

	
	public User findByUserLoginIdAndPassword(String userLoginId,String password);

	public User findByUserLoginId(String userLoginId);

	@Modifying
	@Transactional
	@Query(value = "Update User set password= :password where user_Login_Id = :userLoginId", nativeQuery = true)
	public int updatePasswordByUserLoginId(@Param("password") String password, @Param("userLoginId") String userLoginId);

	@Modifying
	@Transactional
	@Query(value = "Update User set password= :password ,email_Id  = :emailId, first_Name= :firstName, last_Name= :lastName,dob= :dob  where userLoginId = :userLoginId", nativeQuery = true)
	public int updateDeatilsByUserLoginId(@Param("password") String password, @Param("userLoginId") String userLoginId,
			@Param("emailId") String emailId, @Param("firstName") String firstName, @Param("lastName") String lastName,
			@Param("dob") String dob);

}
