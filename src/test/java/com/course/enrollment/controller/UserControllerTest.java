package com.course.enrollment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.course.enrollment.model.User;
import com.course.enrollment.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@InjectMocks
	private UserController userController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private UserService userService;
	
	private static ObjectMapper mapper = new ObjectMapper();
	 
	@Test
	public void testLoginUser() {
		
		User user = new User(); 
		user.setUserLoginId("DV100");
		user.setPassword("14789632");
		when(userService.fetchUserByUserLoginIdAndPassword(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(user);
//		mockMvc.perform(get("/getMapping")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
//		.andExpect(jsonPath("$[0].name", Matchers.equalTo("Arun")));
		User mockedResponse = userService.fetchUserByUserLoginIdAndPassword(any(),any());
		assertEquals(mockedResponse, user);
	}
}
 