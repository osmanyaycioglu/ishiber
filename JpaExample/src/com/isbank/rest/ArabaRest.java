package com.isbank.rest;

import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.isbank.rest.models.Kamyon;
import com.isbank.rest.models.Otomobil;

@Path("/araba")
public class ArabaRest {
	@PersistenceContext(unitName = "JpaExample")
	private EntityManager em;
	
	@Path("/otomobil")
	@POST
	@Transactional
	public String createOtomobil(Otomobil o) {
		em.persist(o);
		return "OK";
	}

	@Path("/kamyon")
	@POST
	@Transactional
	public String createOtomobil(Kamyon o) {
		em.persist(o);
		return "OK";
	}

}