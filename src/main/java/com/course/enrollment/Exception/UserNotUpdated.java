package com.course.enrollment.Exception;

public class UserNotUpdated extends RuntimeException {

	private String message;
    public UserNotUpdated(String message) {
        super(message);
        this.message = message;
    }
    public UserNotUpdated() {
    }
}
