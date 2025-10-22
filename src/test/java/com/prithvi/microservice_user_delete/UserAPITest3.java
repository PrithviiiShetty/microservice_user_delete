package com.prithvi.microservice_user_delete;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.prithvi.repo.UserRepo;

@SpringBootTest
@AutoConfigureMockMvc
public class UserAPITest3 {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	UserRepo userRepo;

	@BeforeEach
	void clean() {
		userRepo.deleteAll();
	}

	@Test
	public void testDeleteUser_Success() throws JsonProcessingException, Exception {
		mockMvc.perform(delete("/user/delete/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.Message").value("User Deleted Successfully"));

	}

	@Test
	public void testDeleteUser_Fail() throws JsonProcessingException, Exception {
		mockMvc.perform(delete("/user/delete/0")).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.Message").value("Bad Request"));

	}

}
