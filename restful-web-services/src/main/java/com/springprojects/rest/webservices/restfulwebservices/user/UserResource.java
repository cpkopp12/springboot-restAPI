package com.springprojects.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;

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

import jakarta.validation.Valid;

@RestController
public class UserResource {
	
	private UserDaoService service;
	
	public UserResource(UserDaoService service) {
		this.service = service;
	}
	

	//GET ALL USERS: /users
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}
	
	//GET USER BY ID: /users/{id}
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUserById(@PathVariable int id) {
		
		User user = service.findOneById(id);
		
		//findOneById() returns null if no user found
		if(user == null) {
			throw new UserNotFoundException("id:" + id);
		}
		
		//create hateos entity model of user
		EntityModel<User> entityModel = EntityModel.of(user);
		
		//add links to the Entity Model of the user
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	
	//POST USER: /users
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		
		User savedUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		//Response Entity = class from spring, created 201 status, location = /users/4
		return ResponseEntity.created(location).build();
	}
	
	//DELETE USER: /users/{id}
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable int id) {
		
		service.deleteOneById(id);
		
	}
	
}
