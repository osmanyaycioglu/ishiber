package com.isbank.rest.models;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerExtra {
	private String esininIsmi;
	private int height;
	public String getEsininIsmi() {
		return esininIsmi;
	}
	public void setEsininIsmi(String esininIsmi) {
		this.esininIsmi = esininIsmi;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
