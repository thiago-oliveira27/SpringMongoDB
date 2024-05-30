package com.spring.mongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mongo.dtos.UserDTO;
import com.spring.mongo.service.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> test() {
		
		List<UserDTO> userListDTO = userService.findAll().stream().map(user -> new UserDTO(user)).collect(Collectors.toList()); 		
		return ResponseEntity.ok().body(userListDTO);
	}

}
