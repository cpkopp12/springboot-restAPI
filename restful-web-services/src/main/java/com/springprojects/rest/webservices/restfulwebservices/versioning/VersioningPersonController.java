package com.springprojects.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPerson() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPerson() {
		return new PersonV2(new Name("bob","charlie"));
	}
	
	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getFirstVersionOfPersonRequestParameter() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path = "/person", params = "version=2")
	public PersonV2 getSecondVersionOfPersonRequestParameter() {
		return new PersonV2(new Name("bob","charlie"));
	}
	
	@GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersonRequestHeaders() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 getSecondVersionOfPersonRequestHeaders() {
		return new PersonV2(new Name("bob","charlie"));
	}
	
	//CAN ALSO USE AN ACCEPT HEADER -> same results
	
//	@GetMapping(path = "/person/accept", produces = "X-API-VERSION=1")
//	public PersonV1 getFirstVersionOfPersonAcceptHeaders() {
//		return new PersonV1("Bob Charlie");
//	}
//	
//	@GetMapping(path = "/person/accept", produces = "X-API-VERSION=2")
//	public PersonV2 getSecondVersionOfPersonAcceptHeaders() {
//		return new PersonV2(new Name("bob","charlie"));
//	}
	
	
	//uri versioning => increased uri pollutions
	//headers => cannot cache just off of url
	//documentation is easiest for uri versioning 
	//all of these versioning methods are used in practice, but stick to one
	
}
