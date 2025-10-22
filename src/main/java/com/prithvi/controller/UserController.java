package com.prithvi.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prithvi.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService deleteUserService;	

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Map<String, String>> deleteUser(@PathVariable int id) {
		System.out.println("Inside Controller, Delete");
		return deleteUserService.deleteUserById(id);
	}

}
