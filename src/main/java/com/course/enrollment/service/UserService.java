package com.course.enrollment.service;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.enrollment.Exception.UserAlreadyExist;
import com.course.enrollment.Exception.UserDoesNotExistException;
import com.course.enrollment.Util.EnrollmentEncryption;
import com.course.enrollment.model.Courses;
import com.course.enrollment.model.User;
import com.course.enrollment.repository.UserRepository;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private EnrollmentEncryption enrollmentEncryption;

	public User saveUser(User user) {
//		String password = user.getPassword();
//		Encoder encoder = Base64.getEncoder();
//		String encodedPassword = encoder.encodeToString(password.getBytes());
		
		user.setPassword(enrollmentEncryption.encrypt(user.getPassword()));
		return repo.save(user);
	}

	public User fetchUserByUserLoginId(String userLoginId) {
		return repo.findByUserLoginId(userLoginId);
	}

	public User fetchUserByUserLoginIdAndPassword(String userLoginId, String password){
		Encoder encoder = Base64.getEncoder();
		String encodedPasswordForLogin = encoder.encodeToString(password.getBytes());
		return repo.findByUserLoginIdAndPassword(userLoginId, encodedPasswordForLogin);

	}

	public String updateNewPassword(String password, String userLoginId) {
		Encoder encoder = Base64.getEncoder();
		String encodedForgotPassword = encoder.encodeToString(password.getBytes());
		int noOfUpadtes = repo.updatePasswordByUserLoginId(encodedForgotPassword, userLoginId);
		if (noOfUpadtes != 0) {
			return "Password updated successfully";
		} else {
			return "No Records updated";
		}
	}

	public int updateUserDetails(String password, String userLoginId, String emailId, String firstName, String lastName,
			String dob) {
		return repo.updateDeatilsByUserLoginId(password, userLoginId, emailId, firstName, lastName, dob);
	}

}
