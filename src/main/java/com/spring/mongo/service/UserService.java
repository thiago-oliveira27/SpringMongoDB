package com.spring.mongo.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.ArrayUtils;

import com.spring.mongo.domain.User;
import com.spring.mongo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	public UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
}
