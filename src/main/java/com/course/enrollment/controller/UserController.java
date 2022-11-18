package com.course.enrollment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.enrollment.Exception.UserAlreadyExist;
import com.course.enrollment.Exception.UserDoesNotExistException;
import com.course.enrollment.Exception.UserNotUpdated;
import com.course.enrollment.model.ResponseData;
import com.course.enrollment.model.User;
import com.course.enrollment.service.UserService;

@RestController
@CrossOrigin(origins = "*")  
public class UserController {

	@Autowired
	private UserService userService;

	// Method for registering new user
	@PostMapping("/register/user")
	public ResponseEntity<ResponseData> registerUser(@RequestBody User user){
		ResponseData data = new ResponseData();
		User userobj = null;

		String userId = user.getUserLoginId();
		if (userId != null) {
			// to check whether userloginid already present or not
			User userVal = userService.fetchUserByUserLoginId(userId);
			if (userVal != null) {
				data.setMsg("UserId already exist");
				data.setUser(null);
				return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
			}
		}
		userobj = userService.saveUser(user);
		data.setMsg("Success");
		data.setUser(userobj);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	// Method for login user
	@GetMapping("/login")
	public ResponseEntity<User> loginUser(@RequestParam String userLoginId, @RequestParam String password)  {
		User userobj = new User();
		if (userLoginId != null && password != null) {
			// call the database to fetch the user login details
			userobj = userService.fetchUserByUserLoginIdAndPassword(userLoginId, password);
		}
		if (userobj == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userobj, HttpStatus.OK);
	}

	// Method for reset password
	@PutMapping("/update/password")
	public ResponseEntity<String> forgotPassword(@RequestBody User user) {
		try {
			String updatedPassword = userService.updateNewPassword(user.getPassword(), user.getUserLoginId());
			return new ResponseEntity<String>(updatedPassword, HttpStatus.OK);
		} catch (UserDoesNotExistException userDoesNotExistException) {
			return new ResponseEntity<String>(userDoesNotExistException.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	// Method for update existing user
	@PutMapping("/update/user")
	public ResponseEntity<Integer> updateUser(@RequestBody User user) {
		try {
			int updatedUser = userService.updateUserDetails(user.getPassword(), user.getUserLoginId(), user.getEmailId(),
					user.getFirstName(), user.getLastName(), user.getDob());
			return new ResponseEntity<Integer>(updatedUser, HttpStatus.OK);
		} catch (UserNotUpdated userNotUpdated) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
