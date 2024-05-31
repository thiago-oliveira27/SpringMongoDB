package com.spring.mongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mongo.domain.User;
import com.spring.mongo.repository.UserRepository;
import com.spring.mongo.service.exception.ObjectNotFoundException;
import com.spring.mongo.service.exception.UserNotFoundException;

@Service
public class UserService {

	@Autowired
	public UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
}
