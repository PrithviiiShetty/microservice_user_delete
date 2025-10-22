package com.prithvi.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.prithvi.repo.UserRepo;
import com.prithvi.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userDAO;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public ResponseEntity<Map<String, String>> deleteUserById(int id) {
		if (id > 0) {
			try {
				ResponseEntity<Map> response = restTemplate.getForEntity("http://microservice-user-fetch/user/fetch/{id}", Map.class, id);
				System.out.println("Response : " + response.toString());
				
				if(response.getBody() !=null) {
					String status = (String) response.getBody().get("Status");
					
					if (status.equals("Fail")) {
						return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Message", "User Not Found"));
					} else if (status.equals("Success")) {
						userDAO.deleteById(id);
						return ResponseEntity.status(HttpStatus.OK).body(Map.of("Message", "User Deleted Successfully"));
					} else {
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("Message", "Technical Error : " + response.getBody().get("Message")));
					}	
				} else {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("Message", "Technical Error : " + response.getBody().get("Message")));
				}
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("Message", "Technical Error"));
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Message", "Bad Request"));
		}
	}

}
