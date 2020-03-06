package com.isbank.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
