package com.spring.mongo.service;

import java.util.Date;
import java.util.List;
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
	
	public List<Post> findByContainedTextInTittle(String text) {
		return postRepository.findByTittleContainingIgnoreCase(text);
	}
	
	public List<Post> findByPostBody(String text) {
		return postRepository.findByPostBody(text);
	}
	
	public List<Post> findByTextAndGivenDate(String text, Date initialDate, Date finalDate){
		return postRepository.findByTextAndGivenDate(text, initialDate, finalDate);
	}
		
}
