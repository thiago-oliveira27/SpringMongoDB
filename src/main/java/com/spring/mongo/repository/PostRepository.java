package com.spring.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.mongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

	List<Post> findByTittleContainingIgnoreCase(String text);

	@Query("{body : { $regex: ?0, $options: 'i'} }")
	List<Post> findByPostBody(String text);	
}
