package com.spring.mongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.spring.mongo.domain.User;
import com.spring.mongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		User user1 = new User(null, "Thiago", "thiago@gmail.com");
		User user2 = new User(null, "Felipe", "felipe@gmail.com");
		User user3 = new User(null, "Ronaldo", "ronaldo@gmail.com");
		userRepository.saveAll(Arrays.asList(user1, user2, user3));
	}
}
