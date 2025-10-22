package com.prithvi.microservice_user_delete;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.prithvi.repo.UserRepo;
import com.prithvi.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserAPITest1 {

	@InjectMocks
	private UserServiceImpl deleteUserServiceImpl;

	@Mock
	UserRepo userDAO;

	@Test
	public void testDeleteUser_Success() {
		doNothing().when(userDAO).deleteById(1);

		ResponseEntity<?> response = deleteUserServiceImpl.deleteUserById(1);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

		String message = ((Map<String, String>) response.getBody()).get("Message");
		Assertions.assertTrue("User Deleted Successfully".contains(message));

		verify(userDAO).deleteById(1);
	}

	@Test
	public void testDeleteUser_Fail() {

		ResponseEntity<?> response = deleteUserServiceImpl.deleteUserById(0);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

		String message = ((Map<String, String>) response.getBody()).get("Message");
		Assertions.assertTrue("Bad Request".contains(message));

	}

}
