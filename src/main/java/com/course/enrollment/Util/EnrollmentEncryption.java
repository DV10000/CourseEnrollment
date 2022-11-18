package com.course.enrollment.Util;

import java.util.Base64;
import java.util.Base64.Encoder;

import org.springframework.stereotype.Component;

@Component
public class EnrollmentEncryption {
	
	public String encrypt(String password) {
		Encoder encoder = Base64.getEncoder();
		String encodedPassword = encoder.encodeToString(password.getBytes());
		return encodedPassword;
	}

}
