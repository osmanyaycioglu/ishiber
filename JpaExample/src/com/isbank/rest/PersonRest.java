package com.isbank.rest;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.isbank.ejb.PersonDAO;
import com.isbank.rest.models.Person;

@Path("/person")
public class PersonRest {
	
	@EJB
	private PersonDAO per;
	
	@Path("/insert")
	@POST
	public String insert(@Valid Person person) {
		per.savePerson(person);
		return "OK";
	}
	
}
