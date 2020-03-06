package com.isbank.rest.models;

import java.util.Base64;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class Listener {
	@PrePersist
	@PreUpdate
	@PreRemove
	public void abc(Object obj) {
		System.out.println("abc running");
	}

	@PostPersist
	@PostUpdate
	@PostRemove
	@PostLoad
	public void xyz(Object obj) {
		System.out.println("xyz running");
	}


}
