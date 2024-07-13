package com.spring.mongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mongo.domain.Post;
import com.spring.mongo.resources.util.URL;
import com.spring.mongo.service.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

	
	@Autowired
	private PostService postService;
	
	@RequestMapping(value="/{postId}", method=RequestMethod.GET)
	public ResponseEntity<Post> getPostById(@PathVariable String postId){
		Post post = postService.findById(postId);
		return ResponseEntity.ok().body(post);
	}
	
	@RequestMapping(value="/tittleSearch", method=RequestMethod.GET)
	public ResponseEntity<List<Post>> findByContainedTextInTittle(@RequestParam(value="text", defaultValue="") String text){
		return ResponseEntity.ok().body(postService.findByContainedTextInTittle(URL.decodeUrlParam(text)));
	}	
}
