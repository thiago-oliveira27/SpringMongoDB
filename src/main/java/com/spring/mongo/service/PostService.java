package com.spring.mongo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mongo.domain.Post;
import com.spring.mongo.repository.PostRepository;
import com.spring.mongo.service.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	public PostRepository postRepository;
	
	public Post findById(String id) {
		Optional<Post> obj = postRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
		
}
