package com.spring.mongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.spring.mongo.domain.Post;
import com.spring.mongo.domain.User;
import com.spring.mongo.dtos.AuthorDTO;
import com.spring.mongo.repository.PostRepository;
import com.spring.mongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		userRepository.deleteAll();
		postRepository.deleteAll();

		User user1 = new User(null, "Thiago", "thiago@gmail.com");
		User user2 = new User(null, "Felipe", "felipe@gmail.com");
		User user3 = new User(null, "Ronaldo", "ronaldo@gmail.com");

		userRepository.saveAll(Arrays.asList(user1, user2, user3));

		Post post1 = new Post(null, sdf.parse("21/03/2024"), "Partiu viagem!", "Vou viajar para SP!\nAbraços!", new AuthorDTO(user1));
		Post post2 = new Post(null, sdf.parse("05/07/2024"), "Hello!", "How u doing?", new AuthorDTO(user2));

		postRepository.saveAll(Arrays.asList(post1, post2));

		user1.getPosts().add(post1);
		user2.getPosts().add(post2);
		
		userRepository.save(user1);
		userRepository.save(user2);
		
	}
}
