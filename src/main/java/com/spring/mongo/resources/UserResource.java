package com.spring.mongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mongo.domain.User;
import com.spring.mongo.service.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/teste")
	public ResponseEntity<List<User>> test() {
		List<User> list = userService.findAll();
		return ResponseEntity.ok().body(list);
	}

}
