package com.springprojects.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;

public class User {
	
	private Integer id;
	private String name;
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
