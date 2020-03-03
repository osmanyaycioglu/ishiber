package com.isbank.rest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.isbank.rest.models.Customer;
import com.isbank.rest.models.Person;

import sun.util.calendar.Gregorian;

@RequestScoped
@Path("/customer")
public class CustomerRest {
		
	@PersistenceContext(unitName = "JpaExample")
	private EntityManager em;
	
	@Path("/hello")
	@GET
	@Transactional
	public String hello() {
		Customer customer = new Customer();
		customer.setName("osman");
		customer.setSurname("yay");
		customer.setAge(50);
		em.persist(customer);
		return "hello world";
	}

	@Path("/insert")
	@POST
	@Transactional
	public String insert(Customer customer) {
		customer.setStartDate(new Date());
		GregorianCalendar calendar = new GregorianCalendar(TimeZone.getDefault());
		customer.setCalendar(calendar);
		customer.setLastUsage(LocalDate.of(2020, 1, 20));
		customer.setRequestDate(LocalDateTime.now());
		customer.setTestDate(System.currentTimeMillis());
		customer.setActivateTime(LocalTime.of(20, 20));
		customer.setAquireDate(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Europe/Paris")));
		em.persist(customer);
		return "Ok";
	}

	@Path("/personinsert")
	@POST
	@Transactional
	public String insertPerson(Person p) {
		em.persist(p);
		return "Ok";
	}

	
	@Path("/get/{customerId}")
	@GET
	@Transactional
	@Produces(MediaType.APPLICATION_JSON)
	public Customer get(@PathParam("customerId") long id) {
		Customer fromDatabase = em.find(Customer.class, id);
		return fromDatabase;
	}

	@Path("/getAll/{searchName}")
	@GET
	@Transactional
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getAll(@PathParam("searchName") String xyz) {
		TypedQuery<Customer> createQuery = em.createQuery("select c from Customer c where c.name = :isim",Customer.class);
		createQuery.setParameter("isim", xyz);
		List<Customer> resultList = createQuery.getResultList();
		return resultList;
	}

}





