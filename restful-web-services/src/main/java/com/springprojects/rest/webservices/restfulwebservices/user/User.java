package com.springprojects.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;


@Entity(name = "user_details")
public class User {
	
	protected User() {
		
	}
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=2, message="Name should have atleast 2 characters")
	//@JsonProperty("user_name")
	private String name;
	
	@Past(message="Birth Date should be in the past")
	//@JsonProperty("birth_date")
	private LocalDate Birthdate;
	
	public User(Integer id, String name, LocalDate birthdate) {
		super();
		this.id = id;
		this.name = name;
		Birthdate = birthdate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthdate() {
		return Birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		Birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", Birthdate=" + Birthdate + "]";
	}
	
}
