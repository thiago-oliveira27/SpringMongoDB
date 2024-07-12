package com.spring.mongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.mongo.domain.Post;
import com.spring.mongo.domain.User;
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
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findUserById(@PathVariable String id) {
		User user = userService.findById(id); 
		return ResponseEntity.ok().body(new UserDTO(user));	
	} 	
	
	@RequestMapping(value="/{userId}/posts", method=RequestMethod.GET)
	public ResponseEntity<List<Post>> getUserPosts(@PathVariable String userId){
		User user = userService.findById(userId);
		return ResponseEntity.ok().body(user.getPosts());
	}

	@RequestMapping(method=RequestMethod.POST)
 	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
		User obj = userService.fromDTO(objDto);
		obj = userService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable String id){
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateUser(@RequestBody UserDTO user, @PathVariable String id){	
		User userFromDTO = userService.fromDTO(user);
		userFromDTO.setId(id);
		userService.updateUser(userFromDTO);
		return ResponseEntity.noContent().build();
	}
}
