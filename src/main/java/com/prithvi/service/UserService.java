package com.prithvi.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface UserService {

	ResponseEntity<Map<String, String>> deleteUserById(int id);

}
