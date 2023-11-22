package com.springprojects.rest.webservices.restfulwebservices.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	public User retrieveUserById(@PathVariable int id) {
		return service.findOneById(id);
	}
	
	//POST USER: /users
	@PostMapping("/users")
	public void createUser(@RequestBody User user) {
		service.save(user);
	}
	
}
