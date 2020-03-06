package com.isbank.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.Valid;

import com.isbank.rest.models.Person;

@Stateless
@LocalBean
public class PersonDAO {

	@PersistenceContext(unitName = "JpaExample")
	private EntityManager em;
    
	public PersonDAO() {
    }

    public void savePerson(Person person) {
    	em.persist(person);
    }

	public void updatePerson(Person person) {
		Person find = em.find(Person.class, person.getPersonId());
		find.setName(person.getName());
	}
	
	public Person getPerson(long personId) {
		TypedQuery<Person> createQuery = em.createQuery("select p from Person p where p.personId = :pid",Person.class);
		createQuery.setParameter("pid", personId);
		createQuery.setLockMode(LockModeType.PESSIMISTIC_READ);
		return createQuery.getSingleResult();
	}
    
}
