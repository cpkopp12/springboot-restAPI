package com.springprojects.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springprojects.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.springprojects.rest.webservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
	
	
	
	private UserRepository repository;
	
	private PostRepository postRepository;
	
	public UserJpaResource(UserRepository repository, PostRepository postRepository) {
		this.repository = repository;
		this.postRepository = postRepository;
	}
	

	//GET ALL USERS: /jpa/users
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return repository.findAll();
	}
	
	//GET USER BY ID: /jpa/users/{id}
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUserById(@PathVariable int id) {
		
		Optional<User> user = repository.findById(id);
		
		//findOneById() returns null if no user found
		if(user.isEmpty()) {
			throw new UserNotFoundException("id:" + id);
		}
		
		//create hateos entity model of user
		EntityModel<User> entityModel = EntityModel.of(user.get());
		
		//add links to the Entity Model of the user
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	
	//POST USER: /jpa/users
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		
		User savedUser = repository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		//Response Entity = class from spring, created 201 status, location = /users/4
		return ResponseEntity.created(location).build();
	}
	
	//DELETE USER: /jpa/users/{id}
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUserById(@PathVariable int id) {
		
		repository.deleteById(id);
		
	}
	
	//POSTS BY USER: /jpa/users/{id}/posts
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrivePostsByUser(@PathVariable int id) {
		
		Optional<User> user = repository.findById(id);
		
		//findOneById() returns null if no user found
		if(user.isEmpty()) {
			throw new UserNotFoundException("id:" + id);
		}
		
		return user.get().getPosts();
		
	}
	
	//CREATE POST BY USER ID: /jpa/users/{id}/posts
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostByUser(@PathVariable int id, @Valid @RequestBody Post post) {
		
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty()) {
			throw new UserNotFoundException("id:" + id);
		}
		
		post.setUser(user.get());
		
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
}
