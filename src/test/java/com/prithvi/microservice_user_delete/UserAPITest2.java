package com.prithvi.microservice_user_delete;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.prithvi.controller.UserController;
import com.prithvi.service.UserService;

@WebMvcTest(UserController.class)
public class UserAPITest2 {

	@Autowired
	private MockMvc mockMvc;

	@TestConfiguration
	static class TestBean {
		@Bean
		UserService createUserDAO() {
			return Mockito.mock(UserService.class);
		}
	}

	@Autowired
	private UserService deleteUserService;

	@Test
	public void testDeleteUser_Success() throws Exception {
		when(deleteUserService.deleteUserById(1))
				.thenReturn(ResponseEntity.status(HttpStatus.OK).body(Map.of("Message", "User Deleted Successfully")));

		mockMvc.perform(delete("/user/delete/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.Message").value("User Deleted Successfully"));
		verify(deleteUserService).deleteUserById(1);
	}

	@Test
	public void testDeleteUser_Fail() throws Exception {
		when(deleteUserService.deleteUserById(0))
				.thenReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Message", "Bad Request")));

		mockMvc.perform(delete("/user/delete/0")).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.Message").value("Bad Request"));
		verify(deleteUserService).deleteUserById(0);
	}

}
