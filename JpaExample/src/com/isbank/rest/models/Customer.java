package com.isbank.rest.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
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
	
	@Column(name = "isim",length = 50,nullable = false)
	private String name;
	private String surname;
	
	@Column(name="yas",unique = true)
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
	
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Address address;
	
	@Transient
	private int dontWantToWrite;
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "customer")
	private List<Account> accounts;

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

}
