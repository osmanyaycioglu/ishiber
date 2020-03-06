package com.isbank.rest.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@EntityListeners(Listener.class)
@Table(name = "Musteri", indexes = { @Index(columnList = "isim,surname", name = "name_surname_inx", unique = true) })
@SecondaryTable(name = "mdate")
//@XmlRootElement
//@SecondaryTables({@SecondaryTable(name = "mdate1"),@SecondaryTable(name = "mdate2")})
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tgen")
	@SequenceGenerator(sequenceName = "custSeq", name = "customerSequence", allocationSize = 50)
	@TableGenerator(table = "ctable", pkColumnName = "idg", initialValue = 1, allocationSize = 100, name = "tgen")
	private long customerId;

	@Column(name = "isim", length = 50, nullable = false)
	private String name;
	private String surname;

	@Column(name = "yas", unique = true)
	private int age;

	@Temporal(TemporalType.DATE)
	@Column(table = "mdate")
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(table = "mdate")
	private Calendar calendar;

	@Column(table = "mdate")
	private LocalDate lastUsage;

	@Column(table = "mdate")
	private LocalTime activateTime;

	@Column(table = "mdate")
	private LocalDateTime requestDate;

	@Column(table = "mdate")
	private ZonedDateTime aquireDate;

	private long testDate;

	@Embedded
	private CustomerExtra customerExtra;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Address address;

	@Transient
	private int dontWantToWrite;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "customer")
	private List<Account> accounts;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REFRESH },mappedBy = "customers")
	private Set<Project> projects;

	public Customer() {
	}

	public Customer(String name, String surname, int age) {
		this.name = name;
		this.surname = surname;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public LocalDate getLastUsage() {
		return lastUsage;
	}

	public void setLastUsage(LocalDate lastUsage) {
		this.lastUsage = lastUsage;
	}

	public LocalTime getActivateTime() {
		return activateTime;
	}

	public void setActivateTime(LocalTime activateTime) {
		this.activateTime = activateTime;
	}

	public LocalDateTime getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDateTime requestDate) {
		this.requestDate = requestDate;
	}

	public ZonedDateTime getAquireDate() {
		return aquireDate;
	}

	public void setAquireDate(ZonedDateTime aquireDate) {
		this.aquireDate = aquireDate;
	}

	public long getTestDate() {
		return testDate;
	}

	public void setTestDate(long testDate) {
		this.testDate = testDate;
	}

	public CustomerExtra getCustomerExtra() {
		return customerExtra;
	}

	public void setCustomerExtra(CustomerExtra customerExtra) {
		this.customerExtra = customerExtra;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getDontWantToWrite() {
		return dontWantToWrite;
	}

	public void setDontWantToWrite(int dontWantToWrite) {
		this.dontWantToWrite = dontWantToWrite;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accounts == null) ? 0 : accounts.hashCode());
		result = prime * result + ((activateTime == null) ? 0 : activateTime.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + age;
		result = prime * result + ((aquireDate == null) ? 0 : aquireDate.hashCode());
		result = prime * result + ((calendar == null) ? 0 : calendar.hashCode());
		result = prime * result + ((customerExtra == null) ? 0 : customerExtra.hashCode());
		result = prime * result + (int) (customerId ^ (customerId >>> 32));
		result = prime * result + dontWantToWrite;
		result = prime * result + ((lastUsage == null) ? 0 : lastUsage.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((projects == null) ? 0 : projects.hashCode());
		result = prime * result + ((requestDate == null) ? 0 : requestDate.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + (int) (testDate ^ (testDate >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (accounts == null) {
			if (other.accounts != null)
				return false;
		} else if (!accounts.equals(other.accounts))
			return false;
		if (activateTime == null) {
			if (other.activateTime != null)
				return false;
		} else if (!activateTime.equals(other.activateTime))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (age != other.age)
			return false;
		if (aquireDate == null) {
			if (other.aquireDate != null)
				return false;
		} else if (!aquireDate.equals(other.aquireDate))
			return false;
		if (calendar == null) {
			if (other.calendar != null)
				return false;
		} else if (!calendar.equals(other.calendar))
			return false;
		if (customerExtra == null) {
			if (other.customerExtra != null)
				return false;
		} else if (!customerExtra.equals(other.customerExtra))
			return false;
		if (customerId != other.customerId)
			return false;
		if (dontWantToWrite != other.dontWantToWrite)
			return false;
		if (lastUsage == null) {
			if (other.lastUsage != null)
				return false;
		} else if (!lastUsage.equals(other.lastUsage))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (projects == null) {
			if (other.projects != null)
				return false;
		} else if (!projects.equals(other.projects))
			return false;
		if (requestDate == null) {
			if (other.requestDate != null)
				return false;
		} else if (!requestDate.equals(other.requestDate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (testDate != other.testDate)
			return false;
		return true;
	}


}
