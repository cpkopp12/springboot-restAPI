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

import com.springprojects.rest.webservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
	
	
	
	private UserRepository repository;
	
	public UserJpaResource(UserRepository repository) {
		this.repository = repository;
	}
	

	//GET ALL USERS: /users
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return repository.findAll();
	}
	
	//GET USER BY ID: /users/{id}
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
	
	//POST USER: /users
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
	
	//DELETE USER: /users/{id}
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUserById(@PathVariable int id) {
		
		repository.deleteById(id);
		
	}
	
}
