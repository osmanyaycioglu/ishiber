package com.isbank.rest.models;

import java.util.Base64;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.isbank.rest.constraint.MyAnnoValid;
import com.isbank.rest.converter.MyConverter;
import com.isbank.rest.converter.MyType;

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long personId;

	@Column(length = 50)
	@Size(min = 2,max = 50,message = "Isim 2 ile 50 arasýnda olmalý")
	private String name;
	
	// @Size(min = 3,max = 50,message = "Soy isim 3 ile 50 arasýnda olmalý")
	@MyAnnoValid(length = 8,message = "8 den küçük olacak")
	private String surname;
	
	
	@Min(value = 10,message = "yaþ 10 dan küçük olamaz")
	@Max(150)
	private int age;
	
	@Convert(converter = MyConverter.class)	
	private MyType mType;

	@Transient
	private String tempSurname;

	public Person() {
	}


	@PrePersist
	@PreUpdate
	@PreRemove
	public void abc() {
		name = name.toUpperCase();
		tempSurname = surname;
		byte[] encode = Base64.getEncoder().encode(surname.getBytes());
		surname = new String(encode);
		System.out.println("abc running");
	}

	@PostPersist
	@PostUpdate
	@PostRemove
	public void xyz() {
		surname = tempSurname;
		name = name.toLowerCase();
		System.out.println("xyz running");
	}

	@PostLoad
	public void postLoad() {
		byte[] decode = Base64.getDecoder().decode(surname.getBytes());
		surname = new String(decode);

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

	public String getTempSurname() {
		return tempSurname;
	}

	public void setTempSurname(String tempSurname) {
		this.tempSurname = tempSurname;
	}

	public MyType getmType() {
		return mType;
	}

	public void setmType(MyType mType) {
		this.mType = mType;
	}


	public long getPersonId() {
		return personId;
	}


	public void setPersonId(long personId) {
		this.personId = personId;
	}


}
