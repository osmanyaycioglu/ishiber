package com.isbank.rest;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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
	
	@Path("/update")
	@POST
	public String update(@Valid Person person) {
		per.updatePerson(person);
		return "OK";
	}

	@Path("/get/{pid}")
	@GET
	public Person get(@PathParam("pid") long pid) {
		return per.getPerson(pid);
	}

}
