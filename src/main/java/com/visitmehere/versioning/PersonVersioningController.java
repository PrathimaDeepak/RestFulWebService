package com.visitmehere.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Version can be done in many types and each has its own advantages and disadvantages
 * URI version
 * parameters based
 * header based
 * produces based
 * */
@RestController
public class PersonVersioningController {

	@GetMapping("/v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Anna Johnson");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 personV2() {
		return new PersonV2("Anna", "Johnson");
	}
	
	@GetMapping(value="/person/param", params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Anna Johnson");
	}
	
	@GetMapping(value="/person/param", params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2("Anna", "Johnson");
	}
	
	@GetMapping(value="/person/header", headers = "X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Anna Johnson");
	}
	
	@GetMapping(value="/person/header", headers = "X-API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2("Anna", "Johnson");
	}
	
	@GetMapping(value="/person/produces", produces = "application/vnd.company.app-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Anna Johnson");
	}
	
	@GetMapping(value="/person/produces", produces = "application/vnd.company.app-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2("Anna", "Johnson");
	}

}
