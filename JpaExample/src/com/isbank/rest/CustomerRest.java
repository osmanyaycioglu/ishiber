package com.isbank.rest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.isbank.rest.models.Account;
import com.isbank.rest.models.Address;
import com.isbank.rest.models.Customer;
import com.isbank.rest.models.Person;
import com.isbank.rest.models.Project;

import sun.util.calendar.Gregorian;

@RequestScoped
@Path("/customer")
public class CustomerRest {

	@PersistenceContext(unitName = "JpaExample")
	private EntityManager em;

//	@PersistenceUnit(unitName = "JpaExample")
//	private EntityManagerFactory emf;

	@Path("/hello")
	@GET
	@Transactional
	public String hello() {
//		EntityManager createEntityManager = emf.createEntityManager();
		Customer customer = new Customer();
		customer.setName("osman");
		customer.setSurname("yay");
		customer.setAge(50);
		em.persist(customer);
//		createEntityManager.close();

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

	@Path("/insertAll")
	@POST
	@Transactional
	public String insert(List<Customer> customers) {
		for (Customer customer : customers) {
			customer.getAddress().setCustomer(customer);
			List<Account> accounts = customer.getAccounts();
			for (Account acc : accounts) {
				acc.setCustomer(customer);
			}
			em.persist(customer);
		}
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
	public Customer get(@PathParam("customerId") long xyz) {
		Customer fromDatabase = em.find(Customer.class, xyz);
		Address address = fromDatabase.getAddress();
		System.out.println(address.getSehir());
		return fromDatabase;
	}

	@Path("/findAll/{searchName}")
	@GET
	@Transactional
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Customer> getAll(@PathParam("searchName") String xyz) {
		TypedQuery<Customer> createQuery = em.createQuery("select c from Customer c where c.name = :isim",
				Customer.class);
		createQuery.setParameter("isim", xyz);
		List<Customer> resultList = createQuery.getResultList();
		return resultList;
	}

	@Path("/getAll")
	@GET
	@Transactional
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getAll() {
		TypedQuery<Customer> createQuery = em.createQuery("select c from Customer c", Customer.class);
		List<Customer> resultList = createQuery.getResultList();
		return resultList;
	}

	@Path("/findLike/{searchName}")
	@GET
	@Transactional
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> findLike(@PathParam("searchName") String xyz) {
		TypedQuery<Customer> createQuery = em
				.createQuery("select c from Customer c where c.name LIKE CONCAT('%',:isim,'%')", Customer.class);
		createQuery.setParameter("isim", xyz);
		List<Customer> resultList = createQuery.getResultList();
		return resultList;
	}

	@Path("/getStat")
	@GET
	@Transactional
	@Produces(MediaType.APPLICATION_JSON)
	public String getStat() {
		Query createQuery = em.createQuery("select count(c),sum(c.age),avg(c.age) from Customer c");
		List<Object> resultList = createQuery.getResultList();
		String statstr = null;
		for (Object object : resultList) {
			Object[] stats = (Object[]) object;
			statstr = "count : " + stats[0] + " sum : " + stats[1] + " avg : " + stats[2];
		}
		return statstr;
	}

	@Path("/getAllNative")
	@GET
	@Transactional
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getAllNative() {
		Query createNativeQuery = em.createNativeQuery("select * from musteri", Customer.class);
		List<Customer> resultList = createNativeQuery.getResultList();
		return resultList;
	}

	@Path("/update")
	@POST
	@Transactional
	public String update(Customer customer) {
		customer.getAddress().setCustomer(customer);
		Customer mergedCustomer = em.merge(customer);
		return "Ok";
	}

	@Path("/update/{nn}/{ss}/{aa}")
	@GET
	@Transactional
	public String update(@PathParam("nn") String name, @PathParam("ss") String surname, @PathParam("aa") int age) {
		// em.getTransaction().begin();
		try {
			TypedQuery<Customer> createQuery = em
					.createQuery("select c from Customer c where c.name = :isim and c.surname = :soy", Customer.class);
			createQuery.setParameter("isim", name);
			createQuery.setParameter("soy", surname);

			Customer singleResult = createQuery.getSingleResult();
			boolean managed = em.contains(singleResult);
			// em.clear();
//			em.detach(singleResult);
//			em.close();
			// Customer mergedCustomer = em.merge(singleResult);
			if (singleResult != null) {
				singleResult.setAge(age);
			}
			em.flush();
			// em.getTransaction().commit();
			return "Ok";

		} catch (Exception e) {
			e.printStackTrace();
			// em.getTransaction().rollback();
		}
		return "NOTOK";
	}

	@Path("/update2/{nn}/{ss}/{aa}")
	@GET
	@Transactional
	public String update2(@PathParam("nn") String name, @PathParam("ss") String surname, @PathParam("aa") int age) {
		Query createQuery = em
				.createQuery("update Customer c set c.age = :yas where c.name = :isim and c.surname = :soy");
		createQuery.setParameter("isim", name);
		createQuery.setParameter("soy", surname);
		createQuery.setParameter("yas", age);
		createQuery.executeUpdate();
		return "Ok";
	}

	@Path("/remove/{mid}")
	@GET
	@Transactional
	public String remove(@PathParam("mid") long custId) {
		Customer customer = em.find(Customer.class, custId);
		if (customer != null) {
			em.remove(customer);
		}
		return "Ok";
	}

	@Path("/remove2/{mid}")
	@GET
	@Transactional
	public String remove2(@PathParam("mid") long custId) {
		Query createQuery = em.createQuery("delete from Customer c where c.customerId = :cid");
		createQuery.setParameter("cid", custId);
		int executeUpdate = createQuery.executeUpdate();
		return "Ok";
	}

	@Path("/project/add/{cid}")
	@POST
	@Transactional
	public String remove2(@PathParam("cid") long custId, Project project) {

		Customer customer = em.find(Customer.class, custId);
		if (customer != null) {
			TypedQuery<Project> createQuery = em.createQuery("select p from Project p where p.name = :name",
					Project.class);
			createQuery.setParameter("name", project.getName());
			Project projectFromDB = createQuery.getSingleResult();
			if (projectFromDB == null) {
				Set<Customer> customerSet = new HashSet<>();
				customerSet.add(customer);
				project.setCustomers(customerSet);
				em.persist(project);
			} else {
				Set<Customer> customers = projectFromDB.getCustomers();
				if (customers != null) {
					customers.add(customer);
				} else {
					Set<Customer> customerSet = new HashSet<>();
					customerSet.add(customer);
					projectFromDB.setCustomers(customerSet);

				}
			}
		}
		return "Ok";
	}

}
