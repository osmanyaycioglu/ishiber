package com.isbank.rest.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("oto")
public class Otomobil extends Arac {
	private int vites;

	public int getVites() {
		return vites;
	}

	public void setVites(int vites) {
		this.vites = vites;
	}
	
}
